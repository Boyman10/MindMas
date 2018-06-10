package main.com.oc.master.model.mind;

import java.util.Arrays;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class defining the behaviors for various methods related to combo with numbers only
 * @author admin
 * @version 0.1.1
 */
public class ComboNumbersImpl implements Combo {

	static final Logger LOGGER = LogManager.getLogger(ComboNumbersImpl.class);
	Level myLevel = Level.forName("NEW_LEVEL", 350);
	
	/**
	 * Generate clues after comparing source and target/secret
	 */
	@Override
	public char[] compareCombo(int[] source, int[] secret) {

		
		LOGGER.log(myLevel,"comparing source and secret " + Arrays.toString(source) + " target : " + Arrays.toString(secret));
		
		if (source.length != secret.length )
			return null;
		
		char[] clues = new char[source.length];
		
		for(int i=0; i < source.length; i++) {
			
			if (source[i] == secret[i])
				clues[i] = '=';
			else if(source[i] < secret[i])
				clues[i] = '-';
			else
				clues[i] = '+';
		}
		
		LOGGER.log(myLevel,"Here are the clues " + Arrays.toString(clues));
		
		return clues;
	}

}
