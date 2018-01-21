package com.oc.master.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oc.master.controller.GameController;
import com.oc.master.model.GameMode;
import com.oc.master.model.mind.User;
import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;
import com.oc.master.view.MainContainer;

/**
 * Class to load the MasterMind Game
 * @author boy
 * @version 1.0.0
 */
public class MasterGamePanel  extends MainContainer implements GameObserver {

	// TODO - Change name of Class to MasterGamePanel ------------ SAME LAYOUT FOR THE WHOLE GAME
	
	private GameController controller;
	private GameObservable model;
	
	
	static final Logger logger = LogManager.getLogger();
	
	
	private JPanel introPanel, choicePanel, colorPanel;
	private JLabel introTxt;
	private LayoutManager layout;
	private JButton validateBtn;

	
	private final static String emptyIcon = "empty.png";
	private final static String[] iconString = {"blue.png","gray.png","black.png","red.png","yellow.png","pink.png"}; 
	
	private JLabel[] colorCombo, colorSecret;
	
		
	/**
	 * Constructor for the ColorSelectorPanel class
	 * @param dim
	 */
	public MasterGamePanel(Dimension dim, GameObservable mod){
		super(dim);
				
		this.model = mod;
		this.controller = new GameController(model, this) ;
		this.model.addObserver(this);
		initPanel();
	}

	/**
	 * Initialization of the panel
	 * Colors at the bottom
	 * Empty fields in center ready to receive the colors from the bottom
	 * At the top : introduction
	 */
	public void initPanel(){
				
		introPanel = new JPanel();
		layout = new BoxLayout(introPanel, BoxLayout.Y_AXIS);
		
		introPanel.setLayout(layout);	
		introPanel.setBackground(Color.white);
		
		Font police = new Font("Arial", Font.BOLD, 14);

		introTxt = new JLabel();
		introTxt.setHorizontalAlignment(JLabel.CENTER);
		introTxt.setFont(police);

		introTxt.setText("<html><center><h1>Master Game</h1>" +
				"<p>Pick up your colors by dragging and dropping them to the fields !</p></center></html>");

		MouseListener listener = new DragMouseAdapter();
	    
	    
	    
		introPanel.add(introTxt);
		
		introPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		// Panel to receive colors
		choicePanel = new JPanel();
		choicePanel.setBackground(Color.yellow);
		
		
		colorSecret = new JLabel[GameObservable.MAX_DIGITS];
		
		for(short i = 0;i < GameObservable.MAX_DIGITS;i++) {
			
			colorSecret[i] = new JLabel(new ImageIcon("res/images/" + emptyIcon), JLabel.CENTER);	

			colorSecret[i].setSize(new Dimension(32, 33));
			
			colorSecret[i].setTransferHandler(new TransferHandler("icon"));
		      
			colorSecret[i].addMouseListener(listener);

			// Set proper name to get the index while retrieving the color :
			colorSecret[i].setName("-1");
			choicePanel.add(colorSecret[i]);	
		}
		
		/**
		 * Adding Validate button and onClickListener using Controller
		 */
		validateBtn = new JButton("Validate");
		validateBtn.setActionCommand("validate");
		validateBtn.addActionListener(this.controller);
		choicePanel.add(validateBtn);

		
		colorPanel = new JPanel();
		colorPanel.setBackground(Color.black);
		
		colorCombo = new JLabel[GameObservable.MAX_DIGITS];
		
		for(short i = 0;i < GameObservable.MAX_DIGITS;i++) {
			
			//icon[i] = createImageIcon("/res/images/" + iconString[i]);

			
			colorCombo[i] = new JLabel(new ImageIcon("res/images/" + iconString[i]), JLabel.CENTER);	
			colorCombo[i].setSize(new Dimension(32, 32));
			
			
			//colorCombo[i].setBorder(BorderFactory.createLineBorder(Color.white));
			/**
			 * Implementing Drag N Drop for our JLabel
			 */
		    // create the new Object to handle the Drag N Drop for our JLabel
			//https://openclassrooms.com/courses/apprenez-a-programmer-en-java/le-drag-n-drop
			colorCombo[i].setTransferHandler(new TransferHandler("icon"));
		      
			colorCombo[i].addMouseListener(listener);
			colorCombo[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			// Set proper name to get the index while retrieving the color :
			colorCombo[i].setName(String.valueOf(i));
			
			colorPanel.add(colorCombo[i]);			
		}
		
		
		this.panel.setLayout(new BorderLayout());
		this.panel.add(introPanel, BorderLayout.NORTH);
		this.panel.add(colorPanel, BorderLayout.EAST);
		this.panel.add(choicePanel, BorderLayout.CENTER);
		
		

	}

	/**
	 * Method returning the content of the fields containing the Color Combo :
	 * @return fields
	 */
	public int[] getFields() {
		
		int[] fields = new int[GameObservable.MAX_DIGITS];
		
		for(short i = 0;i < GameObservable.MAX_DIGITS;i++) {
			
			fields[i] = Integer.parseInt(this.colorSecret[i].getName());
		}
					
		return fields;
	}
	
	@Override
	public void update(Object obj, GameMode gm) {

		// If we have players submitted then update the panels accordingly
		if (obj != null && obj instanceof User[]) {
			
			introTxt.setText("<html><center><h1>Master Game</h1>" +
					"<p>Now it's time to play, have fun !</p></center></html>");
			
			// Do the rest here ...
			
			
		}

		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public void action(String method) {
		// TODO Auto-generated method stub
		
	}
	
	
	class DragMouseAdapter extends MouseAdapter {
		  public void mousePressed(MouseEvent e) {
		    JComponent c = (JComponent) e.getSource();
		    TransferHandler handler = c.getTransferHandler();
		    handler.exportAsDrag(c, e, TransferHandler.COPY);
		  }
		}


	
}
