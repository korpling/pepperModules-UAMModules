package de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions;

import de.hu_berlin.german.korpling.saltnpepper.pepperModules.uamModules.exceptions.UAMModuleException;

public class UAMResourceException extends UAMModuleException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434917989646006935L;

	public UAMResourceException()
	{ super(); }
	
    public UAMResourceException(String s)
    { super(s); }
    
	public UAMResourceException(String s, Throwable ex)
	{super(s, ex); }
}
