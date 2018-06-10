package test.com.oc.master;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import main.com.oc.master.model.mind.Random;

/**
 * @author bob
 * My first JUnit test for Random class
 */
class RandomTest {
	
	static final Logger LOGGER = LogManager.getLogger(RandomTest.class);

	/**
	 * Test method for {@link main.com.oc.master.model.mind.Random#getNumbers()}.
	 */
	@Test
	void testGetNumbers() {
		
		// Test with size of 5
		Random myRandom = new Random((short) 5);
		
		// verify size of generated array and check the difference with each call
		int[] vRandom = myRandom.getNumbers();
		int[] vRandom1 = myRandom.getNumbers();
		int[] vRandom2 = myRandom.getNumbers();
		int[] vRandom3 = myRandom.getNumbers();
		int[] vRandom4 = myRandom.getNumbers();
		
		if (vRandom.length != 5 || vRandom1.length != 5 || vRandom2.length != 5 || vRandom3.length != 5 || vRandom4.length != 5)
			fail("Length not valid");
		
		LOGGER.debug("Ok done with random numbers");
		
	}

	/**
	 * Test method for {@link main.com.oc.master.model.mind.Random#getColors()}.
	 */
	@Test
	void testGetColors() {
		// Test with size of 5
		Random myRandom = new Random((short) 5);
		
		// verify size of generated array and check the difference with each call
		char[] vRandom = myRandom.getColors();
		char[] vRandom1 = myRandom.getColors();
		char[] vRandom2 = myRandom.getColors();
		char[] vRandom3 = myRandom.getColors();
		char[] vRandom4 = myRandom.getColors();
		
		if (vRandom.length != 5 || vRandom1.length != 5 || vRandom2.length != 5 || vRandom3.length != 5 || vRandom4.length != 5)
			fail("Length not valid");
		
		LOGGER.debug("Ok done with random colors");	}

}
