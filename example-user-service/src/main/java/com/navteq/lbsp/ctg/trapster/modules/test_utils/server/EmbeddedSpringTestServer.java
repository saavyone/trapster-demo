package com.navteq.lbsp.ctg.trapster.modules.test_utils.server;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;

/**
 * 
 * @author sprozo
 * 
 */
public class EmbeddedSpringTestServer extends TJWSEmbeddedJaxrsServer
{

	/**
	 * provide a setter method matching to {@link #getPort()} otherwise we can
	 * not set the port via Spring XML
	 * 
	 * @param port
	 *            the port number as String
	 */
	public final void setPort(String port)
	{
		int p= Integer.parseInt(port);
		super.setPort(p);
	}

	@Override
	public void stop()
	{
		deployment.stop();
		servlet.destroy();
		super.stop();
	}
}
