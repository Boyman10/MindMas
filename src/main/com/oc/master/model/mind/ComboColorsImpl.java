package main.com.oc.master.model.mind;

import java.util.Arrays;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * All about colors combo manipulations
 * @author Boyman10
 * @version 0.1.0
 */
public class ComboColorsImpl {

	static final Logger LOGGER = LogManager.getLogger(ComboColorsImpl.class);
	Level myLevel = Level.forName("NEW_LEVEL", 350);
	
	/**
	 * Comparing Color combo and providing clues
	 * @param source
	 * @param secret
	 * @return char[2]
	 */
	public char[] compareCombo(char[] source, char[] secret) {
		
		LOGGER.log(myLevel,
				"comparing source and secret " + Arrays.toString(source) + " target : " + Arrays.toString(secret));
		
		// storing the good spot indexes
		boolean[] good = new boolean[source.length];
		boolean[] bad = new boolean[source.length];
		
		// First retrieve good spots :
		for(int i = 0; i< source.length;i++) {
			
			if(source[i] == secret[i]) {
				good[i] = true;
			} else
				good[i] = false;
		}
		
		// Second pass where we check the bad spots for found ones :
		for(int i = 0; i< source.length;i++) {
			
			bad[i] = false;
			// continue if not found yet
			if(!good[i]) {
				// browse the target avoiding the good indexes
				for(int j = 0; j < secret.length;j++) {
					if (!good[j])
						if (source[i] == secret[j])
							bad[i] = true;
				}
			}
		}		
		
		int badS = 0, goodS = 0;
		
		for(int i = 0; i< source.length;i++) {
		
			if(good[i])
				goodS++;
			if(bad[i])
				badS++;
		}
		
		int REDIX=10;// redix 10 is for decimal number, for hexa use redix 16 
		
		char[] result = new char[2];
		result[0] = Character.forDigit(goodS,REDIX);   
		result[1] = Character.forDigit(badS,REDIX);   
		
		LOGGER.log(myLevel, "Here are the clues " + Arrays.toString(result));
		
		return result;
		
	}
}
