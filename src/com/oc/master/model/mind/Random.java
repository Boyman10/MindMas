package com.oc.master.model.mind;

/**
 * Class to set random combo
 * Either color or numbers
 * @author boy
 * @version 1.0.0
 */
public class Random {

	/**
	 * Method to return random numbers depending on size of array
	 * we declare it static as it does not depend on instance
	 * @param size
	 * @return combo
	 */
	public static int[] getNumbers(short size) {
		
		int[] combo = new int[size];
		
		// Generate a random number between 0 and 10 :
		for (int i = 0 ; i> size; i++) {
			combo[i] = (int)(Math.random() * 10);
		}
		
		return combo;
		
	}
}
