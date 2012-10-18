/**
 * 
 */
package com.navteq.lbsp.ctg.trapster.modules.test_utils.easymock;

/**
 * @author fdavanzo
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.AbstractContextLoader;

import com.navteq.lbsp.ctg.trapster.modules.test_utils.easymock.EasyMockAwareEmbeddedSpringTestServerContextLoader;
import com.navteq.lbsp.ctg.trapster.modules.test_utils.server.EmbeddedSpringTestServer;


public final class EasyMockAwareEmbeddedSpringTestServerContextLoader extends AbstractContextLoader
{
	private static final Log						LOGGER							= LogFactory
																							.getLog(EasyMockAwareEmbeddedSpringTestServerContextLoader.class);

	private final EasyMockGenericXmlContextLoader	easyMockGenericXmlContextLoader	= new EasyMockGenericXmlContextLoader();
 
	
	public ApplicationContext loadContext(String... locations) throws SpringContextInitializationException
	{
		ConfigurableApplicationContext context= null;
		try
		{
			context= easyMockGenericXmlContextLoader.loadContext(locations);
		}
		catch (Exception e)
		{
			throw new SpringContextInitializationException(e.getMessage(), e);
		}
		try
		{
			EmbeddedSpringTestServer server= context
					.getBean(com.navteq.lbsp.ctg.trapster.modules.test_utils.server.EmbeddedSpringTestServer.class);

			ConfigurableListableBeanFactory factory= context.getBeanFactory();
			SpringBeanProcessor processor= new SpringBeanProcessor(server.getDeployment().getDispatcher(), server
					.getDeployment().getRegistry(), server.getDeployment().getProviderFactory());
			processor.postProcessBeanFactory(factory);
		}
		catch (NoSuchBeanDefinitionException e)
		{
			LOGGER.warn("Embedded server bean definition not found in the application context!: " + e.getMessage(), e);
		}
		return context;
	}

	@Override
	protected String[] modifyLocations(Class<?> clazz, String... locations)
	{
		return easyMockGenericXmlContextLoader.processLocations(clazz, locations);
	}

	@Override
	protected String getResourceSuffix()
	{
		return "-context.xml";
	}

	/**
	 * 
	 * @author sprozo
	 * 
	 */
	private static class SpringContextInitializationException extends Exception
	{

		/**
		 * 
		 */
		private static final long	serialVersionUID	= -3044233968086280913L;

		public SpringContextInitializationException(String message, Throwable cause)
		{
			super(message, cause);
		}

	}
}