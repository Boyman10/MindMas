package com.oc.master.model.observer;

public interface Observer {
		public void update(short gameType, short gameMode);
		public void restart();
		
		public void home();
		public void game();
		public void mode();

}
