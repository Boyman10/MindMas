package com.oc.master.model.observer;

public interface GameObservable {
	
	// Max Size of Array - difficulty :
	public static final short MAX_DIGITS = 4;
	
	public void addObserver(GameObserver obs);
	public void notifyObserver();
	public void restartObserver();

	public void deleteObserver();
	
	public void actionObserver(String method);
}
