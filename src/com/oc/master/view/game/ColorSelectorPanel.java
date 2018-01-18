package com.oc.master.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import com.oc.master.controller.GameController;
import com.oc.master.model.observer.GameObservable;
import com.oc.master.model.observer.GameObserver;
import com.oc.master.view.MainContainer;

public class ColorSelectorPanel  extends MainContainer implements GameObserver {

	private GameController controller;

	private static final short MAX_DIGITS = 4;

	private JPanel introPanel, choicePanel, colorPanel;
	private JLabel introTxt;
	private LayoutManager layout;
	private JButton validateBtn;

	private JLabel[] colorCombo;
	private JEditorPane[] secretCombo;
		
	/**
	 * Constructor for the ColorSelectorPanel class
	 * @param dim
	 */
	public ColorSelectorPanel(Dimension dim, GameObservable mod){
		super(dim);
				
		this.controller = new GameController(mod, this) ;
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
				"<p>Pick up your colors by draging them to the fields !</p></center></html>");


		introPanel.add(introTxt);
		
		introPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		// Panel to receive colors
		choicePanel = new JPanel();
		choicePanel.setBackground(Color.yellow);
		
		
		secretCombo = new JEditorPane[MAX_DIGITS];
		
		for(short i = 0;i < MAX_DIGITS;i++) {
			
			secretCombo[i] = new JEditorPane();	
			secretCombo[i].setEditable(false);
			secretCombo[i].setSize(new Dimension(30, 30));
			
			secretCombo[i].setBorder(BorderFactory.createLineBorder(Color.black));
			secretCombo[i].setDragEnabled(true);

			choicePanel.add(secretCombo[i]);	
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
		
		colorCombo = new JLabel[MAX_DIGITS];
		
		for(short i = 0;i < MAX_DIGITS;i++) {
			
			colorCombo[i] = new JLabel("B");	
			colorCombo[i].setSize(new Dimension(30, 30));
			colorCombo[i].setBackground(Color.WHITE);
			colorCombo[i].setBorder(BorderFactory.createLineBorder(Color.white));
			/**
			 * Implementing Drag N Drop for our JLabel
			 */
		    // create the new Object to handle the Drag N Drop for our JLabel
			//https://openclassrooms.com/courses/apprenez-a-programmer-en-java/le-drag-n-drop
			colorCombo[i].setTransferHandler(new TransferHandler("text"));
		      
		    // We specify the component to send data toward TransferHandler
			colorCombo[i].addMouseListener(new MouseAdapter(){
		     
		      public void mousePressed(MouseEvent e){
		        // get the JComponent            
		        JComponent lab = (JComponent)e.getSource();
		        // from component, get our object
		        TransferHandler handle = lab.getTransferHandler();
		        // do it now
		        handle.exportAsDrag(lab, e, TransferHandler.COPY_OR_MOVE);
		      }
		    });
			// ---------------------
			
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
		
		int[] fields = new int[MAX_DIGITS];
		
		for(short i = 0;i < MAX_DIGITS;i++) {
			//fields[i] = Integer.parseInt(this.jtf[i].getText());
		}
					
		return fields;
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
