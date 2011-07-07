/**
 * Copyright 2009 Humboldt University of Berlin, INRIA.
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions.UAMImporterException;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.resources.UAMResource;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.resources.UAMResourceFactory;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperExceptions.PepperFWException;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperExceptions.PepperModuleException;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.FormatDefinition;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.PepperImporter;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.PepperInterfaceFactory;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.impl.PepperImporterImpl;
import de.hu_berlin.german.korpling.saltnpepper.pepperModules.uamModules.exceptions.UAMModuleException;
import de.hu_berlin.german.korpling.saltnpepper.salt.SaltFactory;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SCorpus;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SCorpusDocumentRelation;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SCorpusGraph;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SDocument;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SElementId;

/**
 * This PepperImporter imports data from the UAM tools output. Please note, that not the entire format structure is supported,
 * the importer only considers the content of the /corpus and /analyses folders. 
 * 
 * @author Florian Zipser
 * @version 1.0
 *
 */
@Component(name="UAMImporterComponent", factory="PepperImporterComponentFactory")
@Service(value=PepperImporter.class)
public class UAMImporter extends PepperImporterImpl implements PepperImporter
{
	public UAMImporter()
	{
		super();
		
		{//setting name of module
			this.name= "UAMImporter";
		}//setting name of module
		
		{//for testing the symbolic name has to be set without osgi
			if (	(this.getSymbolicName()==  null) ||
					(this.getSymbolicName().equals("")))
				this.setSymbolicName("de.hu_berlin.german.korpling.saltnpepper.pepperModules.UAMModules");
		}//for testing the symbolic name has to be set without osgi
		
		{//set list of formats supported by this module
			this.supportedFormats= new BasicEList<FormatDefinition>();
			FormatDefinition formatDef= PepperInterfaceFactory.eINSTANCE.createFormatDefinition();
			formatDef.setFormatName("UAM");
			formatDef.setFormatVersion("1.0");
			this.supportedFormats.add(formatDef);
		}
		
		{//just for logging: to say, that the current module has been loaded
			if (this.getLogService()!= null)
				this.getLogService().log(LogService.LOG_DEBUG,this.getName()+" is created...");
		}//just for logging: to say, that the current module has been loaded
	}
		
//===================================== start: performance variables
//	/**
//	 * Measured time which is needed to import the corpus structure. 
//	 */
//	private Long timeImportSCorpusStructure= 0l;
//	/**
//	 * Measured total time which is needed to import the document corpus structure. 
//	 */
//	private Long totalTimeImportSDocumentStructure= 0l;
//	/**
//	 * Measured time which is needed to load all documents into uam model.. 
//	 */
//	private Long totalTimeToLoadDocument= 0l;
//	/**
//	 * Measured time which is needed to map all documents to salt. 
//	 */
//	private Long totalTimeToMapDocument= 0l;
//===================================== end: performance variables
	
//===================================== start: thread number
	/**
	 * Defines the number of processes which can maximal work in parallel for importing documents.
	 * Means the number of parallel imported documents. Default value is 5.
	 */
	private Integer numOfParallelDocuments= 5;
	/**
	 * Sets the number of processes which can maximal work in parallel for importing documents.
	 * Means the number of parallel imported documents.
	 * @param numOfParallelDocuments the numOfParallelDocuments to set
	 */
	public void setNumOfParallelDocuments(Integer numOfParallelDocuments) {
		this.numOfParallelDocuments = numOfParallelDocuments;
	}

	/**
	 * Returns the number of processes which can maximal work in parallel for importing documents.
	 * Means the number of parallel imported documents.
	 * @return the numOfParallelDocuments
	 */
	public Integer getNumOfParallelDocuments() {
		return numOfParallelDocuments;
	}	
	
	public static final String PROP_NUM_OF_PARALLEL_DOCUMENTS="paulaImporter.numOfParallelDocuments";
//===================================== start: thread number
	
// ========================== start: flagging for parallel running	
	/**
	 * If true, PAULAImporter imports documents in parallel.
	 */
	private Boolean RUN_IN_PARALLEL= true;
	/**
	 * @param rUN_IN_PARALLEL the rUN_IN_PARALLEL to set
	 */
	public void setRUN_IN_PARALLEL(Boolean rUN_IN_PARALLEL) {
		RUN_IN_PARALLEL = rUN_IN_PARALLEL;
	}

	/**
	 * @return the RUN_IN_PARALLEL
	 */
	public Boolean getRUN_IN_PARALLEL() {
		return RUN_IN_PARALLEL;
	}
	
	/**
	 * Identifier of properties which contains the maximal number of parallel processed documents. 
	 */
	public static final String PROP_RUN_IN_PARALLEL="paulaImporter.runInParallel";
// ========================== end: flagging for parallel running
	/**
	 * a property representation of a property file
	 */
	protected Properties props= null;
	
