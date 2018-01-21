package com.oc.master.model.observer;

import com.oc.master.model.GameMode;
import com.oc.master.model.GameType;

public interface Observable {

	public GameType getGameType();
	public GameMode getGameMode();
	
	public void addObserver(Observer obs);
	public void notifyObserver();
	public void restartObserver();

	public void deleteObserver();
	public void homeObserver();
	public void gameObserver();
	public void modeObserver();
	
	public void actionSearchObserver();
	public void actionMasterObserver();
	
	public void reset();

}
