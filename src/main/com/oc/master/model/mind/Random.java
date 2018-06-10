package main.com.oc.master.model.mind;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to set random combo Either color or numbers
 * 
 * @author boy
 * @version 1.0.1
 */
public class Random implements RandomInterface {

	/**
	 * Method to return random numbers depending on size of array
	 * 
	 * @param size
	 * @return combo
	 */
	static final Logger logger = LogManager.getLogger(Random.class);

	int size = 0;
	// Beware color names : W for White, B for Blue, O for Orange, Y for Yellow, G
	// for Green, I for Indigo, P for Pink, R for Red,
	// S for Silver, M for MAgenta, C for coffee
	public final static String vColors = "WBOYGIPRSMC";

	public Random(short size) {

		this.size = size;
	}

	public int[] getNumbers() {

		int[] combo = new int[size];

		// Generate a random number between 0 and 10 :
		for (int i = 0; i < size; i++) {
			combo[i] = (int) (Math.random() * 10);
		}

		logger.trace("Random number : " + Arrays.toString(combo));
		return combo;

	}

	public char[] getColors() {
		
		char[] combo = new char[size];

		// Generate a random number between 0 and 10 :
		for (int i = 0; i < size; i++) {
			combo[i] = (char) vColors.charAt((int) (Math.random() * vColors.length()));
		}

		logger.trace("Random color : " + Arrays.toString(combo));

		return combo;
	}
}
