package com.oc.master.model.mind.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComboException extends Exception {

	private static final long serialVersionUID = 4093407776276105343L;

	static final Logger logger = LogManager.getLogger("ComboException");
	
	
	public ComboException(String message) {
		
		super(message);
		logger.error("The submitted combo does not match the game difficulty !");
	}
}
