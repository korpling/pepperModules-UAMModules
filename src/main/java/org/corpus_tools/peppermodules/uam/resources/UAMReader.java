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
package org.corpus_tools.peppermodules.uam.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import org.corpus_tools.peppermodules.model.uam.Layer;
import org.corpus_tools.peppermodules.model.uam.Segment;
import org.corpus_tools.peppermodules.model.uam.Text;
import org.corpus_tools.peppermodules.model.uam.UAMDocument;
import org.corpus_tools.peppermodules.model.uam.UAMFactory;
import org.corpus_tools.peppermodules.uam.exceptions.UAMModelException;
import org.corpus_tools.peppermodules.uam.exceptions.UAMResourceException;
import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

public class UAMReader extends DefaultHandler2
{
	
	private Logger logger= LoggerFactory.getLogger(UAMReader.class); 
	private UAMDocument uamDocument= null;

	public void setUamDocument(UAMDocument uamDocument) {
		this.uamDocument = uamDocument;
	}

	public UAMDocument getUamDocument() {
		return uamDocument;
	}
	/** File which has to be imported by that reader**/
	private URI documentURI= null;
	
	/** Returns URI of File, which is to import by this reader. **/
	public URI getDocumentURI() {
		return documentURI;
	}
	/** Stes the URI of File, which is to import by this reader. **/
	public void setDocumentURI(URI documentURI) {
		this.documentURI = documentURI;
	}
	private Layer currLayer= null;
	
	
	public Layer getCurrLayer() {
		return currLayer;
	}

	public void setCurrLayer(Layer currLayer) {
		this.currLayer = currLayer;
	}

	private File path2Text= null;
	
	
	
	public File getPath2Text() {
		return path2Text;
	}

	public void setPath2Text(File path2Text) {
		this.path2Text = path2Text;
	}

	
	private File corpusPath= null;
	
	
	public File getCorpusPath() {
		return corpusPath;
	}

	public void setCorpusPath(File corpusPath) {
		this.corpusPath = corpusPath;
	}

	public void startDocument() throws SAXException
    {
    }
    
    public void comment(char[] ch, int start, int length)
    {
    }
    
    /**
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(	char[] ch,
            				int start,
            				int length) throws SAXException
    {
		if (isTextFile)
		{//name of textFile
			StringBuffer text= new StringBuffer();
			for (int i= start; i< start+length; i++)
				text.append(ch[i]);
			String[] parts= text.toString().trim().split("/");
			String textFileName= parts[parts.length-1];
			textFileName= text.toString();

			if (	(textFileName== null) ||
					(textFileName.equals("")))
				throw new UAMModelException("Cannot read UAMDocument, because path to textfile is corrupt: "+text+".");
			parts= textFileName.split("[.]");
			String textName= parts[parts.length-1];
			if (	(textName== null) ||
					(textName.equals("")))
				throw new UAMModelException("Cannot read UAMDocument, because path to textfile is corrupt: "+text+".");
			
			boolean alreadyContainsText= false;
			if (	(this.getUamDocument().getTexts()!= null) &&
					(this.getUamDocument().getTexts().size()>0))
			{
				for (Text uamText: this.getUamDocument().getTexts())
				{
					if (uamText.getName().equals(textName))
					{
						alreadyContainsText= true;
						this.currText= uamText;
						break;
					}
				}
			}
			if (!alreadyContainsText)
			{
				File textFile= new File(this.getPath2Text().getAbsolutePath()+ "/"+ textFileName);
				if (!textFile.exists())
					throw new UAMResourceException("Cannot read primary data ('"+textFile.getAbsolutePath()+"') refered by file: ");
				StringBuffer contents = new StringBuffer();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(textFile));
				    String inputText = null;
				    // repeat until all lines is read
				    while ((inputText = reader.readLine()) != null) 
				    {
				    	contents.append(inputText).append(System.getProperty("line.separator"));
				    }
				} catch (FileNotFoundException e) {
					throw new UAMModelException("", e);
				} catch (IOException e) {
					throw new UAMModelException("", e);
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException e) {
						throw new UAMModelException("", e);
					}
				}
				Text uamText= UAMFactory.eINSTANCE.createText();
				uamText.setText(contents.toString());
				uamText.setName(textName);
				this.getUamDocument().getTexts().add(uamText);
				this.currText= uamText;
			}
		}//name of textFile
    }
	
	/**
	 * The current object, to store a textual value into it.
	 */
	private Stack<String> currXMLElementName= null;
	
