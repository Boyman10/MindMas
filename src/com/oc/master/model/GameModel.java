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
	
	// Attacker , Defense, Challenger
	private GameMode modeGame;
	
	/**
	 * Method to assign proper layout to panel
	 * @param command
	 */
	public void assign(String command) {
		
		
		this.notifyObserver();
	}

	@Override
	public void addObserver(GameObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restartObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObserver() {
		// TODO Auto-generated method stub
		
	}
	

	
}
