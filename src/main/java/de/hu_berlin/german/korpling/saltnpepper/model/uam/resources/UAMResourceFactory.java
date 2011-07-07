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
package de.hu_berlin.german.korpling.saltnpepper.model.uam.resources;

import java.util.Hashtable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

public class UAMResourceFactory extends ResourceFactoryImpl{

	private Hashtable<URI, Resource> uri2Resource= null;
	public Resource createResource(URI uri)
	{
		if (this.uri2Resource== null)
		{
			synchronized (this) {
				if (this.uri2Resource== null)
					this.uri2Resource= new Hashtable<URI, Resource>();
			}
		}
		Resource resource= null;
		if (uri2Resource.containsKey(uri))
			resource= uri2Resource.get(uri);
		else
		{
			resource=new UAMResource();
			resource.setURI(uri);
		}
		
		return(resource);
	}
}
