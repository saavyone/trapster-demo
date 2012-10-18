package com.navteq.lbsp.ctg.trapster.modules.test_utils.easymock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.easymock.EasyMock;

/**
 * This annotation specifies in a JUnit test case running with the
 * {@code SpringJUnit4ClassRunner}. The Spring context have to be loaded by a
 * {@code EasyMockGenericXmlContextLoader} instance:
 * <p/>
 * - which Spring bean (beanName) should be mocked<br>
 * - which the kind of mock should be created<br>
 * - the mock instance should be replayed<br>
 * <p/>
 * <b>Sample Usage:</b><br/>
 * <code>
 * <pre>
 * &#64;RunWith(SpringJUnit4ClassRunner.class)
 * &#64;ContextConfiguration(locations= {"classpath:spring-nvtsfw-test-context.xml"}, loader= EasyMockGenericXmlContextLoader.class)
 * public class MyTestClass
 * {
 * ...
 *    &#64;EasyMockObject(value="myComponent", mockType=MockType.DEFAULT, replay=true)
 *    private MyComponentInterface myComponent;
 * ...
 * 
 * </pre>
 * </code>
 * <p/>
 * 
 * @author sprozo
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface EasyMockObject
{
	/**
	 * Defines the bean name of an existing bean in a given Spring context which
	 * should be mocked
	 * 
	 * @return the name of the spring bean
	 */
	String value();

	/**
	 * Defines the mock instance type.
	 * 
	 * @return {@link MockType}
	 */
	MockType mockType() default MockType.DEFAULT;

	/**
	 * Defines
	 * 
	 * @return true or false
	 */
	boolean replay() default true;

	/**
	 * The mock instance type.
	 * 
	 * @author sprozo
	 * 
	 */
	public static enum MockType
	{
		/**
		 * indicates that the created mock should be a default mock,
		 * 
		 * @see {@link EasyMock#createMock(Class)}
		 */
		DEFAULT,
		/**
		 * indicates that the mock should be a nice mock
		 * 
		 * @see {@link EasyMock#createNiceMock(Class)}
		 */
		NICE,
		/**
		 * indicates that the mock should be a strict mock
		 * 
		 * @see {@link EasyMock#createStrictMock(Class)}
		 */
		STRICT;
	}
}
