package com.oc.master.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.oc.master.controller.GameController;

import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;
import com.oc.master.view.MainContainer;

/**
 * Class aimed to display the Search +/- Game mode
 * @author boy
 * @version 1.0.0
 */
public class SearchPanel extends MainContainer implements GameObserver {

	private GameController controller;

	private static final short MAX_DIGITS = 4;

	private JPanel introPanel, gamePanel, cluePanel, historicPanel;
	private JLabel introTxt;
	private LayoutManager layout;
	

	/**
	 * Constructor for the HomePanel class
	 * @param dim
	 */
	public SearchPanel(Dimension dim, GameObservable mod){
		super(dim);

		this.controller = new GameController(mod);
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

		introTxt = new JLabel();

		introTxt.setFont(arial);
		introTxt.setBackground(Color.white);
		introTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		introTxt.setText("<html><center><h1>Search +/- Game</h1>" +
				"<p>Enjoy !</p></center></html>");


		introPanel.add(introTxt);

		gamePanel = new JPanel();
		Font police = new Font("Arial", Font.BOLD, 14);

		JFormattedTextField[] jtf = new JFormattedTextField(8);
		
		for(short i = 0;i < MAX_DIGITS;i++) {

			jtf[i] = new JFormattedTextField(NumberFormat.getIntegerInstance());	

			jtf[i].setFont(police);
			jtf[i].setPreferredSize(new Dimension(50, 30));
			jtf[i].setForeground(Color.BLUE);
			gamePanel.add(jtf[i]);
		}
		
		gamePanel.add(new JButton("Validate"));

		historicPanel = new JPanel();
		JLabel history = new JLabel("Check what you've done so far :<p>");
		historicPanel.add(history);
		
		
		cluePanel = new JPanel();
		

		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(gamePanel, BorderLayout.CENTER);
		this.panel.add(historicPanel, BorderLayout.EAST);
		this.panel.add(cluePanel, BorderLayout.SOUTH);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}


}
