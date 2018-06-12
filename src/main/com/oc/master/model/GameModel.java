package main.com.oc.master.model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.oc.master.model.mind.Random;
import main.com.oc.master.model.mind.User;
import main.com.oc.master.model.mind.exception.ComboException;
import main.com.oc.master.model.observer.GameObservable;
import main.com.oc.master.model.observer.GameObserver;

/**
 * Model for the Games - Handling Data here
 * @author boy
 * @version 1.0.1
 */
public class GameModel implements GameObservable {

	static final Logger logger = LogManager.getLogger("GameModel");
	
	// default maximum tries -- TODO - reading parameter from file or user choice
	static final short MAX_TRIES = 3;
	
	private ArrayList<GameObserver> listObserver = new ArrayList<GameObserver>();
	
	// Master mind or Search
	private GameType typeGame;
	// Attacker , Defense, Challenger
	private GameMode modeGame;
	
	// room for 2 users here : User & Computer
	private User[] players;
	
	/**
	 * Empty constructor
	 */
	public GameModel() {
		// Define number of players : User & Computer
		players = new User[2];
	}
	
	public void setGameMode(GameMode gm) {
		
		modeGame = gm;
	}
	
	public void setGameType(GameType gt) {
		
		typeGame = gt;
	}	
	
	public GameType getGameType() {
		
		return typeGame;
	}
	
	
	public GameMode getGameMode() {
		
		return modeGame;
	}
	

	/**
	 * Method to initialize data
	 * @param userSecret -- make sure not null
	 */
	public void init(int[] userSecret) {		
		
		Random nb = new Random(GameModel.MAX_DIGITS);
		
		switch(this.modeGame) {
		
		/**
		 * Case Attacker - The computer will compare its combo with the user try
		 */		
		case ATTACK :
			
			players[0] = new User(null); // The user is Attacking - no secret combo for him
			players[1] = new User(nb.getNumbers()); // Computer here
			
			break;
			
		/**
		 * Case Defender (user) - the user/automatic part will compare its combo with computer try
		 */		
		case DEFENSE:
			
			players[0] = new User(userSecret); // The user is defending - secret combo here
			players[1] = new User(null); // Computer here
			
			break;
			
		case CHALLENGE:
			players[0] = new User(userSecret); // The user is defending - secret combo here
			players[1] = new User(nb.getNumbers()); // Computer here	
			
			break;
		default:
			
			logger.debug("Wrong mode for the game or empty mode !");
			break;
		
		}
	}
	
	
	
	/**
	 * Method to assign proper data to deal with
	 * @param vars
	 */
	public void assign(int[] vars) {
			
		// some type of control :
		if (players.length == 2 && (players[0] != null || players[1] != null)) {
			
			int counter = 0;
			
			if (this.modeGame == GameMode.ATTACK) {
				if (players[0].getClues() != null)
					counter = players[0].getClues().size();
				
			}
			else
				if (players[1].getClues() != null)
					counter = players[1].getClues().size();
			
			// We already initialize the players, we are in the game now !!
			logger.trace("We are gaming - hit number : " + counter);
			
			/**
			 * Now the fun part - the game
			 */
			switch(this.modeGame) {
			/**
			 * Case Attacker - The computer will compare its combo with the user try
			 */		
			case ATTACK :
				
				logger.trace("Vars submitted : " + Arrays.toString(vars));
				
				try {
					// Add the try first to the list
					players[0].addTry(vars);
					
				} catch (ComboException e) {

					logger.error("Problem with submitted try");
				} // The user is Attacking - no secret combo for him
				
				// Now compare the submitted combo try with the computer one :
				// then return the clue
				/*try {
					// UPDATE TODO : players[0].addClue(players[1].compareCombo(vars));
				} catch (ComboException e) {
					logger.error("Problem with comparison with the try");
				} // Computer here
*/				
							
				++counter;
				
				// Now check if combo is right :
				if (Arrays.equals(players[0].getTries().get(counter-1), players[1].getSecretCombo())) {
					
					JOptionPane.showMessageDialog(null,
						    "Congratulations, You've won !",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);	
					
					this.restartObserver();
				}
				
				// Now check MAX TRIES allowed and stop accordingly !
				if (counter == MAX_TRIES) {
					
					JOptionPane.showMessageDialog(null,
						    "Game Over, You've lost !",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);		
					
					this.restartObserver();
					
				}
				
				
				break;
				
			/**
			 * Case Defender (user) - the user/automatic part will compare its combo with computer try
			 */		
			case DEFENSE:
				
				break;
			case CHALLENGE:
				
				break;
			default:
				logger.debug("Wrong mode for the game or empty mode !");
				break;
			
			}			
			
			
			
		} else {
			
			logger.fatal("Need to init players !");
		}
		
		
		// notify short historic panel with clues and main panel
		this.notifyObserver();
	}

	@Override
	public void addObserver(GameObserver obs) {
		// TODO Auto-generated method stub
		this.listObserver.add(obs);
		this.notifyObserver();
	}

	@Override
	public void notifyObserver() {

		// One Observer here is the MasterGamePanel
		for(GameObserver obs : this.listObserver)
			obs.update(players, modeGame);		
	}

	@Override
	public void restartObserver() {
		// One Observer here is the MasterGamePanel but also the main window
		for(GameObserver obs : this.listObserver)
			obs.restart();			
	}

	@Override
	public void deleteObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionObserver(String method) {
				
		// One Observer here is the MasterGamePanel
		for (int i = 0;i < this.listObserver.size();i++)
			this.listObserver.get(i).action(method);		
	}
	

	
}
