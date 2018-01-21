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
import com.oc.master.model.GameModel;
import com.oc.master.model.GameType;
import com.oc.master.model.observer.Observable;
import com.oc.master.model.observer.Observer;
import com.oc.master.view.game.ColorSelectorPanel;
import com.oc.master.view.game.SearchPanel;


/**
 * Main Class to initialize Swing interface for the game
 * @author bob
 * @version 1.0.1
 */
public class SwingWindow extends JFrame implements Observer {

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

	private Observable model;

	private SwingController swingController;

	/**
	 * Class Constructor
	 * @param obs
	 */
	public SwingWindow(Observable obs) {

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
	public void update(GameType type, GameMode mode) {

		// We have the Game Mode and Game Type
		// We can keep track of the type of Panel we need in case of....
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void home() {

		containerPanel.removeAll();
		containerPanel.add(new HomePanel(size).getPanel(),BorderLayout.CENTER);
		containerPanel.revalidate();
	}

	/**
	 * Calling the Mode Panel
	 */
	@Override 
	public void mode() {

		containerPanel.removeAll();
		ModePanel mp = new ModePanel(size,swingController);
		containerPanel.add(mp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();
	}

	@Override
	public void game() {

		containerPanel.removeAll();
		GamePanel gp = new GamePanel(size,swingController);
		containerPanel.add(gp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();
	}

	/**
	 * Methods calling the proper Game Panel depending on mode and type :
	 */
	@Override
	public void actionSearch() {

		containerPanel.removeAll();
		SearchPanel sp = new SearchPanel(size,new GameModel(this.model.getGameType(),this.model.getGameMode()));
		containerPanel.add(sp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();

	}

	@Override
	public void actionMaster() {


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
		case "actionColorSelector":
			actionColorSelector();
			break;

		}
	}



	/**
	 * Methods calling the proper Panel to let the user choose its colors combo:
	 */
	public void actionColorSelector() {

		containerPanel.removeAll();
		ColorSelectorPanel sp = new ColorSelectorPanel(size,new GameModel(this.model.getGameType(),this.model.getGameMode()));
		containerPanel.add(sp.getPanel(), BorderLayout.CENTER);
		containerPanel.revalidate();

	}
}
