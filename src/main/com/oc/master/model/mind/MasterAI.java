package main.com.oc.master.model.mind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The real mind for playing defense and dual modes
 * 
 * @author Boyman10
 * @version 0.1.0
 */
public class MasterAI implements AI {

	static final Logger LOGGER = LogManager.getLogger(MasterAI.class);
	Level myLevel = Level.forName("NEW_LEVEL", 350);

	// colors still in play
	private ArrayList<Character> colors;
	private byte cSize, nGoodOnes, nBadOnes, nColors;
	
	// color in play
	private Character currentColor;	
	
	// Keeping track of game play - color index with index of spot searches so far
	private ArrayList<Character> goodColors = new ArrayList<>();
	private ArrayList<byte[]> indexGoodColors = new ArrayList<>();
	
	private Character[] foundColors ;

	// colors reference
	private String allColors;

	/**
	 * Initialize colors array and combo size
	 * 
	 * @param nbColors
	 * @param cSize
	 */
	public MasterAI(byte nbColors, byte cSize) {

		this.colors = new ArrayList<>();
		// array of possible colors :
		allColors = Random.vColors.substring(0, nbColors);
		LOGGER.log(myLevel, "Colors : " + allColors);
		
		for (int i = 0; i < allColors.length(); i++) {
			this.colors.add(allColors.charAt(i));
		}

		this.cSize = cSize;

		// initializing data
		foundColors = new Character[cSize];
		nGoodOnes = 0;
		nBadOnes = 0;


	}

	/**
	 * Making new move given a new clue
	 * 
	 * @param char[2]
	 *            clue
	 * @return
	 */
	@Override
	public char[] makeMove(char[] clue) {

		char[] move = new char[cSize];

		// case First move :
		if (this.currentColor == '\u0000') {
			// fill with 1 color :
			for (int j = 0; j < cSize; j++)
				move[j] = this.colors.get(0);

			// This color is being played
			this.currentColor = this.colors.get(0);
			this.colors.remove(0);

			LOGGER.log(myLevel, "Current generated move : " + Arrays.toString(move));
			
			return move;
		}

		// Nb of good clue :
		byte nbGood = (byte) Integer.parseInt(Character.toString(clue[0]));
		// Nb of bad ones clue :
		byte nbBad = (byte) Integer.parseInt(Character.toString(clue[1]));
		
		// Compare clues nb and nb of colors played so far
		if ((nbBad + nbGood) ==  (goodColors.size() + foundColors.length + 1)) {
			
			/**
			 * Case all colors are good - add the current one to the list of good ones and
			 * index it based on free spots
			 */
			
			
			
			
		} else if ((nbBad + nbGood) <  (goodColors.size() + foundColors.length + 1)) {
			
			/**
			 * Case clues < nb colors - we remove the current color and apply next move
			 */
			
			this.colors.remove(0);
			this.currentColor = this.colors.get(0);
			
			generateMove();
			
		} else if ((nbBad + nbGood) >  (goodColors.size() + foundColors.length + 1)) {
			
			/**
			 * Case clues > nb colors - we do have duplicate color for the last one !
			 */
			
			
			
		}
		
		
		
		
		
		
		
		// We compare number good Spots to the new submitted clue :
		if (nGoodOnes < nbGood) {
			
			// Case new color played lead to new good spot then last color is at the right spot(s)
			if (playedSpotColor.size() > nbGood) {
				
				// Beware multiple times the same color :
				if(playedSpotColor.size() - nbGood == 1) {
					
					for (int i = 0; i < (nbGood - nGoodOnes);i++ ) {
						foundSpotColor.put(Character.toString(this.colors.get(0)),(Integer)(new String(checkedCombo)).indexOf(this.colors.get(0)));
						playedSpotColor.remove(Character.toString(this.colors.get(0)));
					}
				}
				
			}

			// First fill in the last entries :
			for (k = 0; k < nGoodOnes + 1; k++)
				move[k] = checkedCombo[k];

			// iterate over number of new Good spots (case same color several times !)
			for (int j = 0; j < (nbGood - nGoodOnes); j++) {
				nGoodOnes++;

				// we add this color to the combo :
				checkedCombo[k++] = this.colors.get(0);
				
				

			}

			// remove the color from the still playing
			colors.remove(0);

			// now padding to the end with next color :
			for (k = nGoodOnes; k < cSize; k++)
				move[k] = this.colors.get(0);
			
			// This color is being played 
			playedSpotColor.put(Character.toString(this.colors.get(0)), -1);

		} else if (nGoodOnes == nbGood) {
			
				
			// Compare nb of distinct colors :
			if (playedSpotColor.size() > nbGood) {
				
				// more colors than the good spots 
				
				// Do we have bad spotted colors ?
				if (nBadOnes > 0) {
					
					// case no more color added yet :
					if (nbBad == nBadOnes ) {
						
						
						
					} else {
						
						nBadOnes++;
						
						
					}
					
					
				} else {
					
					// The good ones are at the right place
					for (k = 0; k < nGoodOnes + 1; k++)
						move[k] = checkedCombo[k];
					
					// Remove latest color from possible spots :
					playedSpotColor.remove(Character.toString(this.colors.get(0)));
					
					// remove the last submitted color :
					colors.remove(0);
					
					// now padding to the end with next color :
					for (k = nGoodOnes; k < cSize; k++)
						move[k] = this.colors.get(0);
					
					// This last color is being played 
					playedSpotColor.put(Character.toString(this.colors.get(0)), -1);
					
				}
				
			}
			
			
		} else {
			// Case one less good spot :
			if ((nGoodOnes - nbGood) == nbBad) {
				// the last checked color is not present :
				playedSpotColor.remove(Character.toString(this.colors.get(0)));
				colors.remove(0);
				
				nGoodOnes --;
				nBadOnes++;
				
			}
			
			
		}

		LOGGER.log(myLevel, "Current combo move : " + Arrays.toString(move));
		
		return move;
	}

	/**
	 * Generating move using current Color, goodColors and indexes of GoodColors
	 */
	private char[] generateMove() {

		char[] move = new char[cSize];
		
		for(int i = 0; i< cSize; i++) {
			
			// If we have a known color - use it
			if (foundColors[i] != '\u0000') {
				move[i] = foundColors[i];
			} else {
				
				int k = 0;
				boolean found = false;
				
				// iterate over goodColors
				for(byte[] arrIndex: indexGoodColors) {

					// We do have a color here
					if(arrIndex[i] == 3 || arrIndex[i] == 1) {
						move[i] = goodColors.get(k); 
						found = true;
					}

					k++;

				}
				
				if (!found) {
					move[i] = this.currentColor;
				}
				
			}
			
		}
		
		return move;
		
		
	}


}
