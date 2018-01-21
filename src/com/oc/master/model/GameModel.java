package com.oc.master.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.mind.Random;
import com.oc.master.model.mind.User;
import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;

/**
 * Model for the Games - Handling Data here
 * @author boy
 * @version 1.0.1
 */
public class GameModel implements GameObservable {

	static final Logger logger = LogManager.getLogger("GameModel");
	
	private ArrayList<GameObserver> listObserver = new ArrayList<GameObserver>();
	
	// Master mind or Search
	private GameType typeGame;
	// Attacker , Defense, Challenger
	private GameMode modeGame;
	
	// room for 2 users here : User & Computer
	private User[] players;
	
	/**
	 * Class constructor initializing GameMode and GameType
	 * @param typeGame, modeGame
	 */
	public GameModel(GameType type, GameMode mode) {
		
		this.typeGame = type;
		this.modeGame = mode;
		
		// Define number of players : User & Computer
		players = new User[2];
		
	}
	
	/**
	 * Method to assign proper data to deal with
	 * @param vars
	 */
	public void assign(int[] vars) {
		
		/** 
		* Here we will launch the calculation from the computer depending on the Game mode :
		* Players instantiation
		*/
		
		switch(this.modeGame) {
		/**
		 * Case Attacker - The computer will compare its combo with the user try
		 */		
		case ATTACK :
			players[0] = new User(null); // The user is Attacking - no secret combo for him
			players[1] = new User(Random.getNumbers(GameModel.MAX_DIGITS)); // Computer here
			break;
			
		/**
		 * Case Defender (user) - the user/automatic part will compare its combo with computer try
		 */		
		case DEFENSE:
			players[0] = new User(vars); // The user is defending - secret combo here
			players[1] = new User(null); // Computer here
			
		case CHALLENGE:
			players[0] = new User(vars); // The user is defending - secret combo here
			players[1] = new User(Random.getNumbers(GameModel.MAX_DIGITS)); // Computer here	
			
			
		default:
			logger.debug("Wrong mode for the game or empty mode !");
			break;
		
		}
		

		
		
		// notify short historic panel with clues and main panel
		
		this.notifyObserver();
	}

	@Override
	public void addObserver(GameObserver obs) {
		// TODO Auto-generated method stub
		this.listObserver.add(obs);
		this.notifyObserver();
	}

	@Override
	public void notifyObserver() {

		// One Observer here is the MasterGamePanel
		for(GameObserver obs : this.listObserver)
			obs.update();		
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
