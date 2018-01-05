package com.oc.master.model;

import java.util.ArrayList;

import com.oc.master.model.observer.Observee;
import com.oc.master.model.observer.Observer;

public class Model implements Observee {

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	@Override
	public void addObserver(Observer obs) {
		
		this.listObserver.add(obs);
		this.notifyObserver();
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

	@Override
	public void homeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
}
