package test.com.oc.master;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.com.oc.master.model.mind.Random;

/**
 * @author bob
 * My first JUnit test for Random class
 */
class RandomTest {

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
		
		
		
	}

	/**
	 * Test method for {@link main.com.oc.master.model.mind.Random#getColors()}.
	 */
	@Test
	void testGetColors() {
		fail("Not yet implemented");
	}

}
