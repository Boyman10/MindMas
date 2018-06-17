package test.com.oc.master.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import main.com.oc.master.utils.MyProperties;

class MyPropertiesTest {

	static final Logger LOGGER = LogManager.getLogger(MyPropertiesTest.class);

	@Test
	void testReadProperties() {

		MyProperties myProp = new MyProperties();
		myProp.readProperties();
		/**
		 * Reading properties file master.combo_size = 5 master.nb_colors = 10
		 */

		LOGGER.debug("Comparing property from file : master.combo_size with " + myProp.COMBO_SIZE);
		LOGGER.debug("Comparing property from file : master.nb_colors with " + myProp.NB_COLORS);

		assertTrue("Comparing combo size", myProp.COMBO_SIZE == 5);
		assertTrue("Comparing colors number", myProp.NB_COLORS == 10);

	}

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


		LOGGER.debug("Comparing property from file : master.combo_size with " + myProp.COMBO_SIZE);
		LOGGER.debug("Comparing property from file : master.nb_colors with " + myProp.NB_COLORS);

		assertTrue("Comparing combo size", myProp.COMBO_SIZE == 10);
		assertTrue("Comparing colors number", myProp.NB_COLORS == 6);

	}
}
