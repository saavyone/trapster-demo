package com.navteq.lbsp.ctg.trapster.modules.test_utils.easymock;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.internal.MocksControl.MockType;


/**
 * @author sprozo
 */
public final class SpringBeanEasyMockFactory
{

	private SpringBeanEasyMockFactory()
	{
		// singleton
	}

	/**
	 * @param toMock
	 *            - the class or interface which should be mocked
	 * @param mockType
	 *            - the {@link MockType}
	 * @param replay
	 *            - true if the mock should be replayed, false if not
	 * @return the mocked objecd
	 */
	public static Object createMock(Class<?> toMock, MockType mockType, boolean replay)
	{
		IMocksControl control= null;
		EasyMock.createNiceControl();
		switch (mockType)
		{
		case NICE:
			control= EasyMock.createNiceControl();
			break;
		case STRICT:
			control= EasyMock.createStrictControl();
			break;
		default:
			control= EasyMock.createControl();
		}
		Object mock= control.createMock(toMock);
		if (replay)
		{
			control.replay();
		}
		return mock;
	}
}
