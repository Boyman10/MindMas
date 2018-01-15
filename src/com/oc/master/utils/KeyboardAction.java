package com.oc.master.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyboardAction implements KeyListener{

	private JFormattedTextField jtf;
	static final Logger logger = LogManager.getLogger();

	public void keyReleased(KeyEvent event) {

		logger.trace("Key Released " + event.getSource().getClass().getName());
		
		if (event.getSource() instanceof JFormattedTextField)
			jtf = (JFormattedTextField) event.getSource();
		
		if(!isNumeric(event.getKeyChar()))
			jtf.setText(jtf.getText().replace(String.valueOf(event.getKeyChar()), ""));      	
	}

	/**
	 * Check the authenticity of the field
	 * @param carac
	 * @return boolean
	 */
	private boolean isNumeric(char carac){
		try {
			Integer.parseInt(String.valueOf(carac));
		}
		catch (NumberFormatException e) {
			return false;            
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}