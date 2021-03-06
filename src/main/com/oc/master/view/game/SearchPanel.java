package main.com.oc.master.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.text.ParseException;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.oc.master.controller.GameController;
import main.com.oc.master.model.GameMode;
import main.com.oc.master.model.mind.User;
import main.com.oc.master.model.observer.GameObservable;
import main.com.oc.master.model.observer.GameObserver;
import main.com.oc.master.utils.KeyboardAction;
import main.com.oc.master.view.MainContainer;

/**
 * Class aimed to display the Search +/- Game mode
 * @author boy
 * @version 1.0.2
 */
public class SearchPanel extends MainContainer implements GameObserver {

	static final Logger logger = LogManager.getLogger();

	
	private GameController controller;
	private GameObservable model;
	
	
	private JPanel introPanel, gamePanel, cluePanel, historicPanel;
	private JLabel introTxt, secretCombo;
	private LayoutManager layout;
	private JButton validateBtn;

	private JFormattedTextField[] jtf;
		
	/**
	 * Constructor for the SearchPanel class
	 * @param dim
	 */
	public SearchPanel(Dimension dim, GameObservable mod){
		super(dim);
		
		this.model = mod;		
		this.controller = new GameController(mod, this) ;
		
		initPanel();
	}

	/**
	 * Initialization of the panel
	 * Historic of the current game on the right
	 * General Info on top + Combo to guess
	 * In middle : area to drop numbers
	 * In South : clue from the computer
	 */
	public void initPanel(){
		
		introPanel = new JPanel();
		layout = new BoxLayout(introPanel, BoxLayout.Y_AXIS);
		introPanel.setLayout(layout);	
		introPanel.setBackground(Color.white);
		introTxt = new JLabel();
		introTxt.setHorizontalAlignment(JLabel.CENTER);
		introTxt.setFont(arial);

		introTxt.setText("<html><center><h1>Search +/- Game</h1>" +
				"<p>Enjoy !</p></center></html>");

		char[] secret = new char[GameObservable.MAX_DIGITS];
		for (int i = 0; i < GameObservable.MAX_DIGITS; i++)
			secret[i] = '#';
		
		secretCombo = new JLabel();
		
		secretCombo.setText(String.valueOf(secret));
		
		introPanel.add(introTxt);
		introPanel.add(secretCombo);

		gamePanel = new JPanel();

		jtf = new JFormattedTextField[GameObservable.MAX_DIGITS];
		validateBtn = new JButton("Validate");
		validateBtn.setActionCommand("validate");
		validateBtn.addActionListener(this.controller);
		
		//refreshGamePanel();
		
		historicPanel = new JPanel();
		historicPanel.setLayout(new BoxLayout(historicPanel, BoxLayout.Y_AXIS));
		
		JLabel history = new JLabel("Check what you've done so far :");
		Font font = new Font("Courier", Font.BOLD,12);
		history.setFont(font);
		historicPanel.add(history);
		
		historicPanel.setBackground(Color.white);


		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(gamePanel, BorderLayout.CENTER);
		this.panel.add(historicPanel, BorderLayout.EAST);
		
		this.panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.model.addObserver(this);
	}

	/**
	 * Method returning the content of the fields containing the proposition :
	 * @return fields
	 */
	public int[] getFields() {
		
		int[] fields = new int[GameObservable.MAX_DIGITS];
		
		for(short i = 0;i < GameObservable.MAX_DIGITS;i++) {

			// Make sure we have an integer in each field
			try {
				fields[i] = Integer.parseInt(this.jtf[i].getText());
			} catch(NumberFormatException  e) {
				
				return null;
			}
		}
					
		return fields;
	}
	
	@Override
	public void update(Object obj, GameMode gm) {

		logger.trace("Updating search Panel");
	
		
		/**
		 * Currently handling the game
		 */
		if (obj instanceof User[]) {
						
			historicPanel.removeAll();
			
			// obj[0] is the user
			// obj[1] is the computer

			User[] players = (User[])obj;
			
			if (gm == GameMode.ATTACK) {
				
				introTxt.setText("<html><center><h1>Search +/- Game</h1>" +
						"<p>Now time to play - You are trying to guess !</p></center></html>");
				
				logger.trace("Writing secret combo from computer " + Arrays.toString(players[1].getSecretCombo()) );
				String myCombo = Arrays.toString(players[1].getSecretCombo());
				secretCombo.setText(myCombo);

				
				// Now historic panel part :
				
				for (int i=0;i<players[0].getTries().size();i++) {
					
					String myTry = Arrays.toString((players[0].getTries()).get(i));
					String myClue = Arrays.toString((players[0].getClues()).get(i));
					
					historicPanel.add(new JLabel(myTry));
					historicPanel.add(new JLabel(myClue));
					
					logger.trace("Adding try from user to historic panel " + myTry );
					logger.trace("Adding clue for user to historic panel " + myClue );
				}	
				
				
				
				refreshGamePanel();
				
			} else if (gm == GameMode.DEFENSE) {
				
				introTxt.setText("<html><center><h1>Search +/- Game</h1>" +
						"<p>Now time to play - Computer is trying to guess !</p></center></html>");
				
				logger.trace("Writing secret combo from user " + Arrays.toString(players[0].getSecretCombo()) );
				String myCombo = Arrays.toString(players[0].getSecretCombo());
				secretCombo.setText(myCombo);

				
				// Now historic panel part :
				
				for (int i=0;i<players[1].getTries().size();i++) {
					
					String myTry = Arrays.toString((players[1].getTries()).get(i));
					String myClue = Arrays.toString((players[1].getClues()).get(i));
					
					historicPanel.add(new JLabel(myTry));
					historicPanel.add(new JLabel(myClue));
					
					logger.trace("Adding try from computer to historic panel " + myTry );
					logger.trace("Adding clue for computer to historic panel " + myClue );
				}	
				
				
				
				refreshGamePanel();
				
			}
			
			historicPanel.revalidate();
		}
		
		
		
		logger.trace("End of update");
	}

	/**
	 * Method which refresh game panel with empty data
	 * TODO : remove this panel and update its content based on computer research !!
	 */
	private void refreshGamePanel() {
		

		logger.trace("Refreshing Game Panel - Start");
		
		gamePanel.removeAll();
		
		Font police = new Font("Arial", Font.BOLD, 14);
		gamePanel.setBackground(Color.white);
		
		try{
			MaskFormatter nb = new MaskFormatter("#");
		
			for(short i = 0;i < GameObservable.MAX_DIGITS;i++) {
	
				jtf[i] = new JFormattedTextField(nb);	
	
				jtf[i].setFont(police);
				jtf[i].setPreferredSize(new Dimension(50, 30));
				jtf[i].setForeground(Color.BLUE);
				
				jtf[i].addKeyListener(new KeyboardAction());
				
				gamePanel.add(jtf[i]);
			}
		} catch(ParseException e){
			logger.error("Error Refreshing game panel - double...");
			e.printStackTrace();
			
		}
		
		/**
		 * Adding Validate button and onClickListener using Controller
		 */

		gamePanel.add(validateBtn);
		gamePanel.revalidate();
	
		logger.trace("Refreshing Game Panel");
				
	}

	@Override
	public void restart() {

	}

	@Override
	public void action(String method) {
		// TODO Auto-generated method stub
		
	}


}
