package main.com.oc.master.model.mind;

import java.util.ArrayList;

/**
 * The real mind for playing defense and dual modes
 * @author Boyman10
 * @version 0.1.0
 */
public class MasterAI implements AI {
	
	// play colors
	private char[] colors;
	private byte cSize;
	
	// Keeping track of game play
	private ArrayList<Integer> badColors, goodColors;
	private boolean[] goodSpots;

	
	// initialize colors array and combo size
	public MasterAI(byte nbColors, byte cSize) {
		
		this.colors = Random.vColors.substring(0, nbColors).toCharArray();
		this.cSize = cSize;
		
		// nothing yet, we initialize the spots - no value for now
		goodSpots = new boolean[cSize];
	}


	/**
	 * Making new move given a new clue
	 * @return
	 */
	@Override
	public char[] makeMove() {
		
		char[] move = new char[cSize];
		
		
		return move;	
	}

	
}