	/**
	 * Extracts properties out of given special parameters.
	 */
	private void exctractProperties()
	{
		if (this.getSpecialParams()!= null)
		{//check if flag for running in parallel is set
			File propFile= new File(this.getSpecialParams().toFileString());
			this.props= new Properties();
			try{
				this.props.load(new FileInputStream(propFile));
			}catch (Exception e)
			{throw new UAMImporterException("Cannot find input file for properties: "+propFile+"\n nested exception: "+ e.getMessage());}
			if (this.props.containsKey(PROP_RUN_IN_PARALLEL))
			{
				try {
					Boolean val= new Boolean(this.props.getProperty(PROP_RUN_IN_PARALLEL));
					this.setRUN_IN_PARALLEL(val);
				} catch (Exception e) 
				{
					if (this.getLogService()!= null)
						this.getLogService().log(LogService.LOG_WARNING, "Cannot set correct property value of property "+PROP_RUN_IN_PARALLEL+" to "+this.getName()+", because of the value is not castable to Boolean. A correct value can contain 'true' or 'false'.");
				}
			}
			else if (this.props.containsKey(PROP_NUM_OF_PARALLEL_DOCUMENTS))
			{
				try {
					Integer val= new Integer(this.props.getProperty(PROP_NUM_OF_PARALLEL_DOCUMENTS));
					if (val > 0)
						this.setNumOfParallelDocuments(val);
				} catch (Exception e) 
				{
					if (this.getLogService()!= null)
						this.getLogService().log(LogService.LOG_WARNING, "Cannot set correct property value of property "+PROP_NUM_OF_PARALLEL_DOCUMENTS+" to "+this.getName()+", because of the value is not castable to Integer. A correct value must be a positiv, whole number (>0).");
				}
			}
		}//check if flag for running in parallel is set
	}
	
	private static final String PATH_TO_TEXT="./Corpus";
	
	/**
	 * ThreadPool
	 */
	private ExecutorService executorService= null;
	
	private Hashtable<SElementId, URI> sElement2Resource= null;
	public void setsElement2Resource(Hashtable<SElementId, URI> sElement2Resource) {
		this.sElement2Resource = sElement2Resource;
	}

	public Hashtable<SElementId, URI> getsElement2Resource() {
		if (sElement2Resource== null)
			sElement2Resource= new Hashtable<SElementId, URI>();
		return sElement2Resource;
	}
	
	/**
	 * 
	 * @param an empty graph given by Pepper, which shall contains the corpus structure
	 */
	@Override
	public synchronized void importCorpusStructure(SCorpusGraph sCorpusGraph)
			throws PepperModuleException
	{
		if (this.getCorpusDefinition()== null)
			throw new UAMModuleException("Cannot import corpus, because no CorpusDefinition object is given.");
		if (this.getCorpusDefinition().getCorpusPath()== null)
			throw new UAMModuleException("Cannot import corpus, because the given uri is empty.");
		
		if (getLogService()!= null)
			getLogService().log(LogService.LOG_DEBUG, this.getClass().getName()+ "> start importing of corpus structure for path '"+this.getCorpusDefinition().getCorpusPath()+"'... ");
		
		File corpusPath= new File(this.getCorpusDefinition().getCorpusPath().toFileString());
		
		if (!corpusPath.exists())
			throw new UAMModuleException("Cannot import corpus, because the given file-uri does not exists:"+corpusPath+" .");
		if (!corpusPath.isDirectory())
			throw new UAMModuleException("Cannot import corpus, because the given file-uri '"+corpusPath+"'is not a directory .");
		
		File analysesPath= new File(corpusPath.getAbsoluteFile()+ "/analyses/");
		if (!analysesPath.exists())
			throw new UAMModuleException("Cannot import corpus, because an analyses folder does not exists for given uri:"+corpusPath+" .");
		if (!analysesPath.isDirectory())
			throw new UAMModuleException("Cannot import corpus, because the analyses folder for :"+corpusPath+" is not a folder.");
		
		for (File corpusResource: analysesPath.listFiles())
		{// one folder as super corpus
			SCorpus sCorpus= null;
			{//create SCorpus object
				sCorpus= SaltFactory.eINSTANCE.createSCorpus();
				sCorpus.setSName(corpusResource.getAbsoluteFile().getName());
				sCorpusGraph.addSNode(sCorpus);
			}//create SCorpus object
			
			for (File documentResource: corpusResource.listFiles())
			{//each subfolder must be a document
				SDocument sDocument= SaltFactory.eINSTANCE.createSDocument();
				sDocument.setSName(documentResource.getName());
				sCorpusGraph.addSNode(sDocument);
				SCorpusDocumentRelation sCorpDocRel= SaltFactory.eINSTANCE.createSCorpusDocumentRelation();
				sCorpDocRel.setSCorpus(sCorpus);
				sCorpDocRel.setSDocument(sDocument);
				sCorpusGraph.addSRelation(sCorpDocRel);
				
				
				this.getsElement2Resource().put(sDocument.getSElementId(), URI.createFileURI(documentResource.getAbsolutePath()));
				//TODO remove
				if (getLogService()!= null)
					getLogService().log(LogService.LOG_DEBUG, this.getClass().getName()+ "> HIER 01: "+documentResource.getAbsolutePath()+"::"+sDocument.getSElementId());
	//			SElementId sDocumentId= SaltFactory.eINSTANCE.createSElementId();
				
	//			sDocumentId.setSId(corpusPath.getName()+ "/"+ documentResource.getName());
	//			this.getsElement2Resource().put(sDocumentId, URI.createFileURI(documentResource.getAbsolutePath()));
			}//each subfolder must be a document
		}// one folder as super corpus
	}
	
