package com.oc.master.model.mind;

public interface Combo {
	
	/**
	 * Method comparing submitted Try with the secret combo
	 * @return clue
	 */
	public char[] compareCombo(int[] combo) ;
}
