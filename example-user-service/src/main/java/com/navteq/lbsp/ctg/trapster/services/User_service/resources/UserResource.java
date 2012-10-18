package com.navteq.lbsp.ctg.trapster.services.User_service.resources;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.mail.internet.AddressException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.navteq.lbsp.ctg.trapster.modules.udb.TrapsterUdbException;
import com.navteq.lbsp.ctg.trapster.modules.udb.domain.UdbDao;
import com.navteq.lbsp.ctg.trapster.services.User_service.exception.ResourceNotFoundException;


/**
 * This is an example resource CRUD resource for demonstration purposes.
 * 
 * 
 */

@Singleton
@Named("userResource")
@Path("/2.0/user")
public class UserResource {
	
	private final static Log logger = LogFactory.getLog(UserResource.class);
	private final static long MINIMUM_EPOCHTIME = 28800000L; // used for setting
	
		
	//TODO  using example.module-udb as DAO layer, wire in access
	private UdbDao udbDao;
	


	/**
	 * returns TrapsterType for existing user specified in query parameter
	 * as userid all that happens here is getting path/query parms into local
	 * vars, and then call a common get method getByUserId
	 * 
	 * @param userid
	 *            : key to udb table
	 */
	public Response getUserUsingOnlyQueryParms(
			)
			throws ResourceNotFoundException {
		
		return null;
	}




	/**
	 * creates a new user posted via XML/JSON and returns the resource location
	 * if succeeded
	 * 
	 * @param incomingRequest
	 *            POST request body
	 */
	public Response update(
			)
			throws ResourceNotFoundException,
			TrapsterUdbException, AddressException {
		

		return null;
	}

		
	/**
	 * delete an existing user
	 * 
	 * @param userid
	 *            : key to udb table
	 * 
	 * @throws ResourceNotFoundException
	 */
	public Response delete(
			) throws ResourceNotFoundException {
		
		return null;

	}

	


	
}