	@Override
	public void start() throws PepperModuleException
	{	
		if (this.mapperRunners== null)
		{
			synchronized (this) {
				if (this.mapperRunners== null)
					this.mapperRunners= new BasicEList<MapperRunner>();
			}
		}
		{//extracts special parameters
			this.exctractProperties();
		}//extracts special parameters
		{//initialize ThreadPool
			if (executorService== null)
			{
				synchronized (this) {
					if (executorService== null)
					{
						executorService= Executors.newFixedThreadPool(this.getNumOfParallelDocuments());
					}
				}
			}
		}//initialize ThreadPool
		boolean isStart= true;
		SElementId sElementId= null;
		while ((isStart) || (sElementId!= null))
		{	
			isStart= false;
			sElementId= this.getPepperModuleController().get();
			if (sElementId== null)
				break;
			
			
			//call for using push-method
			this.start(sElementId);
		}	
		for (MapperRunner mapperRunner: this.mapperRunners)
		{
			mapperRunner.waitUntilFinish();
		}
		this.end();
	}
	
	/**
	 * List of all used mapper runners.
	 */
	private EList<MapperRunner> mapperRunners= null;
	
	/**
	 * This method is called by method start() of superclass PepperImporter, if the method was not overriden
	 * by the current class. If this is not the case, this method will be called for every document which has
	 * to be processed.
	 * @param sElementId the id value for the current document or corpus to process  
	 */
	@Override
	public void start(SElementId sElementId) throws PepperModuleException 
	{
		if (	(sElementId!= null) &&
				(sElementId.getSIdentifiableElement()!= null) &&
				((sElementId.getSIdentifiableElement() instanceof SDocument) ||
				((sElementId.getSIdentifiableElement() instanceof SCorpus))))
		{//only if given sElementId belongs to an object of type SDocument or SCorpus	
			if (sElementId.getSIdentifiableElement() instanceof SDocument)
			{//mapping SDocument
				if (getLogService()!= null)
					getLogService().log(LogService.LOG_DEBUG, this.getClass().getName()+ "> start importing of data for SDocument object '"+sElementId+"'... ");
				
				SDocument sDocument= (SDocument) sElementId.getSIdentifiableElement();
				MapperRunner mapperRunner= new MapperRunner();
				{//configure mapper and mapper runner
					UAM2SaltMapper mapper= new UAM2SaltMapper();
					mapperRunner.mapper= mapper;
					mapperRunner.sDocument= sDocument;
					mapper.setCurrentSDocument(sDocument);
					mapper.setLogService(this.getLogService());
				}//configure mapper and mapper runner
				
				if (this.getRUN_IN_PARALLEL())
				{//run import in parallel	
					this.mapperRunners.add(mapperRunner);
					this.executorService.execute(mapperRunner);
				}//run import in parallel
				else 
				{//do not run import in parallel
					mapperRunner.start();
				}//do not run import in parallel
			}//mapping SDocument
		}//only if given sElementId belongs to an object of type SDocument or SCorpus
	}
	
	private Hashtable<String, String> resourceOptions= null;
	
	
	public Hashtable<String, String> getResourceOptions() {
		if (resourceOptions== null)
		{
			synchronized (this) {
				if (resourceOptions== null)
				{
					File path2Corpus= new File(this.getCorpusDefinition().getCorpusPath().toFileString()+ "/"+PATH_TO_TEXT);
					if (!path2Corpus.exists())
						throw new UAMImporterException("Cannot import document, because path to corpus '"+path2Corpus.getAbsolutePath()+"' does not exists.");
					resourceOptions= new Hashtable<String, String>();
					resourceOptions.put(UAMResource.PROP_PATH_2_TEXT, path2Corpus.getAbsolutePath());
				}
			}
		}
		return resourceOptions;
	}

