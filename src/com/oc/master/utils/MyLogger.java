package com.oc.master.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gathering logger implementation
 * @author bob
 * @version 1.2
 */
public class MyLogger {

	private static final Logger logger = LogManager.getLogger();
	
	/**
	 * Getting logger -- @TODO : name of class or package ??
	 * @return
	 */
	public static Logger getLogger() {
		
		return logger;
		
	}
}
