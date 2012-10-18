package com.navteq.lbsp.ctg.trapster.services.User_service.resources;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.navteq.lbsp.ctg.trapster.modules.commons.domain.DataType;
import com.navteq.lbsp.ctg.trapster.modules.commons.domain.ParamsType;
import com.navteq.lbsp.ctg.trapster.modules.commons.domain.TrapsterRequestType;
import com.navteq.lbsp.ctg.trapster.modules.commons.domain.TrapsterType;
import com.navteq.lbsp.ctg.trapster.modules.test_utils.easymock.EasyMockAwareEmbeddedSpringTestServerContextLoader;

//@DirtiesContext(classMode= ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-app-test-context.xml" }, loader = EasyMockAwareEmbeddedSpringTestServerContextLoader.class)
public class UserResourceTest {

	private static final String SRV_URI = "http://localhost:3001/User-service-example/2.0/user";

	private final static Log LOGGER = LogFactory.getLog(UserResourceTest.class);

	private static int testUserId = 0;

	private TrapsterType testPayload = null;

	@Before
	private void setUp() {
		// set up user info in request
		TrapsterRequestType requestBody = new TrapsterRequestType();
		requestBody.setParams(new ParamsType());
		requestBody.getParams().setLogin("testuser.junit@trapster.com");
		requestBody.getParams().setDisplayname("TestUserJunit");
		requestBody.getParams().setEmailaddr("testuser.junit@trapster.com");
		requestBody.getParams().setPwdusernamehash("xxxxxxxxxx");
		requestBody.getParams().setPwdemailhash("xxxxxxxxxx");
		requestBody.getParams().setUsercredlevel(new Integer("9"));
		requestBody.getParams().setInfo("newInfo");
		requestBody.getParams().setAlertradius(.03);
		requestBody.getParams().setAlert("newAlert");
		requestBody.getParams().setSignupepochtime(0l);
		requestBody.getParams().setLastvoteepochtime(0l);
		requestBody.getParams().setTotaluservotes(new Integer("5"));
		requestBody.getParams().setKarma(new Integer("9"));
		requestBody.getParams().setTosagreed("Y");
		requestBody.getParams().setTrustedgrouppref("U");
		requestBody.getParams().setPrivmsgpref("YN");
		requestBody.getParams().setSmsaddr("555555555@att.txt");
		requestBody.getParams().setModeratorlevel("7");
		requestBody.getParams().setLoggedontositeflag("Y");
		requestBody.getParams().setNewsletterflag("Y");
		requestBody.getParams().setUserstatus("A");
		requestBody.getParams().setLastkarma(new Integer("7"));
		requestBody.getParams().setEmailconfflag("Y");
		requestBody.getParams().setSipallowedflag("Y");
		requestBody.getParams().setLoginfailures(new Integer("7"));
		requestBody.getParams().setModapplyepochtime(0L);
		requestBody.getParams().setLastapiepochtime(0L);
		requestBody.getParams().setLastwebepochtime(0L);
		requestBody.getParams().setConfirmepochtime(0L);
		requestBody.getParams().setConfirmmethod("M");
		requestBody.getParams().setInfo("this is what i really call a message");

		// get the body into the req/resp struct
		this.testPayload = new TrapsterType();
		this.testPayload.setRequest(requestBody);
	}

	
	@Test
	/**
	 * DISPLAYNAME is based on udb.name
	 * 
	 * @throws Exception
	 */
	public void testGETbyDISPLAYNAME() throws Exception {

		// TODO build and execute request
		ClientRequest request = new ClientRequest(SRV_URI);
		request.accept(MediaType.APPLICATION_XML);

		// TODO test get response
		ClientResponse<TrapsterType> response = request.get(TrapsterType.class);
		TrapsterType TrapsterType = response.getEntity();
		response.releaseConnection();

	}

	@Test
	public void testPOST() throws Exception {

		// TODO test POST in XML

		// build and execute request
		ClientRequest request = new ClientRequest(SRV_URI);
		request.accept(MediaType.APPLICATION_XML);

		// get response
		ClientResponse<TrapsterType> response = request
				.post(TrapsterType.class);

		// get what you need out of the response and release the connection
		TrapsterType trapsterType = (TrapsterType) response.getEntity();
		response.releaseConnection();

		// TODO check database for new record

	}

