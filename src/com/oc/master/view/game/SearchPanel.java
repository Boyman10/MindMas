package com.oc.master.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.oc.master.controller.GameController;

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
		introPanel.setBackground(Color.white);
		introTxt = new JLabel();
		introTxt.setHorizontalAlignment(JLabel.CENTER);
		introTxt.setFont(arial);

		introTxt.setText("<html><center><h1>Search +/- Game</h1>" +
				"<p>Enjoy !</p></center></html>");


		introPanel.add(introTxt);

		gamePanel = new JPanel();
		Font police = new Font("Arial", Font.BOLD, 14);
		gamePanel.setBackground(Color.white);
		
		JFormattedTextField[] jtf = new JFormattedTextField[MAX_DIGITS];
		
		try{
			MaskFormatter nb = new MaskFormatter("#");
		
			for(short i = 0;i < MAX_DIGITS;i++) {
	
				jtf[i] = new JFormattedTextField(nb);	
	
				jtf[i].setFont(police);
				jtf[i].setPreferredSize(new Dimension(50, 30));
				jtf[i].setForeground(Color.BLUE);
				
				jtf[i].addKeyListener(new KeyboardAction());
				
				gamePanel.add(jtf[i]);
			}
		} catch(ParseException e){e.printStackTrace();}
		
		gamePanel.add(new JButton("Validate"));

		gamePanel.add(new JSeparator(JSeparator.VERTICAL),
		          BorderLayout.LINE_START);
		
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