	public void setResourceOptions(Hashtable<String, String> resourceOptions) {
		this.resourceOptions = resourceOptions;
	}

	/**
	 * This class is a container for running PAULAMappings in parallel.
	 * @author Administrator
	 *
	 */
	private class MapperRunner implements java.lang.Runnable
	{
		public SDocument sDocument= null;
		UAM2SaltMapper mapper= null;
		
		/**
		 * Lock to lock await and signal methods.
		 */
		protected Lock lock= new ReentrantLock();
		
		/**
		 * Flag wich says, if mapperRunner has started and finished
		 */
		private Boolean isFinished= false;
		
		/**
		 * If condition is achieved a new SDocument can be created.
		 */
		private Condition finishCondition=lock.newCondition();
		
		public void waitUntilFinish()
		{
			lock.lock();
			try {
				if (!isFinished)
					finishCondition.await();
			} catch (InterruptedException e) {
				throw new PepperFWException(e.getMessage());
			}
			lock.unlock();
		}
		
		@Override
		public void run() 
		{
			start();
		}
		
		/**
		 * starts Mapping of PAULA data
		 */
		public void start()
		{
			//TODO remove
			if (getLogService()!= null)
				getLogService().log(LogService.LOG_DEBUG, "----> HIER01");
			
			if (mapper== null)
				throw new UAMImporterException("BUG: Cannot start import, because the mapper is null.");
			if (sDocument== null)
				throw new UAMImporterException("BUG: Cannot start import, because no SDocument object is given.");
			{//getting UAMDocument object
				URI uamDoc= getsElement2Resource().get(sDocument.getSElementId());
				if (uamDoc== null)
					throw new UAMImporterException("BUG: Cannot start import, no uam-document-path was found for SDocument '"+sDocument.getSElementId()+"'.");
				ResourceSet resourceSet = new ResourceSetImpl();
				// Register XML resource factory
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(null, new UAMResourceFactory());
				//this is because a lot of folders in uam ends with .txt when primary data are in txt format
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("txt", new UAMResourceFactory());
				//load resource 
				Resource resource = resourceSet.createResource(uamDoc);
				
				
				if (resource== null)
					throw new UAMImporterException("Cannot load the UAMDocument for path: "+ uamDoc+", becuase the resource is null.");
				try {
					resource.load(getResourceOptions());
				} catch (IOException e) 
				{
					throw new UAMImporterException("Cannot load the uam file: "+ uamDoc+".", e);
				}
				if (resource.getContents().size()!= 0)
				{
					Object obj= resource.getContents().get(0);
					if (	(obj!= null)&&
							(obj instanceof UAMDocument))
					{
						//TODO remove
						if (getLogService()!= null)
							getLogService().log(LogService.LOG_DEBUG, "----> HIER02");
						
						UAMDocument uamDocument= (UAMDocument) resource.getContents().get(0);
						mapper.mapUAMDocument2SDocument(uamDocument, sDocument);
					}
				}
			}//getting UAMDocument object
			
			try 
			{
				getPepperModuleController().put(this.sDocument.getSElementId());
			}catch (Exception e)
			{
				if (getLogService()!= null)
				{
					getLogService().log(LogService.LOG_WARNING, "Cannot import the SDocument '"+sDocument.getSName()+"'. The reason is: "+e);
				}
				getPepperModuleController().finish(this.sDocument.getSElementId());
			}
			this.lock.lock();
			this.isFinished= true;
			this.finishCondition.signal();
			this.lock.unlock();
		}
	}
	
//================================ start: methods used by OSGi
	/**
	 * This method is called by the OSGi framework, when a component with this class as class-entry
	 * gets activated.
	 * @param componentContext OSGi-context of the current component
	 */
	protected void activate(ComponentContext componentContext) 
	{
		this.setSymbolicName(componentContext.getBundleContext().getBundle().getSymbolicName());
		{//just for logging: to say, that the current module has been activated
			if (this.getLogService()!= null)
				this.getLogService().log(LogService.LOG_DEBUG,this.getName()+" is activated...");
		}//just for logging: to say, that the current module has been activated
	}

	/**
	 * This method is called by the OSGi framework, when a component with this class as class-entry
	 * gets deactivated.
	 * @param componentContext OSGi-context of the current component
	 */
	protected void deactivate(ComponentContext componentContext) 
	{
		{//just for logging: to say, that the current module has been deactivated
			if (this.getLogService()!= null)
				this.getLogService().log(LogService.LOG_DEBUG,this.getName()+" is deactivated...");
		}	
	}
//================================ start: methods used by OSGi

	
}
