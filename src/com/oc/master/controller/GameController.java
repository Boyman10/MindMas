package com.oc.master.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.observer.Observee;

public class GameController implements ActionListener {

	static final Logger logger = LogManager.getLogger();

	private Observee model;

	public GameController (Observee model) {

		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			logger.trace("Action Performed : " + e.getActionCommand());
			
			// case Search type of Game, we update the Panel :
			if (e.getActionCommand().equals("search")) {
				
				
			
			}
	}
}
