package com.oc.master.model.observer;

import com.oc.master.model.GameMode;

public interface GameObserver {
	public void update(Object obj, GameMode gm);
	public void restart();
}
