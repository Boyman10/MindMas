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

public class ModePanel extends MainContainer {

	private SwingController controller;
	
	private JPanel introPanel;
	private JLabel introTxt;
	private LayoutManager layout;
	private JPanel buttonsPanel;
	
	
	public ModePanel(Dimension dim, SwingController sw) {
		super(dim);

		this.controller = sw;
		initPanel();
	}

	@Override
	protected void initPanel() {
		
		introPanel = new JPanel();
		layout = new BoxLayout(introPanel, BoxLayout.Y_AXIS);
		introPanel.setLayout(layout);	

		introTxt = new JLabel();

		introTxt.setFont(arial);
		introTxt.setBackground(Color.white);
		introTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		introTxt.setText("<html><center><h1>Please Pick up the Game mode</h1>");


		introPanel.add(introTxt);

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,3));

		// Default buttons :
		JButton search = new JButton("Attack");
		JButton master = new JButton("Defense");
		JButton challenge = new JButton("Challenger");

		search.setActionCommand("search");
		master.setActionCommand("master");
		challenge.setActionCommand("challenge");
		
		
		search.addActionListener(this.controller);
		master.addActionListener(this.controller);
		challenge.addActionListener(this.controller);
		
		buttonsPanel.add(search);
		buttonsPanel.add(master);
		buttonsPanel.add(challenge);

		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(buttonsPanel, BorderLayout.CENTER);
		
	}

}
