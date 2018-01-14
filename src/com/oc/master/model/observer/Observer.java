package com.oc.master.model.observer;

import com.oc.master.model.GameMode;
import com.oc.master.model.GameType;

public interface Observer {
	
		public void update(GameType gameType, GameMode gameMode);
		public void restart();
		
		public void home();
		public void game();
		public void mode();

		public void actionSearch();
		public void actionMaster();
}
