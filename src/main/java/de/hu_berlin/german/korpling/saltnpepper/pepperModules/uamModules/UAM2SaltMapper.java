package de.hu_berlin.german.korpling.saltnpepper.pepperModules.uamModules;

import java.util.Hashtable;

import org.eclipse.emf.common.util.URI;
import org.osgi.service.log.LogService;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.Text;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions.UAMImporterException;
import de.hu_berlin.german.korpling.saltnpepper.salt.SaltFactory;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SDocument;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sDocumentStructure.STextualDS;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sDocumentStructure.STextualRelation;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sDocumentStructure.SToken;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SAnnotation;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SLayer;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SNode;

public class UAM2SaltMapper {
	// ================================================ start: LogService	
	private LogService logService;

	public void setLogService(LogService logService) 
	{
		this.logService = logService;
	}
	
	public LogService getLogService() 
	{
		return(this.logService);
	}
// ================================================ end: LogService
// ================================================ start: physical path of the uam-documents
	/**
	 * Stores the physical path of the uam-documents.
	 */
	private URI currentUAMDocument= null;
	/**
	 * @param currentuamDocument the currentuamDocument to set
	 */
	public void setCurrentUAMDocument(URI currentUAMDocument) {
		this.currentUAMDocument = currentUAMDocument;
	}

	/**
	 * @return the currentuamDocument
	 */
	public URI getCurrentUAMDocument() {
		return currentUAMDocument;
	}
	
// ================================================ end: physical path of the uam-documents
// ================================================ start: current SDocument	
	private SDocument currentSDocument= null;
	/**
	 * @param currentSDocument the currentSDocument to set
	 */
	public void setCurrentSDocument(SDocument currentSDocument) {
		this.currentSDocument = currentSDocument;
	}

	/**
	 * @return the currentSDocument
	 */
	public SDocument getCurrentSDocument() {
		return this.currentSDocument;
	}
// ================================================ end: current SDocument
	/**
	 * contains the mapping of an Text object to its corresponding STextualDS object
	 */
	private Hashtable<Text, STextualDS> text2STextualDS= null;
	
	/**
	 * Maps a given UAMDocument object to a given SDocument object.
	 * @param uamDocument object to map
	 * @param sDocument object to map to
	 */
	public void mapUAMDocument2SDocument(UAMDocument uamDocument, SDocument sDocument)
	{
		if (uamDocument== null)
			throw new UAMImporterException("Cannot map the given uamDocument to sDocument, because uamDocument is null.");
		if (sDocument== null)
			throw new UAMImporterException("Cannot map the given uamDocument to sDocument, because sDocument is null.");
				
		sDocument.setSName(uamDocument.getName());
		sDocument.setSDocumentGraph(SaltFactory.eINSTANCE.createSDocumentGraph());
		{//map primary texts
			text2STextualDS= mapText2STextualDS(uamDocument, sDocument);
		}//map primary texts
		
		if (	(uamDocument.getLayers()!= null)&&
				(uamDocument.getLayers().size()> 0))
		{
			for (Layer layer: uamDocument.getLayers())
			{
				if (layer!= null)
				{
					SLayer sLayer= null;
					sLayer= this.mapLayer2SLayer(layer, sDocument);
					if (	(layer.getSegments()!= null)&&
							(layer.getSegments().size()> 0))
					{
						for (Segment segment: layer.getSegments())
						{
							if(segment!= null)
							{
								SToken sToken= mapSegments2SToken(segment, sDocument);
								sLayer.getSNodes().add(sToken);
								mapSegment2SAnnotation(segment, sToken);
							}
						}
					}
				}
			}
		}
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
					else this.getLogService().log(LogService.LOG_WARNING, "Some SToken objects exist without refering to a STextualDS in SDocument '"+sDocument.getSId()+"'.");
				}
				else this.getLogService().log(LogService.LOG_WARNING, "Some SToken objects exist without refering to a STextualDS in SDocument '"+sDocument.getSId()+"'.");
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
