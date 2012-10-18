package com.navteq.lbsp.ctg.trapster.services.User_service.mapper;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.navteq.lbsp.ctg.trapster.modules.commons.domain.TrapsterResponseType;
import com.navteq.lbsp.ctg.trapster.modules.commons.domain.TrapsterType;
import com.navteq.lbsp.ctg.trapster.services.User_service.exception.ResourceNotFoundException;

/**
 * This maps a resource not found java exception to a 404 HTTP status code
 * 
 * @author w casperson
 * 
 */
@Provider
@Named("resourceNotFoundExceptionMapper")
@Singleton
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException>
{
	private static final Log	logger	= LogFactory.getLog(ResourceNotFoundExceptionMapper.class);


	public Response toResponse(ResourceNotFoundException e)
	{
		TrapsterResponseType trapsterResponse= new TrapsterResponseType();
		trapsterResponse.setStatus("ERROR");
		trapsterResponse.setStatuscode(404);
		trapsterResponse.setMessage("Stack trace: " + e.getMessage());

		TrapsterType trapsterType= new TrapsterType();
		trapsterType.setResponse(trapsterResponse);
	
		logger.error("ResourceNotFoundException:"+ e.getMessage());

		return Response.ok().entity(trapsterType).build();
	}

}