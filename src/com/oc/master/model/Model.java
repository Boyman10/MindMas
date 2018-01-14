package com.oc.master.model;

import java.util.ArrayList;

import com.oc.master.model.observer.Observable;
import com.oc.master.model.observer.Observer;

/**
 * Main class dealing with Data throughout the SwingWindow Frame
 * @author boy
 * @version 1.0.2
 */
public class Model implements Observable {

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	// Search +/- or Master
	private short typeGame;
	// Attacker , Defenser, Challenger
	private short modeGame;
	
	public Model() {
		
		typeGame = 0;
		modeGame = 0;
	}
	
	public void setGameMode(short gm) {
		
		modeGame = gm;
	}
	
	public void setGameType(short gt) {
		
		typeGame = gt;
	}	
	
	public short getGameType() {
		
		return typeGame;
	}
	
	
	public short getGameMode() {
		
		return modeGame;
	}
	

	
	@Override
	public void addObserver(Observer obs) {
		
		this.listObserver.add(obs);
		this.notifyObserver();
	}

	@Override
	public void notifyObserver() {
		for(Observer obs : this.listObserver)
			obs.update(typeGame, modeGame);
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
	public void homeObserver() {
		
		for(Observer obs : this.listObserver)
			obs.home();		
	}
	
	/**
	 * Method intend to call the mode panel from every Observer :
	 */
	@Override
	public void modeObserver() {
		
		for(Observer obs : this.listObserver)
			obs.mode();
	}
	
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
}
