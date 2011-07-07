package de.hu_berlin.german.korpling.saltnpepper.model.uam.exceptions;

public class UAMModelException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3621878724207639893L;

	public UAMModelException()
	{ super(); }
	
    public UAMModelException(String s)
    { super(s); }
    
	public UAMModelException(String s, Throwable ex)
	{super(s, ex); }
}
