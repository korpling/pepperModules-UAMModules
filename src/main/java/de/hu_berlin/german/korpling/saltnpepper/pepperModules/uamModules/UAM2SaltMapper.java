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
package de.hu_berlin.german.korpling.saltnpepper.pepperModules.uamModules;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.Text;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.resources.UAMResourceFactory;
import de.hu_berlin.german.korpling.saltnpepper.pepper.common.DOCUMENT_STATUS;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.exceptions.PepperModuleException;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.impl.PepperMapperImpl;
import de.hu_berlin.german.korpling.saltnpepper.salt.SaltFactory;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SDocument;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sDocumentStructure.STextualDS;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sDocumentStructure.STextualRelation;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sDocumentStructure.SToken;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SAnnotation;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SLayer;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SNode;

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
		if (getResourceURI()== null)
			throw new PepperModuleException(this, "Cannot map the given uamDocument to sDocument, because uri for UAM document is null.");
		if (uamDocument== null)
			throw new PepperModuleException(this, "Cannot map the given uamDocument to sDocument, because uamDocument is null.");
		if (getSDocument()== null)
			throw new PepperModuleException(this, "Cannot map the given uamDocument to sDocument, because sDocument is null.");
		
		if (getSDocument().getSDocumentGraph()== null)
			getSDocument().setSDocumentGraph(SaltFactory.eINSTANCE.createSDocumentGraph());
		
		
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register XML resource factory
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(null, new UAMResourceFactory());
		//this is because a lot of folders in uam ends with .txt when primary data are in txt format
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("txt", new UAMResourceFactory());
		//load resource 
		Resource resource = resourceSet.createResource(getResourceURI());
		
		if (resource== null)
			throw new PepperModuleException(this, "Cannot load the UAMDocument for path: "+ getResourceURI()+", becuase the resource is null.");
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
		
		getSDocument().setSName(uamDocument.getName());
		getSDocument().setSDocumentGraph(SaltFactory.eINSTANCE.createSDocumentGraph());
		{//map primary texts
			text2STextualDS= mapText2STextualDS(uamDocument, getSDocument());
		}//map primary texts
		
		if (	(uamDocument.getLayers()!= null)&&
				(uamDocument.getLayers().size()> 0))
		{
			for (Layer layer: uamDocument.getLayers())
			{
				if (layer!= null)
				{
					SLayer sLayer= null;
					sLayer= this.mapLayer2SLayer(layer, getSDocument());
					if (	(layer.getSegments()!= null)&&
							(layer.getSegments().size()> 0))
					{
						for (Segment segment: layer.getSegments())
						{
							if(segment!= null)
							{
								SToken sToken= mapSegments2SToken(segment, getSDocument());
								sToken.getSLayers().add(sLayer);
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
				STextualDS sText= SaltFactory.eINSTANCE.createSTextualDS();
				sText.setSName(text.getName());
				sText.setSText(text.getText());
				sDocument.getSDocumentGraph().addSNode(sText);
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
			sLayer= SaltFactory.eINSTANCE.createSLayer();
			sLayer.setSName(layer.getName());
			
			SAnnotation sAnno= SaltFactory.eINSTANCE.createSAnnotation();
			
			sAnno.setSName("complete");
			sAnno.setSValue(layer.getComplete());
			sLayer.addSAnnotation(sAnno);
			
			sAnno= SaltFactory.eINSTANCE.createSAnnotation();
			sAnno.setSName("lang");
			sAnno.setSValue(layer.getLang());
			sLayer.addSAnnotation(sAnno);
			
			sDocument.getSDocumentGraph().addSLayer(sLayer);
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
			sToken= SaltFactory.eINSTANCE.createSToken();
			sDocument.getSDocumentGraph().addSNode(sToken);
			{//create relation to STextualDS
				if (segment.getSourceText()!= null)
				{
					STextualDS sText= text2STextualDS.get(segment.getSourceText());
					if (sText!= null)
					{
						STextualRelation sTextRel= SaltFactory.eINSTANCE.createSTextualRelation();
						sTextRel.setSToken(sToken);
						sTextRel.setSTextualDS(sText);
						sTextRel.setSStart(segment.getStart());
						sTextRel.setSEnd(segment.getEnd());
						sDocument.getSDocumentGraph().addSRelation(sTextRel);
					}
					logger.warn("Some SToken objects exist without refering to a STextualDS in SDocument '"+sDocument.getSId()+"'.");
				}
				logger.warn("Some SToken objects exist without refering to a STextualDS in SDocument '"+sDocument.getSId()+"'.");
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
		
		sAnno= SaltFactory.eINSTANCE.createSAnnotation();
		sAnno.setSName("state");
		sAnno.setSValue(segment.getState());
		sNode.addSAnnotation(sAnno);
		
		sAnno= SaltFactory.eINSTANCE.createSAnnotation();
		sAnno.setSName("features");
		sAnno.setSValue(segment.getFeatures());
		sNode.addSAnnotation(sAnno);
	}
}
