package com.oc.master.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.oc.master.controller.SwingController;
import com.oc.master.model.observer.Observee;
import com.oc.master.model.observer.Observer;


/**
 * Main Class to initialize Swing interface for the game
 * @author bob
 * @version 1.0.1
 */
public class SwingWindow extends JFrame implements Observer {

	private static final long serialVersionUID = 2121616383041870269L;

	private JMenuBar menu = null;

	private JMenu menuFile = null;
	private JMenuItem newGameMenuItem = null;
	private JMenuItem quitMenuItem = null;
	private JMenu aboutMenu = null;
	private JMenuItem aboutMenuItem = null;

	private JPanel containerPanel = new JPanel();
	private Dimension size;
	
	private Observee model;

	private SwingController swingController;

	/**
	 * Class Constructor
	 * @param obs
	 */
	public SwingWindow(Observee obs) {

	    this.model = obs;
	    initWindow();
	   
	}
	
	/**
	 * Method to generate the window with its menu and
	 * default panel
	 */
	private void initWindow() {
		
		this.setTitle("Welcome to a killer M Game !");
	    this.setSize(900, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    this.model.addObserver(this);
	    	    
	    // set default dimension for proper cleaning of panel :
	    this.size = new Dimension(this.getWidth(), this.getHeight());
	    
	    this.swingController = new SwingController(containerPanel,model,size,this);
	    
	    menu = new JMenuBar();

	    menuFile = new JMenu("File");
	    menuFile.setMnemonic('f');

	    newGameMenuItem = new JMenuItem("New Game");
	    newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
	                                                  InputEvent.CTRL_MASK));
	    newGameMenuItem.setActionCommand("game");
	    
	    newGameMenuItem.addActionListener(swingController);


	    quitMenuItem = new JMenuItem("Exit");
	    quitMenuItem.setActionCommand("quit");
	    
	    quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	                                                  KeyEvent.CTRL_MASK));
	    quitMenuItem.addActionListener(swingController);


	    menuFile.add(newGameMenuItem);
	    menuFile.addSeparator();
	    menuFile.add(quitMenuItem);

	    aboutMenu = new JMenu("About");
	    aboutMenu.setMnemonic('a');


	    aboutMenuItem = new JMenuItem("   ?   ");
	    aboutMenuItem.setActionCommand("about");
	    aboutMenuItem.addActionListener(swingController);

	    aboutMenu.add(aboutMenuItem);

	    menu.add(menuFile);
	    menu.add(aboutMenu);
	    
	    this.containerPanel.setPreferredSize(this.size);
	    this.containerPanel.setBackground(Color.white);
	    this.containerPanel.add(new HomePanel(this.size).getPanel());
	    
	    this.setContentPane(this.containerPanel);
	    
	    this.setJMenuBar(menu);
		
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
