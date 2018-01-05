package com.oc.master.model.observer;


public interface Observee {

	public void addObserver(Observer obs);
	public void notifyObserver();
	public void restartObserver();

	public void deleteObserver();
	public void homeObserver();

	public void reset();


}
