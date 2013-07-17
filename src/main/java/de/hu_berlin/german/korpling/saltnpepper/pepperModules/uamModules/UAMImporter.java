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
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.eclipse.emf.common.util.URI;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.log.LogService;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions.UAMImporterException;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.resources.UAMResource;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperExceptions.PepperModuleException;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.PepperImporter;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.PepperMapper;
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
public class UAMImporter extends PepperImporterImpl implements PepperImporter
{
	public UAMImporter()
	{
		super();
		//setting name of module
		this.name= "UAMImporter";
		//set list of formats supported by this module
		this.addSupportedFormat("UAM", "1.0", null);
	}
	
	private static final String PATH_TO_TEXT="./Corpus";
	
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
			throw new UAMModuleException("Cannot import corpus, because the given file-uri does not exist:"+corpusPath+" .");
		if (!corpusPath.isDirectory())
			throw new UAMModuleException("Cannot import corpus, because the given file-uri '"+corpusPath+"'is not a directory .");
		
		File analysesPath= new File(corpusPath.getAbsoluteFile()+ "/analyses/");
		if (!analysesPath.exists())
			throw new UAMModuleException("Cannot import corpus, because an analyses folder does not exist for given uri:"+corpusPath+" .");
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
				
				
				getSElementId2ResourceTable().put(sDocument.getSElementId(), URI.createFileURI(documentResource.getAbsolutePath()));
			}//each subfolder must be a document
		}// one folder as super corpus
	}
	
	/**
	 * Creates a mapper of type {@link EXMARaLDA2SaltMapper}.
	 * {@inheritDoc PepperModule#createPepperMapper(SElementId)}
	 */
	@Override
	public PepperMapper createPepperMapper(SElementId sElementId){
		UAM2SaltMapper mapper= new UAM2SaltMapper();
		mapper.setResourceOptions(getResourceOptions());
		return(mapper);
	}
	
	private Map<String, String> resourceOptions= null;
	public Map<String, String> getResourceOptions() {
		if (resourceOptions== null)
		{
			synchronized (this) {
				if (resourceOptions== null)
				{
					File path2Corpus= new File(this.getCorpusDefinition().getCorpusPath().toFileString()+ "/"+PATH_TO_TEXT);
					if (!path2Corpus.exists())
						throw new UAMImporterException("Cannot import document, because path to corpus '"+path2Corpus.getAbsolutePath()+"' does not exist.");
					resourceOptions= new Hashtable<String, String>();
					resourceOptions.put(UAMResource.PROP_PATH_2_TEXT, path2Corpus.getAbsolutePath());
				}
			}
		}
		return resourceOptions;
	}
}
