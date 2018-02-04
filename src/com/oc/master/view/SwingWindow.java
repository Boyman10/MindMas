package com.oc.master.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.controller.SwingController;
import com.oc.master.model.GameMode;

import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;
import com.oc.master.view.game.MasterGamePanel;
import com.oc.master.view.game.SearchPanel;


/**
 * Main Class to initialize Swing interface for the game
 * @author bob
 * @version 1.0.1
 */
public class SwingWindow extends JFrame implements GameObserver {

	private static final long serialVersionUID = 2121616383041870269L;
	
	static final Logger logger = LogManager.getLogger("View");
	
	private JMenuBar menu = null;

	private JMenu menuFile = null;
	private JMenuItem newGameMenuItem = null;
	private JMenuItem quitMenuItem = null;
	private JMenu aboutMenu = null;
	private JMenuItem aboutMenuItem = null;

	private JPanel containerPanel = new JPanel();
	private Dimension size;

	private GameObservable model;

	private SwingController swingController;

	/**
	 * Class Constructor
	 * @param obs
	 */
	public SwingWindow(GameObservable obs) {

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

		this.swingController = new SwingController(model);

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
	public void update(Object obj, GameMode mode) {

		// We have the Game Mode and Game Type
		// We can keep track of the type of Panel we need in case of....
	}

	@Override
	public void restart() {

		this.actionHome();

	}


	/**
	 * Grouping actions here
	 * TODO : remove the rest and keep the implementation here :
	 * @version 1.0.1
	 */
	@Override
	public void action(String method) {

		logger.trace("Calling action " + method);
		
		switch(method) {
		case "home":
			actionHome();
			break;
		case "master":
			actionMaster();
			break;
		case "mode":
			actionMode();
			break;
		case "game":
			actionGame();
			break;
		case "search" :
			actionSearch();
			break;
			default :
				logger.error("Wrong action taken... please check : " + method);

		}
	}

	public void actionHome() {

		containerPanel.removeAll();
		containerPanel.add(new HomePanel(size).getPanel(),BorderLayout.CENTER);
		containerPanel.revalidate();
	}


	/**
	 * Methods calling the proper Panel to let the user choose its colors combo:
	 */
	public void actionMaster() {

		containerPanel.removeAll();
		MasterGamePanel sp = new MasterGamePanel(size,model);
		containerPanel.add(sp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();

	}
	
	/**
	 * Calling the Mode Panel
	 */
	public void actionMode() {

		containerPanel.removeAll();
		ModePanel mp = new ModePanel(size,swingController);
		containerPanel.add(mp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();
	}

	public void actionGame() {

		containerPanel.removeAll();
		GamePanel gp = new GamePanel(size,swingController);
		containerPanel.add(gp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();
	}

	/**
	 * Methods calling the proper Game Panel depending on mode and type :
	 */
	public void actionSearch() {

		logger.trace("Action Search called");
		
		containerPanel.removeAll();
		SearchPanel sp = new SearchPanel(size,model);
		containerPanel.add(sp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();

	}	
}
