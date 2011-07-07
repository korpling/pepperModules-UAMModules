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
