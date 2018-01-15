package com.oc.master.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.GameModel;
import com.oc.master.model.observer.GameObservable;

/**
 * Class handling events and updates on the current Game Panel
 * @author boy
 * @version 1.0.0
 */
public class GameController implements ActionListener {

	static final Logger logger = LogManager.getLogger();

	private GameModel model;

	public GameController (GameObservable model) {

		this.model = (GameModel)model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			logger.trace("Action Performed : " + e.getActionCommand());
			
		
	}
}