	@Test
	public void testGet() throws Exception {

		// build and execute request
		ClientRequest request = new ClientRequest(SRV_URI);
		request.accept(MediaType.APPLICATION_XML);

		// get response
		ClientResponse<TrapsterType> response = request.get(TrapsterType.class);
		TrapsterType trapsterResponseType = response.getEntity();
		response.releaseConnection();

	
		Assert.assertEquals("Status code was:"
				+ trapsterResponseType.getResponse().getStatuscode(), 301,
				trapsterResponseType.getResponse().getStatuscode());

	}

	@Test
	public void testDelete() throws Exception {
		ClientRequest request = new ClientRequest(SRV_URI + "/" + testUserId);

		// TODO test delete response
		ClientResponse<TrapsterType> response = request
				.delete(TrapsterType.class);
		TrapsterType TrapsterType = response.getEntity();
		response.releaseConnection();

	}

	private void logResponseBodyInfo(String whoCalled, TrapsterType responseBody) {
		// get out info from response for assertions
		String requestStatus = responseBody.getResponse().getStatus();
		String requestResultMsg = responseBody.getResponse().getMessage();
		DataType userInfo = responseBody.getResponse().getData();

		// show something you got back in the xml
		LOGGER.debug(whoCalled + " : response body values: ");
		LOGGER.debug(whoCalled + " : requestResultStatus = " + requestStatus);
		LOGGER.debug(whoCalled + " : requestResultMsg    = " + requestResultMsg);

		if (userInfo != null) {
			LOGGER.debug(whoCalled + " : response userInfo: ");
			LOGGER.debug(whoCalled + " : id                  = "
					+ userInfo.getId());
			LOGGER.debug(whoCalled + " : emailaddr           = "
					+ userInfo.getEmailaddr());
			LOGGER.debug(whoCalled + " : displayname         = "
					+ userInfo.getDisplayname());
			LOGGER.debug(whoCalled + " : usercredlevel       = "
					+ userInfo.getUsercredlevel());
			LOGGER.debug(whoCalled + " : info                = "
					+ userInfo.getInfo());
			LOGGER.debug(whoCalled + " : alertradius         = "
					+ userInfo.getAlertradius());
			LOGGER.debug(whoCalled + " : alert               = "
					+ userInfo.getAlert());
			LOGGER.debug(whoCalled + " : signupdate          = "
					+ new Date(userInfo.getSignupepochtime()).toString());
			LOGGER.debug(whoCalled + " : lastvotetime        = "
					+ new Date(userInfo.getLastvoteepochtime()).toString());
			LOGGER.debug(whoCalled + " : totaluservotes      = "
					+ userInfo.getTotaluservotes());
			LOGGER.debug(whoCalled + " : karma               = "
					+ userInfo.getKarma());
			LOGGER.debug(whoCalled + " : tosagreed           = "
					+ userInfo.getTosagreed());
			LOGGER.debug(whoCalled + " : trustedgrouppref    = "
					+ userInfo.getTrustedgrouppref());
			LOGGER.debug(whoCalled + " : privmsgpref         = "
					+ userInfo.getPrivmsgpref());
			LOGGER.debug(whoCalled + " : pmsmsaddr           = "
					+ userInfo.getSmsaddr());
			LOGGER.debug(whoCalled + " : moderatorlevel      = "
					+ userInfo.getModeratorlevel());
			LOGGER.debug(whoCalled + " : loggedsite          = "
					+ userInfo.getLoggedontositeflag());
			LOGGER.debug(whoCalled + " : newsletter          = "
					+ userInfo.getNewsletterflag());
			LOGGER.debug(whoCalled + " : emailconf           = "
					+ userInfo.getEmailconfflag());
			LOGGER.debug(whoCalled + " : status              = "
					+ userInfo.getUserstatus());
			LOGGER.debug(whoCalled + " : lastkarma           = "
					+ userInfo.getLastkarma());
			LOGGER.debug(whoCalled + " : sip                 = "
					+ userInfo.getSipallowedflag());
			LOGGER.debug(whoCalled + " : modapplydate        = "
					+ new Date(userInfo.getModapplyepochtime()).toString());
			LOGGER.debug(whoCalled + " : lastapitime         = "
					+ new Date(userInfo.getLastapiepochtime()).toString());
			LOGGER.debug(whoCalled + " : lastwebtime         = "
					+ new Date(userInfo.getLastwebepochtime()).toString());
			LOGGER.debug(whoCalled + " : confirmmethod       = "
					+ userInfo.getConfirmmethod());
		}
		return;
	}

}
