package com.oc.master.model.observer;

public interface GameObservable {
	
	public void addObserver(GameObserver obs);
	public void notifyObserver();
	public void restartObserver();

	public void deleteObserver();
	
	public void actionObserver(String method);
}
