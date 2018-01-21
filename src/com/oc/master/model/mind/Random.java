package com.oc.master.model.mind;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to set random combo
 * Either color or numbers
 * @author boy
 * @version 1.0.1
 */
public class Random {

	/**
	 * Method to return random numbers depending on size of array
	 * @param size
	 * @return combo
	 */
	static final Logger logger = LogManager.getLogger();

	int size = 0;
	
	public Random(short size) {
		
		this.size = size;
	}
	
	public int[] getNumbers() {
		
		int[] combo = new int[size];
		
		// Generate a random number between 0 and 10 :
		for (int i = 0 ; i< size; i++) {
			combo[i] = (int)(Math.random() * 10);
		}
		
		logger.trace("Random number : " + Arrays.toString(combo));
		return combo;
		
	}
}
