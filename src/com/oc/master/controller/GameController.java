package com.oc.master.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.GameModel;
import com.oc.master.model.observer.GameObservable;
import com.oc.master.view.MainContainer;
import com.oc.master.view.game.MasterGamePanel;
import com.oc.master.view.game.SearchPanel;

/**
 * Class handling events and updates on the current Game Panel
 * @author boy
 * @version 1.0.0
 */
public class GameController implements ActionListener {

	static final Logger logger = LogManager.getLogger("GameController");

	private GameModel model;
	private MainContainer container;

	public GameController (GameObservable model, MainContainer container) {
		
		logger.trace("Switching Controller");
		
		this.model = (GameModel)model;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			logger.trace("Action Performed : " + e.getActionCommand());
			
			/**
			 * Case we click on the Validate Button from the Search game panel 
			 */
			if (e.getActionCommand().equals("validate")) {
				
				//TODO -- We do have the model in references but in case, we should make sure it ain't empty !
				
				// Verify if it is SearchPanel calling :
				if (container instanceof SearchPanel)
					model.assign(((SearchPanel)container).getFields());
				else if (container instanceof MasterGamePanel)
					model.assign(((MasterGamePanel)container).getFields());
			}
		
	}
}
