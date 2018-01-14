package com.oc.master.view.game;

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

import com.oc.master.controller.GameController;
import com.oc.master.model.observer.Observable;
import com.oc.master.model.observer.Observer;
import com.oc.master.view.MainContainer;

/**
 * Class aimed to display the Search +/- Game mode
 * @author boy
 * @version 1.0.0
 */
public class Search extends MainContainer implements Observer {

	private GameController controller;

	private JPanel introPanel;
	private JLabel introTxt;
	private LayoutManager layout;
	private JPanel buttonsPanel;
	
	/**
	 * Constructor for the HomePanel class
	 * @param dim
	 */
	public Search(Dimension dim, Observable mod){
		super(dim);
		// Instead of coupling containerPanel here - we revamp the code and use the notify of model
		//this.controller = new GameController(containerPanel,mod,dim,this);

		this.controller = new GameController(mod);
		initPanel();
	}

	/**
	 * Initialization of the panel
	 */
	public void initPanel(){

		introPanel = new JPanel();
		layout = new BoxLayout(introPanel, BoxLayout.Y_AXIS);
		introPanel.setLayout(layout);	

		introTxt = new JLabel();

		introTxt.setFont(arial);
		introTxt.setBackground(Color.white);
		introTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		introTxt.setText("<html><center><h1>Welcome here man !</h1>" +
				"<p>Please pick the game you wish to play !</p></center></html>");


		introPanel.add(introTxt);

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,2));

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

	@Override
	public void update(String intro) {

		introTxt.setText(intro);
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void home() {
		// TODO Auto-generated method stub

	}
}
