package com.oc.master.model.mind;

import java.util.ArrayList;

import com.oc.master.model.mind.exception.ComboException;

/**
 * Class to handle the user general behaviors
 * @author boy
 * @version 1.0.0
 */
public abstract class User {

	// Chosen combo (case defense & challenger as defender)
	private int[] secretCombo;
	// List of tries (case of attack)
	private ArrayList<int[]> tries;
	
	/**
	 * List of past clues
	 * Search+/- type is as : {"+","-",...}
	 * Master Colors is as : {"2","3"} good spots, bad spots
	 */
	private ArrayList<char[]> clues;
	
	/**
	 * Class constructor passing variables and initializing data
	 * @param secret
	 */
	public User(int[] secret) {
		 
		secretCombo = secret;
		tries = new ArrayList<int[]>();
		clues = new ArrayList<char[]>();
		
	}
	
	/**
	 * Method to add a clue to the list
	 * @param clue
	 * @throws ComboException
	 */
	public void addClue(char[] clue)  throws ComboException {
		
		if (clue.length != secretCombo.length)
			throw new ComboException("Problem with lenght of submitted clue");
		else
			this.clues.add(clue);
	}
	
	
}
