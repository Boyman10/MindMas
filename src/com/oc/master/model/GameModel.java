package com.oc.master.model;

import java.util.ArrayList;

import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;

/**
 * Model for the Games - Handling Data here
 * @author boy
 * @version 1.0.1
 */
public class GameModel implements GameObservable {

	private ArrayList<GameObserver> listObserver = new ArrayList<GameObserver>();
	
	// Mastermind or Search
	private GameType typeGame;
	// Attacker , Defense, Challenger
	private GameMode modeGame;
	
	/**
	 * Class constructor initializing GameMode and GameType
	 * @param typeGame, modeGame
	 */
	public GameModel(GameType type, GameMode mode) {
		
		this.typeGame = type;
		this.modeGame = mode;
		
	}
	
	/**
	 * Method to assign proper data to deal with
	 * @param vars
	 */
	public void assign(int[] vars) {
		
		// Here we will launch the calculation from the computer depending on the Game mode :
		
		/**
		 * Case Attacker - The computer will compare its combo with the user try
		 */
		
		/**
		 * Case Defender (user) - the user/automatic part will compare its combo with computer try
		 */
		
		
		// notify short historic panel with clues and main panel
		
		this.notifyObserver();
	}

	@Override
	public void addObserver(GameObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {

		// One Observer here is the ColorSelectorPanel

		
		
		
		
	}

	@Override
	public void restartObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionObserver(String method) {
		// TODO Auto-generated method stub
		
	}
	

	
}
