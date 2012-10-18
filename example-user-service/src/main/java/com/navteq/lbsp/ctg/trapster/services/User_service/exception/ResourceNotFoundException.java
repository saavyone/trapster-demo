package com.navteq.lbsp.ctg.trapster.services.User_service.exception;

import com.navteq.lbsp.ctg.trapster.services.User_service.mapper.ResourceNotFoundExceptionMapper;

/**
 * This is an example exception for demonstrating the JaxRS-exception mapping in
 * class {@linkplain ResourceNotFoundExceptionMapper}
 * 
 * @author w casperson
 * 
 */
public class ResourceNotFoundException extends Exception
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public ResourceNotFoundException()
	{
		super();
	}

	public ResourceNotFoundException(Exception exc)
	{
		super(exc);
	}

	public ResourceNotFoundException(String string)
	{
		super(string);
	}
}
