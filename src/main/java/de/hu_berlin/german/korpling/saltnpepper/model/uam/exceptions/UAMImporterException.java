package de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions;

import de.hu_berlin.german.korpling.saltnpepper.pepperModules.uamModules.exceptions.UAMModuleException;

public class UAMImporterException extends UAMModuleException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434917989646006935L;

	public UAMImporterException()
	{ super(); }
	
    public UAMImporterException(String s)
    { super(s); }
    
	public UAMImporterException(String s, Throwable ex)
	{super(s, ex); }
}
