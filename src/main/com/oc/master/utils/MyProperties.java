package main.com.oc.master.utils;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Class used to read and manipulate Games properties
 * @author bob
 * reading the conf/mastermind.properties file
 */
public class MyProperties {

	/**
	 * Constructor class
	 */
	public MyProperties() {
		
		
	}
	
	public void readProperties() {
		
		Configurations configs = new Configurations();

		try
		{
		    Configuration config = configs.properties(new File("conf/mastermind.properties"));
		    MyLogger.getLogger().trace("Loading configuration file with parameter : " + config.getInt("master.combo_size"));
		}
		catch(ConfigurationException cex)
		{
		    // loading of the configuration file failed
		}
	}

	
}
