package com.oc.master.model.observer;


public interface Observable {

	public void addObserver(Observer obs);
	public void notifyObserver();
	public void restartObserver();

	public void deleteObserver();
	public void homeObserver();
	public void gameObserver();
	public void modeObserver();
	public void reset();

}
