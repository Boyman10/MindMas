package test.com.oc.master.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import junit.framework.TestCase;
import main.com.oc.master.utils.MyProperties;

/**
 * JUnit test for properties file management
 * The rule is useful to get the current method being tested
 * @author bob
 */
class MyPropertiesTest extends TestCase {

	static final Logger LOGGER = LogManager.getLogger(MyPropertiesTest.class);
	
	private String lastMethod ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach //setUp
	public void beforeEach(TestInfo testInfo) {
		
		LOGGER.debug("-- Just testing : " + testInfo.getDisplayName());
	}

	/**
	 * Method after each test
	 */
	@AfterEach // tearDown
	public void afterEach(TestInfo testInfo)  {

		LOGGER.debug("-- Just tested : " + testInfo.getDisplayName());
	}

	@DisplayName("READ")
	@Test
	void testReadProperties() {

		MyProperties myProp = new MyProperties();
		myProp.readProperties();
		/**
		 * Reading properties file master.combo_size = 5 master.nb_colors = 10
		 */

		LOGGER.debug("Comparing property from file : master.combo_size with " + MyProperties.COMBO_SIZE);
		LOGGER.debug("Comparing property from file : master.nb_colors with " + MyProperties.NB_COLORS);

		assertTrue("Comparing combo size", MyProperties.COMBO_SIZE == 5);
		assertTrue("Comparing colors number", MyProperties.NB_COLORS == 10);
		
	}
	
	@DisplayName("UPDATE")
	@Test
	void testUpdateProperties() {

		MyProperties myProp = new MyProperties();
		myProp.readProperties();

		Map<String, Integer> hm = new HashMap<>();

		hm.put("master.combo_size", 10);
		hm.put("master.nb_colors", 6);

		try {
			myProp.updateProperties(hm);
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}

		/**
		 * Reading properties file master.combo_size = 5 master.nb_colors = 10
		 */
		myProp.readProperties();

		LOGGER.debug("Comparing property from file : master.combo_size with " + MyProperties.COMBO_SIZE);
		LOGGER.debug("Comparing property from file : master.nb_colors with " + MyProperties.NB_COLORS);

		assertTrue("Comparing combo size", MyProperties.COMBO_SIZE == 10);
		assertTrue("Comparing colors number", MyProperties.NB_COLORS == 6);

	}

	@DisplayName("PERSIST")
	@Test
	void testPersistUpdateProperties() {

		MyProperties myProp = new MyProperties();
		myProp.readProperties();

		Map<String, Integer> hm = new HashMap<>();

		hm.put("master.combo_size", 10);
		hm.put("master.nb_colors", 6);

		// Persist data to file :
		myProp.persistUpdateProperties(hm);

		/**
		 * Re Reading properties file master.combo_size = 5 master.nb_colors = 10
		 */
		MyProperties myPersistProp = new MyProperties();
		myPersistProp.readProperties();

		LOGGER.debug("Comparing property from file : master.combo_size with " + MyProperties.COMBO_SIZE);
		LOGGER.debug("Comparing property from file : master.nb_colors with " + MyProperties.NB_COLORS);

		assertTrue("Comparing combo size", MyProperties.COMBO_SIZE == 10);
		assertTrue("Comparing colors number", MyProperties.NB_COLORS == 6);
		

	}
}
