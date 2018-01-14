package com.oc.master.model;

import java.util.ArrayList;

import com.oc.master.model.observer.Observable;
import com.oc.master.model.observer.Observer;

public class GameModel implements Observable {

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private String intro;
	/**
	 * Method to assign proper layout to panel
	 * @param command
	 */
	public void assign(String command) {
		
		
		if(command.equals("search")) {
			
			this.intro = "<html><h1>Research +/- Game</h1><p>Please select the mode for the game now:</p></html>";
			
		} else if(command.equals("master")) {
			
			this.intro = "<html><h1>Master Game</h1><p>Please select the mode for the game now:</p></html>";
			
		}
			
		
		this.notifyObserver();
	}
	
	@Override
	public void addObserver(Observer obs) {
		
		this.listObserver.add(obs);
		this.notifyObserver();
	}

	@Override
	public void notifyObserver() {
		
		for(Observer obs : this.listObserver)
			obs.update(intro);		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
}
