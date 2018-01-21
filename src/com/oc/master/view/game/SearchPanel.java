package com.oc.master.view.game;

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
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.controller.GameController;
import com.oc.master.model.GameMode;
import com.oc.master.model.mind.User;
import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;
import com.oc.master.utils.KeyboardAction;
import com.oc.master.view.MainContainer;

/**
 * Class aimed to display the Search +/- Game mode
 * @author boy
 * @version 1.0.0
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
		
		refreshGamePanel();
		
		historicPanel = new JPanel();
		JLabel history = new JLabel("Check what you've done so far :");
		Font font = new Font("Courier", Font.BOLD,12);
		history.setFont(font);
		historicPanel.add(history);
		
		historicPanel.setBackground(Color.white);
		cluePanel = new JPanel();
		cluePanel.setBackground(Color.black);

		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(gamePanel, BorderLayout.CENTER);
		this.panel.add(historicPanel, BorderLayout.EAST);
		this.panel.add(cluePanel, BorderLayout.SOUTH);
		
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
			fields[i] = Integer.parseInt(this.jtf[i].getText());
		}
					
		return fields;
	}
	
	@Override
	public void update(Object obj, GameMode gm) {

		logger.trace("Updating search Panel");
		
		if (obj instanceof User[]) {
						
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
					historicPanel.add(new JLabel(myTry));
				}	
				
				// The clue part :
				for (int i=0;i<players[0].getClues().size();i++) {
					String myClue = Arrays.toString((players[0].getClues()).get(i));
					cluePanel.add(new JLabel(myClue));
				}	
				
				
				refreshGamePanel();
				
			}
			
		}
		
		
		
	}

	/**
	 * Method which refresh game panel with empty data
	 */
	private void refreshGamePanel() {

		gamePanel.removeAll();
		
		Font police = new Font("Arial", Font.BOLD, 14);
		gamePanel.setBackground(Color.white);
		
		// Nullify object :
		//jtf = new JFormattedTextField[GameObservable.MAX_DIGITS];
		
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
			e.printStackTrace();
			logger.error("Error Refreshing game panel - double...");
		}
		
		/**
		 * Adding Validate button and onClickListener using Controller
		 */

		gamePanel.add(validateBtn);

		gamePanel.add(new JSeparator(JSeparator.VERTICAL),
		          BorderLayout.LINE_START);
		
	
		logger.trace("Refreshing Game Panel");
		
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
