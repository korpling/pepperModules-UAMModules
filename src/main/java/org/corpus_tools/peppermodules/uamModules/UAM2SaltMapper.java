/**
 * Copyright 2009 Humboldt-Universität zu Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.corpus_tools.peppermodules.uamModules;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.corpus_tools.pepper.common.DOCUMENT_STATUS;
import org.corpus_tools.pepper.impl.PepperMapperImpl;
import org.corpus_tools.pepper.modules.exceptions.PepperModuleException;
import org.corpus_tools.peppermodules.model.uam.Layer;
import org.corpus_tools.peppermodules.model.uam.Segment;
import org.corpus_tools.peppermodules.model.uam.Text;
import org.corpus_tools.peppermodules.model.uam.UAMDocument;
import org.corpus_tools.peppermodules.model.uam.UAMFactory;
import org.corpus_tools.peppermodules.uam.resources.UAMResourceFactory;
import org.corpus_tools.salt.SaltFactory;
import org.corpus_tools.salt.common.SDocument;
import org.corpus_tools.salt.common.STextualDS;
import org.corpus_tools.salt.common.STextualRelation;
import org.corpus_tools.salt.common.SToken;
import org.corpus_tools.salt.core.SAnnotation;
import org.corpus_tools.salt.core.SLayer;
import org.corpus_tools.salt.core.SNode;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UAM2SaltMapper extends PepperMapperImpl{
	private static final Logger logger= LoggerFactory.getLogger(UAM2SaltMapper.class); 
	/**
	 * contains the mapping of an Text object to its corresponding STextualDS object
	 */
	private Hashtable<Text, STextualDS> text2STextualDS= null;
	
	private UAMDocument uamDocument= null;
	
	/**
	 * {@inheritDoc PepperMapper#setSDocument(SDocument)}
	 * 
	 * OVERRIDE THIS METHOD FOR CUSTOMIZED MAPPING.
	 */
	@Override
	public DOCUMENT_STATUS mapSDocument() {
		if (getResourceURI()== null){
			throw new PepperModuleException(this, "Cannot map the given uamDocument to sDocument, because uri for UAM document is null.");
		}
		if (uamDocument== null){
//			throw new PepperModuleException(this, "Cannot map the given uamDocument to sDocument, because uamDocument is null.");
			uamDocument= UAMFactory.eINSTANCE.createUAMDocument();
		}
		if (getDocument()== null){
			throw new PepperModuleException(this, "Cannot map the given uamDocument to sDocument, because sDocument is null.");
		}
		if (getDocument().getDocumentGraph()== null){
			getDocument().setDocumentGraph(SaltFactory.createSDocumentGraph());
		}
		
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register XML resource factory
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(null, new UAMResourceFactory());
		//this is because a lot of folders in uam ends with .txt when primary data are in txt format
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("txt", new UAMResourceFactory());
		//load resource 
		Resource resource = resourceSet.createResource(getResourceURI());
		
		if (resource== null){
			throw new PepperModuleException(this, "Cannot load the UAMDocument for path: "+ getResourceURI()+", becuase the resource is null.");
		}
		try {
			resource.load(getResourceOptions());
		} catch (IOException e) 
		{
			throw new PepperModuleException(this, "Cannot load the uam file: "+ getResourceURI()+".", e);
		}
		if (resource.getContents().size()!= 0)
		{
			Object obj= resource.getContents().get(0);
			if (	(obj!= null)&&
					(obj instanceof UAMDocument))
			{
				UAMDocument uamDocument= (UAMDocument) resource.getContents().get(0);
			}
		}
		
		getDocument().setName(uamDocument.getName());
		getDocument().setDocumentGraph(SaltFactory.createSDocumentGraph());
		{//map primary texts
			text2STextualDS= mapText2STextualDS(uamDocument, getDocument());
		}//map primary texts
		
		if (	(uamDocument.getLayers()!= null)&&
				(uamDocument.getLayers().size()> 0))
		{
			for (Layer layer: uamDocument.getLayers())
			{
				if (layer!= null)
				{
					SLayer sLayer= null;
					sLayer= this.mapLayer2SLayer(layer, getDocument());
					if (	(layer.getSegments()!= null)&&
							(layer.getSegments().size()> 0))
					{
						for (Segment segment: layer.getSegments())
						{
							if(segment!= null)
							{
								SToken sToken= mapSegments2SToken(segment, getDocument());
								sToken.getLayers().add(sLayer);
								mapSegment2SAnnotation(segment, sToken);
							}
						}
					}
				}
			}
		}
		return(DOCUMENT_STATUS.COMPLETED);
	}
	
	private Map<String, String> resourceOptions= null;
	public void setResourceOptions(Map<String, String> resourceOptions)
	{
		this.resourceOptions= resourceOptions;
	}
	public Map<String, String> getResourceOptions()
	{
		return(resourceOptions);
	}
	
	/**
	 * Maps all primary texts of a UAMDocument object to a SDocument object and returns a table of the corresponding mappings.
	 * @param uamDocument
	 * @param sDocument
	 * @return
	 */
	public Hashtable<Text, STextualDS> mapText2STextualDS(UAMDocument uamDocument, SDocument sDocument)
	{
		Hashtable<Text, STextualDS> text2STextualDS= null;
		if (	(uamDocument.getTexts()!= null)&&
				(uamDocument.getTexts().size()> 0))
		{
			text2STextualDS= new Hashtable<Text, STextualDS>();
			for (Text text: uamDocument.getTexts())
			{
				STextualDS sText= SaltFactory.createSTextualDS();
				sText.setName(text.getName());
				sText.setText(text.getText());
				sDocument.getDocumentGraph().addNode(sText);
				text2STextualDS.put(text, sText);
			}
		}
		return(text2STextualDS);
	}
	
	/**
	 * Creates an SLayer object and adds it to the given SDocument object. the method also creates all necessary
	 * SAnnotation objects.
	 * @param layer
	 * @return
	 */
	private SLayer mapLayer2SLayer(Layer layer, SDocument sDocument)
	{
		SLayer sLayer= null;
		if (layer!= null)
		{
			sLayer= SaltFactory.createSLayer();
			sLayer.setName(layer.getName());
			
			SAnnotation sAnno= SaltFactory.createSAnnotation();
			
			sAnno.setName("complete");
			sAnno.setValue(layer.getComplete());
			sLayer.addAnnotation(sAnno);
			
			sAnno= SaltFactory.createSAnnotation();
			sAnno.setName("lang");
			sAnno.setValue(layer.getLang());
			sLayer.addAnnotation(sAnno);
			
			sDocument.getDocumentGraph().addLayer(sLayer);
		}
		return(sLayer);
	}
	
	/**
	 * Maps an Segment object to an SToken object and returns this. This method also adds the SToken object
	 * to the given sDocument and creates a STextualRelation between the SToken object and the STextualDS object
	 * corresponding to the Text object referred by the given Segment object.
	 * @param segment
	 * @param sDocument
	 * @return SToken object which was created
	 */
	private SToken mapSegments2SToken(Segment segment, SDocument sDocument)
	{
		SToken sToken= null;
		if (segment!= null)
		{
			sToken= SaltFactory.createSToken();
			sDocument.getDocumentGraph().addNode(sToken);
			{//create relation to STextualDS
				if (segment.getSourceText()!= null)
				{
					STextualDS sText= text2STextualDS.get(segment.getSourceText());
					if (sText!= null)
					{
						STextualRelation sTextRel= SaltFactory.createSTextualRelation();
						sTextRel.setSource(sToken);
						sTextRel.setTarget(sText);
						sTextRel.setStart(segment.getStart());
						sTextRel.setEnd(segment.getEnd());
						sDocument.getDocumentGraph().addRelation(sTextRel);
					}
					logger.warn("Some SToken objects exist without refering to a STextualDS in SDocument '"+sDocument.getId()+"'.");
				}
				logger.warn("Some SToken objects exist without refering to a STextualDS in SDocument '"+sDocument.getId()+"'.");
			}//create relation to STextualDS
		}
		return(sToken);
	}
	
	/**
	 * Creates all necessary annotations for the given Segment. This method creates SAnnotation objects
	 * and adds them to the given SNode object.
	 * @param segment
	 */
	private void mapSegment2SAnnotation(Segment segment, SNode sNode)
	{
		SAnnotation sAnno= null;
		
		sAnno= SaltFactory.createSAnnotation();
		sAnno.setName("state");
		sAnno.setValue(segment.getState());
		sNode.addAnnotation(sAnno);
		
		sAnno= SaltFactory.createSAnnotation();
		sAnno.setName("features");
		sAnno.setValue(segment.getFeatures());
		sNode.addAnnotation(sAnno);
	}
}
