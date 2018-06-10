package main.com.oc.master.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Gathering logger implementation
 * @author bob
 * @version 1.2
 */
public class MyLogger {

	
	// Few links to check :
	// https://stackify.com/log4j2-java/
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	/**
	 * Getting logger -- @TODO : name of class or package ??
	 * @return
	 */
	public static Logger getLogger() {
		
		return LOGGER;
		
	}
	
	/**
	 * Reconfiguring the logger depending on parameter
	 * case debug mode is enabled - not the default !
	 * @see https://logging.apache.org/log4j/2.x/manual/customconfig.html
	 */
	public static void reconfigureLogger() {
		
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = (Configuration) ctx.getConfiguration();
		
		LOGGER.debug("Retrieving current configuration from property file : " + config.getProperties(""));
		
		//config.setProperty(arg0, arg1);
		//.addLogger("com", Level.INFO, (Filter) null);
	}
}
