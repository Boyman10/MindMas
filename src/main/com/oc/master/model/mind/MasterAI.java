package main.com.oc.master.model.mind;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The real mind for playing defense and dual modes
 * @author Boyman10
 * @version 0.1.0
 */
public class MasterAI implements AI {
	
	static final Logger LOGGER = LogManager.getLogger(MasterAI.class);
	Level myLevel = Level.forName("NEW_LEVEL", 350);
	
	// play colors
	private ArrayList<Character> colors;
	private byte cSize;
	
	// Keeping track of game play
	private byte[][] badSpotColor; // good colors at wrong place
	
	// current found combo
	private char[] fCombo;

	
	// initialize colors array and combo size
	public MasterAI(byte nbColors, byte cSize) {
		
		// array of possible colors :
		String allColors = Random.vColors.substring(0, nbColors);
        
		for (int i = 0;i < allColors.length();i++){
            this.colors.add(allColors.charAt(i));
        }
        
		this.cSize = cSize;
		
		// initializing found combo - null values for the moment:
		fCombo = new char[cSize];
		
	}


	/**
	 * Making new move given a new clue
	 * @return
	 */
	@Override
	public char[] makeMove() {
		
		// fill the array with known colors :
		char[] move = this.fCombo;
		
		LOGGER.log(myLevel, "Current combo before new move : " + Arrays.toString(move));
		
		// now use good colors from wrong spot and increment by one to make a new move
		int k = 0;
		
		// till different than null character
		while(move[k] != '\u0000' && k < cSize) {
			
			
			
			
			k++;
		}
		
		
		for(int i = 0; i < badSpotColor.length; i++) {
			
		
			for(int j = 0; j < badSpotColor[i].length; j++) {
		
				
			}
		}
		
		return move;	
	}

	/**
	 * Generate the fCombo given a new clue after last move
	 * @param clue
	 */
	public void updateCombo(char[] clue) {
		
	}
	
}