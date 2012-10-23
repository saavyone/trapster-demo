package com.navteq.lbsp.ctg.trapster.services.User_service.resources;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.mail.internet.AddressException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.navteq.lbsp.ctg.trapster.modules.commons.domain.DataType;
import com.navteq.lbsp.ctg.trapster.modules.commons.domain.TrapsterResponseType;
import com.navteq.lbsp.ctg.trapster.modules.commons.domain.TrapsterType;
import com.navteq.lbsp.ctg.trapster.modules.udb.TrapsterUdbException;
import com.navteq.lbsp.ctg.trapster.modules.udb.domain.Udb;
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
	
		
	@Autowired
	private UdbDao udbDao;
	


	/**
	 * returns TrapsterType for existing user specified in query parameter
	 * as userid all that happens here is getting path/query parms into local
	 * vars, and then call a common get method getByUserId
	 * 
	 * @param userid
	 *            : key to udb table
	 */
	@GET
	@Path("/{userid}")
	public Response getUserUsingOnlyQueryParms(@PathParam("userid") String userid) throws ResourceNotFoundException {
		logger.debug("getUserUsingOnlyQueryParms(String userid) has been called with userid = " + userid);
		logger.debug("udbDao = " + udbDao);
		Udb user = udbDao.getByNameOrUname(userid);
		logger.debug("user = " + user);

		TrapsterType ttUser = new TrapsterType();
		ttUser.setResponse(convertUdbToTrapsterResponseType(user));
		logger.debug("ttUser = " + user);
		
		return Response.status(200).entity(ttUser).build();
	}


	/**
	 * creates a new user posted via XML/JSON and returns the resource location
	 * if succeeded
	 * 
	 * @param incomingRequest
	 *            POST request body
	 */
	@POST
	@Path("/{id}")
	public Response update(@PathParam("id") String id)
			throws ResourceNotFoundException,
			TrapsterUdbException, AddressException {
		

		logger.debug("update(String id) has been called");
		
		logger.debug("udbDao = " + udbDao);
		System.err.println(id);
		Udb user = udbDao.getByNameOrUname(id);
		logger.debug("user = " + user);
		if (user == null) throw new ResourceNotFoundException();

		TrapsterType ttUser = new TrapsterType();
		ttUser.setResponse(convertUdbToTrapsterResponseType(user));
		logger.debug("ttUser = " + user);
		
		return Response.status(200).entity(ttUser).build();
	}

		
	/**
	 * delete an existing user
	 * 
	 * @param userid
	 *            : key to udb table
	 * 
	 * @throws ResourceNotFoundException
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") @DefaultValue("0") String id)
			throws ResourceNotFoundException {
		logger.debug("delete(String id) has been called");
		
		logger.debug("udbDao = " + udbDao);
		Udb user = udbDao.getByNameOrUname(id);
		logger.debug("user = " + user);
		if (user == null) throw new ResourceNotFoundException();

		System.err.println("deleting user: " + id);
		udbDao.removeById(user.getId());

		TrapsterType ttUser = new TrapsterType();
		ttUser.setResponse(convertUdbToTrapsterResponseType(user));
		logger.debug("ttUser = " + user);
		
		return Response.status(200).entity(ttUser).build();
	}

	

	private TrapsterResponseType convertUdbToTrapsterResponseType(Udb user) {
		TrapsterResponseType response = new TrapsterResponseType();
		response.setData(new DataType());
		response.getData().setDisplayname(user.getName());
		response.getData().setEmailaddr(user.getUname());
		response.getData().setPwdusernamehash("xxxxxxxxxx");
		response.getData().setUsercredlevel(user.getLevel());
		response.getData().setInfo(user.getInfo());
		response.getData().setAlertradius(user.getAradius());
		response.getData().setAlert(user.getAlert());
		response.getData().setSignupepochtime(user.getSignupdate() == null ? MINIMUM_EPOCHTIME : user.getSignupdate().getTime());
		response.getData().setLastvoteepochtime(user.getLastvotetime() == null ? MINIMUM_EPOCHTIME : user.getLastvotetime().getTime());
		response.getData().setTotaluservotes(user.getNumvotes());
		response.getData().setKarma(user.getKarma());
		response.getData().setTosagreed(user.getAgreed());
		response.getData().setTrustedgrouppref(user.getTgpref());
		response.getData().setPrivmsgpref(user.getPmpref());
		response.getData().setSmsaddr(user.getPmsmsaddr());
		response.getData().setModeratorlevel(user.getGlobalModerator().toString());
		response.getData().setLoggedontositeflag(user.getLoggedSite());
		response.getData().setNewsletterflag(user.getNewsletter());
		response.getData().setUserstatus(user.getStatus());
		response.getData().setLastkarma(user.getLastkarma());
		response.getData().setEmailconfflag(user.getEmailConf());
		response.getData().setSipallowedflag(user.getSip());
		response.getData().setLoginfailures(user.getLoginfailures());
		response.getData().setModapplyepochtime(user.getModapplydate() == null ? MINIMUM_EPOCHTIME : user.getModapplydate().getTime());
		response.getData().setLastapiepochtime(user.getLastapitime() == null ? MINIMUM_EPOCHTIME : user.getLastapitime().getTime());
		response.getData().setLastwebepochtime(user.getLastwebtime() == null ? MINIMUM_EPOCHTIME : user.getLastwebtime().getTime());
		response.getData().setConfirmepochtime(user.getConfirmdate() == null ? MINIMUM_EPOCHTIME : user.getConfirmdate().getTime());
		response.getData().setConfirmmethod(user.getConfirmmethod());
		
		return response;
	}


	
}