package main.com.oc.master.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.oc.master.model.GameMode;
import main.com.oc.master.model.observer.GameObservable;
import main.com.oc.master.model.observer.GameObserver;
import main.com.oc.master.utils.KeyboardAction;

/**
 * Class personalizing Dialog
 * @author John
 */
public class ComboDialog extends JDialog {

	private static final long serialVersionUID = 3622715224324910278L;
	private static final Logger logger = LogManager.getLogger();
	
	// Prepare the JformattedTextField to receive our combo :
	private JFormattedTextField jtf[] ;
	
	
	private ArrayList<GameObserver> lObs = new ArrayList<GameObserver>();

	public ComboDialog(String title, final short maxDigits){

		super();

		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.setLayout(new BorderLayout());
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
		
		JLabel secret = new JLabel();
		
		secret.setText("Please, do choose a combination");
		
		dialogPanel.add(secret);
		
		jtf = new JFormattedTextField[maxDigits];

		
		try{
			MaskFormatter nb = new MaskFormatter("#");
		
			for(short i = 0;i < maxDigits;i++) {
	
				jtf[i] = new JFormattedTextField(nb);	
				jtf[i].setPreferredSize(new Dimension(50, 30));
				jtf[i].setForeground(Color.BLUE);
				
				jtf[i].addKeyListener(new KeyboardAction());
				
				dialogPanel.add(jtf[i]);
			}
		} catch(ParseException e){
			logger.error("Error Refreshing game panel - double...");
			e.printStackTrace();
			
		}
		
		JButton ok = new JButton("Validate");
		
		this.add(dialogPanel);
		this.add(ok,BorderLayout.SOUTH);
		

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// We are going to close the dialog box here and send the GamePanel the user combo !
				notifyObserver();
				setVisible(false);
			}



		});


	}

	/**
	 * Method returning the content of the fields containing the proposition :
	 * @return fields
	 */
	public int[] getFields() {
		
		int[] fields = new int[GameObservable.MAX_DIGITS];
		
		for(short i = 0;i < GameObservable.MAX_DIGITS;i++) {

			// Make sure we have an integer in each field
			try {
				fields[i] = Integer.parseInt(this.jtf[i].getText());
			} catch(NumberFormatException  e) {
				
				return null;
			}
		}
					
		return fields;
	}

	public void addObserver(GameObserver o) {

		lObs.add(o);
	}

	public void notifyObserver() {

		for(GameObserver obs : this.lObs )
			obs.update(getFields(), GameMode.ATTACK);

	}

}