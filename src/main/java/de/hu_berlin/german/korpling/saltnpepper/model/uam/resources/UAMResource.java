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
package de.hu_berlin.german.korpling.saltnpepper.model.uam.resources;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMFactory;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions.UAMModelException;

public class UAMResource extends ResourceImpl
{	
	
	public static final String PROP_PATH_2_TEXT= "PATH_2_TEXT";
	/**
	 * Stores a a treetagger-model into tab-seperated file.
	 */
	public void save(java.util.Map<?,?> options) throws java.io.IOException
	{
		throw new UAMModelException("We are very sorry, but the storing of an UAMModel is not yet implemented.");
	}
	
	private static volatile SAXParserFactory factory= null;
	
	public synchronized static SAXParserFactory getFactory() {
		if (factory== null)
		{
			factory= SAXParserFactory.newInstance();
		}
		return factory;
	}

	public static void setFactory(SAXParserFactory factory) {
		UAMResource.factory = factory;
	}

	/**
	 * Loads a resource into treetagger-model from tab-seperated file.
	 */
	public void load(java.util.Map<?,?> options) throws IOException
	{
		if (	(options.get(PROP_PATH_2_TEXT)== null)||
        		(!(options.get(PROP_PATH_2_TEXT) instanceof String))||
        		((options.get(PROP_PATH_2_TEXT).equals(""))))
        {
        	throw new UAMModelException("Cannot load UAMDocument from uri '"+uri+"', because no path to text is set.");
        }
		 
		if (this.getURI()== null)
			throw new UAMModelException("Cannot load any resource, because no uri is given.");
		
		File uamPath= new File(this.getURI().toFileString());
		if (!uamPath.exists()) 
			throw new UAMModelException("Cannot load resource, because the file does not exist: " + uamPath);
		
		if (!uamPath.canRead())
			throw new UAMModelException("Cannot load resource, because the file can not be read: " + uamPath);
		
		UAMDocument uamDocument= null;
		uamDocument= UAMFactory.eINSTANCE.createUAMDocument();
		uamDocument.setName(uamPath.getName());
		for (File uamFile: uamPath.listFiles())
		{
			if (uamFile.toString().endsWith(".xml"))
			{
				{//fill UAMDocument
					SAXParser parser;
			        XMLReader xmlReader;
			        try {
			        	parser= getFactory().newSAXParser();
					
				        xmlReader= parser.getXMLReader();
		
				        //create and set content handler
				        
				        String layerName= uamFile.toString().replace(".xml", "");
				        String[] parts= layerName.split("/");
				        if (parts.length<=1)
				        	parts= layerName.split("\\\\");
				        layerName= parts[parts.length-1];
				        
				        
				        Layer layer= UAMFactory.eINSTANCE.createLayer();
				        layer.setName(layerName);
				        uamDocument.getLayers().add(layer);
				        
				        File path2Text= new File((String)options.get(PROP_PATH_2_TEXT));
				        
				        UAMReader uamReader= new UAMReader();
				        uamReader.setPath2Text(path2Text);
				        uamReader.setUamDocument(uamDocument);
				        uamReader.setCurrLayer(layer);
				        uamReader.setCorpusPath(uamPath);
				        uamReader.setDocumentURI(URI.createFileURI(uamFile.getAbsolutePath()));
				        xmlReader.setContentHandler(uamReader);
				        
				        //setting LexicalHandler to read DTD
				        xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", uamReader);
				        xmlReader.setContentHandler(uamReader);
				        
				        xmlReader.parse(uamFile.getAbsolutePath());
				        
				    } catch (ParserConfigurationException e) {
			        	throw new UAMModelException("Cannot load UAMDocument from resource '"+uamFile.getAbsolutePath()+"'.", e);
			        } catch (SAXException e) {
			        	throw new UAMModelException("Cannot load UAMDocument from resource '"+uamFile.getAbsolutePath()+"'.", e);
					}
				}//fill UAMDocument
			}
		}
		if (uamDocument!= null)
			this.getContents().add(uamDocument);
	}
}

