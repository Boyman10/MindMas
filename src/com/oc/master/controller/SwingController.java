package com.oc.master.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.GameMode;
import com.oc.master.model.GameType;
import com.oc.master.model.Model;
import com.oc.master.model.observer.Observable;

/**
 * Controller Class handling main Menu click events
 * We observe the events on the menu & main buttons - the window is the Observer
 * @author john
 * @version 1.0.1
 */
public class SwingController implements ActionListener {

	static final Logger logger = LogManager.getLogger();

	private Model model;

	public SwingController ( Observable model) {

		this.model = (Model)model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		logger.trace("Action performed " + e.getActionCommand());

		// NEW Game Menu action : 
		if (e.getActionCommand().equals("game")) {

			model.gameObserver();

		} else if(e.getActionCommand().equals("quit")) {

			// Quit the game or Return to console : -- TODO return to console
			System.exit(0);
			
		} else if(e.getActionCommand().equals("about")) {

			JOptionPane.showMessageDialog(null,
					"Author : Boy\nLicence : Freeware\nCopyright : whatever link",
					"Informations", JOptionPane.NO_OPTION);

			model.homeObserver();
						
		} // NEW Game Menu action : 
		else if (e.getActionCommand().equals("search") || e.getActionCommand().equals("master") ) {

			if (e.getActionCommand().equals("search")) {
				
				model.setGameType(GameType.SEARCH);
			}
			else {
				model.setGameType(GameType.MASTER);
							
			}
			
			model.modeObserver();	
			
		} else if (e.getActionCommand().equals("attack")) {
			
			logger.error("Starting the game now...");
			
			model.setGameMode(GameMode.ATTACK);
			
			switch (model.getGameType()) {
			
				case SEARCH : 
					model.actionSearchObserver();
					break;
				case MASTER :
					model.actionMasterObserver();
					
				default :
					logger.error("Starting game mode error !");
			}
			
			
		}
	}

}