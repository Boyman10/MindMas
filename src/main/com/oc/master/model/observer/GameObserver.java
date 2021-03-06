package main.com.oc.master.model.observer;

import main.com.oc.master.model.GameMode;

public interface GameObserver {
	public void update(Object obj, GameMode gm);
	public void restart();
	public void action(String method);
}
