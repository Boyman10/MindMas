package com.oc.master.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.model.Model;
import com.oc.master.model.observer.Observee;
import com.oc.master.view.GamePanel;
import com.oc.master.view.HomePanel;
import com.oc.master.view.SwingWindow;

/**
 * Controller Class handling main Menu click events
 * We observe the events on the menu - the window is the Observer
 * @author john
 * @version 1.0.0
 */
public class SwingController implements ActionListener {

	static final Logger logger = LogManager.getLogger();

	private Observee model;
	private JPanel containerPanel;
	private Dimension size; 
	private SwingWindow swingWindow;

	public SwingController (JPanel panel, Observee model, Dimension size, SwingWindow sw) {

		this.model = model;
		this.containerPanel = panel;
		this.size = size;
		this.swingWindow = sw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		logger.trace("Action performed " + e.getActionCommand());

		// NEW Game Menu action : 
		if (e.getActionCommand().equals("game")) {

			containerPanel.removeAll();
			GamePanel gp = new GamePanel(size, model);
			model.addObserver(gp);
			containerPanel.add(gp.getPanel(), BorderLayout.CENTER);
			containerPanel.revalidate();
			initModel();

		} else if(e.getActionCommand().equals("quit")) {

			// Quit the game or Return to console : -- TODO return to console
			System.exit(0);
			
		} else if(e.getActionCommand().equals("about")) {

			JOptionPane.showMessageDialog(null,
					"Author : Boy\nLicence : Freeware\nCopyright : whatever link",
					"Informations", JOptionPane.NO_OPTION);
			containerPanel.removeAll();
			containerPanel.add(new HomePanel(size).getPanel());
			containerPanel.revalidate();
			model.reset();
		}
	}

	/**
	 * Model initialization, we add an observer to the model
	 */
	private void initModel(){
		this.model = new Model();
		this.model.addObserver(swingWindow);
	}

}
