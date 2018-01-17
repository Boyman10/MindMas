package com.oc.master.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.GameModel;
import com.oc.master.model.GameType;
import com.oc.master.model.observer.GameObservable;

/**
 * Class handling events and updates on the current Game Panel
 * @author boy
 * @version 1.0.0
 */
public class GameController implements ActionListener {

	static final Logger logger = LogManager.getLogger("GameController");

	private GameModel model;

	public GameController (GameObservable model) {

		this.model = (GameModel)model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			logger.trace("Action Performed : " + e.getActionCommand());
			
			/**
			 * Case we click on the Validate Button from the Search game panel 
			 */
			if (e.getActionCommand().equals("validate")) {
				
				//TODO -- We do have the model in references but in case, we should make sure it ain't empty !
				//We use the source as the observable to call our model and update the Observer accordingly :
				
				
			}
		
	}
}
