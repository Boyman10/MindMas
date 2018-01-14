package com.oc.master.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.oc.master.controller.SwingController;

/**
 * Game panel where we actually play with the computer 
 * Any type of game involved !!
 * @author bob
 * @version 1.0.1
 */
public class GamePanel extends MainContainer {

	private SwingController controller;

	private JPanel introPanel;
	private JLabel introTxt;
	private LayoutManager layout;
	private JPanel buttonsPanel;
	
	/**
	 * Constructor for the GamePanel class
	 * @param dim
	 */
	public GamePanel(Dimension dim, SwingController sw){
		super(dim);

		this.controller = sw;
		initPanel();
	}

	/**
	 * Initialization of the panel
	 */
	public void initPanel(){

		introPanel = new JPanel();
		layout = new BoxLayout(introPanel, BoxLayout.Y_AXIS);
		introPanel.setLayout(layout);	
		introPanel.setBackground(Color.white);
		
		introTxt = new JLabel();

		introTxt.setFont(arial);
		introTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		introTxt.setText("<html><center><h1>Welcome here man !</h1>" +
				"<p>Please pick the game you wish to play !</p></center></html>");


		introPanel.add(introTxt);

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,2));
		buttonsPanel.setBackground(Color.white);
		
		// Default buttons :
		JButton search = new JButton("Search +/-");
		JButton master = new JButton("Master Me");

		search.setActionCommand("search");
		master.setActionCommand("master");

		search.addActionListener(this.controller);
		master.addActionListener(this.controller);

		buttonsPanel.add(search);
		buttonsPanel.add(master);

		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(buttonsPanel, BorderLayout.CENTER);
	}


}
