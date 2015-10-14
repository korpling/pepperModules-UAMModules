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

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.corpus_tools.pepper.impl.PepperImporterImpl;
import org.corpus_tools.pepper.modules.PepperImporter;
import org.corpus_tools.pepper.modules.PepperMapper;
import org.corpus_tools.pepper.modules.exceptions.PepperModuleException;
import org.corpus_tools.salt.SaltFactory;
import org.corpus_tools.salt.common.SCorpus;
import org.corpus_tools.salt.common.SCorpusDocumentRelation;
import org.corpus_tools.salt.common.SCorpusGraph;
import org.corpus_tools.salt.common.SDocument;
import org.corpus_tools.salt.graph.Identifier;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.resources.UAMResource;

/**
 * This PepperImporter imports data from the UAM tools output. Please note, that
 * not the entire format structure is supported, the importer only considers the
 * content of the /corpus and /analyses folders.
 * 
 * @author Florian Zipser
 * @version 1.0
 *
 */
@Component(name = "UAMImporterComponent", factory = "PepperImporterComponentFactory")
public class UAMImporter extends PepperImporterImpl implements PepperImporter {
	private static final Logger logger = LoggerFactory.getLogger(UAMImporter.class);

	public UAMImporter() {
		super();
		// setting name of module
		this.setName("UAMImporter");
		setSupplierContact(URI.createURI("saltnpepper@lists.hu-berlin.de"));
		setSupplierHomepage(URI.createURI("https://github.com/korpling/pepperModules-UAMModules"));
		setDesc("This importer transforms data in UAM format produced by the UAM corpus tool to a Salt model. ");
		// set list of formats supported by this module
		this.addSupportedFormat("UAM", "1.0", null);
	}

	private static final String PATH_TO_TEXT = "./Corpus";

	/**
	 * 
	 * @param an
	 *            empty graph given by Pepper, which shall contains the corpus
	 *            structure
	 */
	@Override
	public synchronized void importCorpusStructure(SCorpusGraph sCorpusGraph) throws PepperModuleException {
		if (this.getCorpusDesc() == null)
			throw new PepperModuleException(this, "Cannot import corpus, because no CorpusDefinition object is given.");
		if (this.getCorpusDesc().getCorpusPath() == null)
			throw new PepperModuleException(this, "Cannot import corpus, because the given uri is empty.");

		logger.debug(this.getClass().getName() + "> start importing of corpus structure for path '" + this.getCorpusDesc().getCorpusPath() + "'... ");

		File corpusPath = new File(this.getCorpusDesc().getCorpusPath().toFileString());

		if (!corpusPath.exists())
			throw new PepperModuleException(this, "Cannot import corpus, because the given file-uri does not exist:" + corpusPath + " .");
		if (!corpusPath.isDirectory())
			throw new PepperModuleException(this, "Cannot import corpus, because the given file-uri '" + corpusPath + "'is not a directory .");

		File analysesPath = new File(corpusPath.getAbsoluteFile() + "/analyses/");
		if (!analysesPath.exists())
			throw new PepperModuleException(this, "Cannot import corpus, because an analyses folder does not exist for given uri:" + corpusPath + " .");
		if (!analysesPath.isDirectory())
			throw new PepperModuleException(this, "Cannot import corpus, because the analyses folder for :" + corpusPath + " is not a folder.");

		for (File corpusResource : analysesPath.listFiles()) {// one folder as
																// super corpus
			SCorpus sCorpus = null;
			{// create SCorpus object
				sCorpus = SaltFactory.createSCorpus();
				sCorpus.setName(corpusResource.getAbsoluteFile().getName());
				sCorpusGraph.addNode(sCorpus);
			}// create SCorpus object

			for (File documentResource : corpusResource.listFiles()) {
				// each subfolder must be a document
				SDocument sDocument = SaltFactory.createSDocument();
				sDocument.setName(documentResource.getName());
				sCorpusGraph.addNode(sDocument);
				SCorpusDocumentRelation sCorpDocRel = SaltFactory.createSCorpusDocumentRelation();
				sCorpDocRel.setSource(sCorpus);
				sCorpDocRel.setTarget(sDocument);
				sCorpusGraph.addRelation(sCorpDocRel);

				getIdentifier2ResourceTable().put(sDocument.getIdentifier(), URI.createFileURI(documentResource.getAbsolutePath()));
			}// each subfolder must be a document
		}// one folder as super corpus
	}

	/**
	 * Creates a mapper of type {@link EXMARaLDA2SaltMapper}. {@inheritDoc
	 * PepperModule#createPepperMapper(Identifier)}
	 */
	@Override
	public PepperMapper createPepperMapper(Identifier Identifier) {
		UAM2SaltMapper mapper = new UAM2SaltMapper();
		mapper.setResourceOptions(getResourceOptions());
		return (mapper);
	}

	private Map<String, String> resourceOptions = null;

	public Map<String, String> getResourceOptions() {
		if (resourceOptions == null) {
			synchronized (this) {
				if (resourceOptions == null) {
					File path2Corpus = new File(this.getCorpusDesc().getCorpusPath().toFileString() + "/" + PATH_TO_TEXT);
					if (!path2Corpus.exists())
						throw new PepperModuleException(this, "Cannot import document, because path to corpus '" + path2Corpus.getAbsolutePath() + "' does not exist.");
					resourceOptions = new Hashtable<String, String>();
					resourceOptions.put(UAMResource.PROP_PATH_2_TEXT, path2Corpus.getAbsolutePath());
				}
			}
		}
		return resourceOptions;
	}
}
