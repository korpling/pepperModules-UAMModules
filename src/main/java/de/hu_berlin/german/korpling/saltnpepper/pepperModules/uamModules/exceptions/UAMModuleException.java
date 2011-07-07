package de.hu_berlin.german.korpling.saltnpepper.pepperModules.uamModules.exceptions;

import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperExceptions.PepperModuleException;

public class UAMModuleException extends PepperModuleException{
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 7152733787673679658L;

	public UAMModuleException()
	{ super(); }
	
    public UAMModuleException(String s)
    { super(s); }
    
	public UAMModuleException(String s, Throwable ex)
	{super(s, ex); }
}