	/**
	 * Stores the current text.
	 */
	private Text currText= null;
	
	public Stack<String> getCurrXMLElementName() {
		if (currXMLElementName== null)
		{
			synchronized (this) 
			{
				if (currXMLElementName== null)
				{
					currXMLElementName= new Stack<String>();
				}
			}
		}
		return currXMLElementName;
	}

//	private static final String XML_ELEMENT_HEADER= "header";
	public static final String XML_ELEMENT_SEGMENT= "segment";
	public static final String XML_ELEMENT_TEXTFILE= "textfile";
	public static final String XML_ATTRIBUTE_SEGMENT_ID= "id";
	public static final String XML_ATTRIBUTE_SEGMENT_FEATURE= "features";
	public static final String XML_ATTRIBUTE_SEGMENT_START= "start";
	public static final String XML_ATTRIBUTE_SEGMENT_END= "end";
	public static final String XML_ATTRIBUTE_SEGMENT_STATE= "state";
	
	
	private boolean isTextFile= false;
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(	String uri,
            					String localName,
            					String qName,
            					Attributes attributes) throws SAXException
    {
		if (qName.equalsIgnoreCase(XML_ELEMENT_TEXTFILE))
		{
			this.isTextFile= true;
		}
		else if (qName.equalsIgnoreCase(XML_ELEMENT_SEGMENT))
		{
			Segment segment= UAMFactory.eINSTANCE.createSegment();
			this.getCurrLayer().getSegments().add(segment);
			if (this.currText== null)
				throw new UAMResourceException("Cannot set the corresponding text to segment, because it is null.");
			segment.setSourceText(this.currText);
			
			segment.setId(attributes.getValue(XML_ATTRIBUTE_SEGMENT_ID));
			segment.setFeatures(attributes.getValue(XML_ATTRIBUTE_SEGMENT_FEATURE));
			segment.setState(attributes.getValue(XML_ATTRIBUTE_SEGMENT_STATE));
			try{
				segment.setStart(new Integer(attributes.getValue(XML_ATTRIBUTE_SEGMENT_START)));
				segment.setEnd(new Integer(attributes.getValue(XML_ATTRIBUTE_SEGMENT_END)));
				if (segment.getEnd()< segment.getStart())
				{//end has a smaller value than start, it seems to be a mistake --> switch values
					int tmpStart= segment.getStart();
					segment.setStart(segment.getEnd());
					segment.setEnd(tmpStart);
				}//end has a smaller value than start, it seems to be a mistake --> switch values
				if (segment.getEnd()>= this.currText.getText().length())
					segment.setEnd(this.currText.getText().length());
					logger.warn("The end of a 'segment' in file '"+getDocumentURI()+"' is bigger than the max. size of the text ("+this.currText.getText().length()+"). Therefore the end of the segment was cutted at end of the text. ");
//					throw new UAMResourceException("Cannot read given corpus, because the attribute '"+XML_ATTRIBUTE_SEGMENT_END+"' in file '"+getDocumentURI()+"' contains a value ("+segment.getEnd()+") which is larger than the max. size of the text ("+this.currText.getText().length()+").");
			}
			catch (NumberFormatException e)
			{//do nothing in this case
			}//do nothing in this case
		}
		this.getCurrXMLElementName().push(qName);
    }
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		if (!this.getCurrXMLElementName().peek().equalsIgnoreCase(qName))
			throw new UAMResourceException("The given file is not wellformed. Expected element is: "+this.getCurrXMLElementName().peek() + ", but given is: "+qName);
		if (qName.equalsIgnoreCase(XML_ELEMENT_TEXTFILE))
		{
			this.isTextFile= false;
		}
		this.getCurrXMLElementName().pop();
	}
}
