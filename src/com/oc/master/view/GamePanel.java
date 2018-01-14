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

import com.oc.master.controller.GameController;
import com.oc.master.model.observer.Observee;
import com.oc.master.model.observer.Observer;

/**
 * Game panel where we actually play with the computer 
 * Any type of game involved !!
 * @author bob
 * @version 1.0.1
 */
public class GamePanel extends MainContainer implements Observer {
	
	private GameController controller;
	
	/**
	 * Constructor for the HomePanel class
	 * @param dim
	 */
	public GamePanel(Dimension dim, Observee mod){
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
				
		JPanel introPanel = new JPanel();
	    LayoutManager layout = new BoxLayout(introPanel, BoxLayout.Y_AXIS);
	    introPanel.setLayout(layout);	
		
		JLabel txt = new JLabel(	"<html><center><h1>Welcome here man !</h1>" +
				"<p>Please pick the game you wish to play !</p></center></html>");

		txt.setFont(arial);
		txt.setBackground(Color.white);
		txt.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		introPanel.add(txt);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,2));
		
		JButton search = new JButton("Search +/-");
		JButton master = new JButton("Master Me");
		
		search.setActionCommand("search");
		master.setActionCommand("master");
		
		buttonsPanel.add(search);
		buttonsPanel.add(master);
		
		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(buttonsPanel, BorderLayout.CENTER);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
