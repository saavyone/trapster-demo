package com.navteq.lbsp.ctg.trapster.modules.test_utils.easymock;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;


/**
 * This class prepares the test spring context with mocked beans.
 * 
 * @author sprozo
 */

public final class EasyMockGenericXmlContextLoader extends GenericXmlContextLoader
{
	private static final Log					LOGGER		= LogFactory.getLog(EasyMockGenericXmlContextLoader.class);
	private final ThreadLocal<List<Class<?>>>	testClasses	= new ThreadLocal<List<Class<?>>>();

	
	protected void prepareContext(GenericApplicationContext context)
	{
		EasyMockBeanPostProcessor mockPostProcessor= new EasyMockBeanPostProcessor();

		for (Class<?> testClass : testClasses.get())
		{
			if (!mockPostProcessor.cachedMockDefinitions.containsKey(testClass))
			{
				mockPostProcessor.cachedMockDefinitions.put(testClass, new ArrayList<MockInjectionPoint>());
			}

			List<MockInjectionPoint> mockInjectionPoints= mockPostProcessor.cachedMockDefinitions.get(testClass);
			Field[] fields= testClass.getDeclaredFields();
			for (final Field field : fields)
			{
				// check if field is annotated by Environment Property
				if (field.isAnnotationPresent(EasyMockObject.class))
				{
					EasyMockObject mockAnnotation= field.getAnnotation(EasyMockObject.class);
					String mockBeanName= mockAnnotation.value();
					Class<?> mockClass= null;
					mockInjectionPoints.add(new MockInjectionPoint(field, mockBeanName));

					try
					{
						AccessController.doPrivileged(SetAccessiblePrivilegedAction.action(field));
						mockClass= field.getType();
					}
					catch (Exception e)
					{
						LOGGER.warn(e.getMessage(), e);
					}
					BeanDefinition df= BeanDefinitionBuilder.genericBeanDefinition(SpringBeanEasyMockFactory.class)
							.getBeanDefinition();
					df.setBeanClassName(SpringBeanEasyMockFactory.class.getCanonicalName());
					df.setFactoryMethodName("createMock");
					df.setPrimary(true);
					df.setScope(BeanDefinition.SCOPE_SINGLETON);
					df.getConstructorArgumentValues().addIndexedArgumentValue(0, mockClass);
					df.getConstructorArgumentValues().addIndexedArgumentValue(1, mockAnnotation.mockType());
					df.getConstructorArgumentValues().addIndexedArgumentValue(2, mockAnnotation.replay());
					context.registerBeanDefinition(mockBeanName, df);
				}
			}
		}
		context.getBeanFactory().addBeanPostProcessor(mockPostProcessor);
		mockPostProcessor.context= context;
		super.prepareContext(context);
	}

	@Override
	protected String[] modifyLocations(Class<?> clazz, String... locations)
	{
		if (testClasses.get() == null)
		{
			testClasses.set(new ArrayList<Class<?>>());
		}

		if (!testClasses.get().contains(clazz))
		{
			testClasses.get().add(clazz);
		}

		return super.modifyLocations(clazz, locations);
	}

	private static final class MockInjectionPoint
	{
		MockInjectionPoint(Field f, String mockBeanName)
		{
			this.f= f;
			this.mockBeanName= mockBeanName;
		}

		Field	f;
		String	mockBeanName;
	}

	/**
	 * @author sprozo
	 */
	private static final class EasyMockBeanPostProcessor implements BeanPostProcessor
	{
		final ConcurrentMap<Class<?>, List<MockInjectionPoint>>	cachedMockDefinitions	= new ConcurrentHashMap<Class<?>, List<MockInjectionPoint>>();
		private ApplicationContext								context;

		
		public Object postProcessBeforeInitialization(final Object bean, String beanName)
		{
			Class<?> beanClass= bean.getClass();
			if (cachedMockDefinitions.containsKey(beanClass))
			{
				for (final MockInjectionPoint mp : cachedMockDefinitions.get(beanClass))
				{
					AccessController.doPrivileged(SetAccessiblePrivilegedAction.action(mp.f));
					AccessController.doPrivileged(new PrivilegedAction<Object>()
					{

						
						public Object run()
						{
							Object obj= context.getBean(mp.mockBeanName);
							try
							{
								mp.f.set(bean, obj);
							}
							catch (IllegalAccessException e)
							{
								LOGGER.warn(e.getMessage(), e);
							}
							return null;
						}
					});
				}
			}
			return bean;
		}

		
		public Object postProcessAfterInitialization(Object bean, String beanName)
		{
			return bean;
		}
	}

	private static final class SetAccessiblePrivilegedAction implements PrivilegedAction<Object>
	{
		private final Field	field;

		public static SetAccessiblePrivilegedAction action(Field field)
		{
			return new SetAccessiblePrivilegedAction(field);
		}

		private SetAccessiblePrivilegedAction(Field field)
		{
			this.field= field;
		}

		
		public Object run()
		{
			field.setAccessible(true);
			return null;
		}
	}
}
