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
	private GameType typeGame;
	// Attacker , Defense, Challenger
	private GameMode modeGame;
	
	public Model() {
		

	}
	
	public void setGameMode(GameMode gm) {
		
		modeGame = gm;
	}
	
	public void setGameType(GameType gt) {
		
		typeGame = gt;
	}	
	
	public GameType getGameType() {
		
		return typeGame;
	}
	
	
	public GameMode getGameMode() {
		
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
	


	
	@Override
	public void gameObserver() {
		for(Observer obs : this.listObserver)
			obs.game();		
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
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
	public void actionSearchObserver() {
		
		for(Observer obs : this.listObserver)
			obs.actionSearch();		
	}

	@Override
	public void actionMasterObserver() {
		
		for(Observer obs : this.listObserver)
			obs.actionMaster();		
	}


	
}
