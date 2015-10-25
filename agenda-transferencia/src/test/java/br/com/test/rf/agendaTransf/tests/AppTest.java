package br.com.test.rf.agendaTransf.tests;

import java.util.Calendar;

import br.com.test.rf.agendaTransf.util.CalendarUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Calendar d1 = CalendarUtil.getCalendar(2015, 10, 1);
		Calendar d2 = CalendarUtil.getCalendar(2015, 10, 5);
		int days = CalendarUtil.getDaysBetween(d1, d2);
		System.out.println(days);
		assertTrue(true);
		
	}
	
	/**
	 * Rigourous Test :-)
	 */
	public void testApp2() {
		assertFalse(false);
	}
	
	/**
	 * Rigourous Test :-)
	 */
	public void bla() {
		assertFalse(false);
	}
}
