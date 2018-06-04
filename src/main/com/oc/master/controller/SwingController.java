package main.com.oc.master.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.oc.master.model.GameMode;
import main.com.oc.master.model.GameModel;
import main.com.oc.master.model.GameType;
import main.com.oc.master.model.observer.GameObservable;
import main.com.oc.master.model.observer.GameObserver;
import main.com.oc.master.view.game.ComboDialog;

/**
 * Controller Class handling main Menu click events
 * We observe the events on the menu & main buttons - the window is the Observer
 * @author john
 * @version 1.0.1
 */
public class SwingController implements ActionListener, GameObserver {

	static final Logger logger = LogManager.getLogger();

	private GameModel model;

	public SwingController ( GameObservable model) {

		this.model = (GameModel)model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		logger.trace("Action performed " + e.getActionCommand());

		// NEW Game Menu action : 
		if (e.getActionCommand().equals("game")) {

			model.actionObserver("game");

		} else if(e.getActionCommand().equals("quit")) {

			// Quit the game or Return to console : -- TODO return to console
			System.exit(0);
			
		} else if(e.getActionCommand().equals("about")) {

			JOptionPane.showMessageDialog(null,
					"Author : Boy\nLicence : Freeware\nCopyright : whatever link",
					"Informations", JOptionPane.NO_OPTION);

			model.actionObserver("home");
						
		} // NEW Game Menu action : 
		else if (e.getActionCommand().equals("search") || e.getActionCommand().equals("master") ) {

			if (e.getActionCommand().equals("search")) {
				
				model.setGameType(GameType.SEARCH);
			}
			else {
				model.setGameType(GameType.MASTER);
							
			}
			
			model.actionObserver("mode");
			
		} else if (e.getActionCommand().equals("attack")) {
			
			logger.trace("Starting the game now - Attack - calling model");
			
			model.setGameMode(GameMode.ATTACK);
			
			switch (model.getGameType()) {
			
				case SEARCH : 
					model.init(null);
					model.actionObserver("search");
					break;
				case MASTER :
					model.init(null);
					model.actionObserver("master");
					break;
				default :
					logger.error("Starting game Type error !");
			}
			
			logger.trace("panel opened successfully !");
			
		} else if (e.getActionCommand().equals("defense")) {
			
			logger.error("Starting the game now - Master defense");
			
			model.setGameMode(GameMode.DEFENSE);
			
			switch (model.getGameType()) {
			
				case SEARCH : 
					
					// Open up dialog to retrieve the choosen combo
					// Launching JDialog
					ComboDialog dialog = new ComboDialog("Pick up a combo !", model.MAX_DIGITS);
					dialog.addObserver(this);
					dialog.setVisible(true);

					break;
				case MASTER :
					model.init(null);
					model.actionObserver("master");
					break;
				default :
					logger.error("Starting game type error !");
			}
			
			
		}
	}

	/**
	 * Method to launch the Search game from the controller once we do have a choosen combo from the user
	 * @param Object of type int[] and GameMode which is not used here
	 */
	@Override
	public void update(Object obj, GameMode gm) {
		if (obj instanceof int[])
		{
			model.init((int[])obj);
			model.actionObserver("search");
		}
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action(String method) {
		// TODO Auto-generated method stub
		
	}

}
