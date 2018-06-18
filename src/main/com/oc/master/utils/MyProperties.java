package main.com.oc.master.utils;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Class used to read and manipulate Games properties
 * 
 * @author bob reading the conf/mastermind.properties file
 */
public class MyProperties {

	public static int COMBO_SIZE;
	public static int NB_COLORS;
	
	private Configurations configs;
	private Configuration config;
	private File myFile;

	/**
	 * Constructor class
	 */
	public MyProperties() {
		
		configs = new Configurations();
		myFile = new File("conf/mastermind.properties");
		
		try {
			config = configs.properties(myFile);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void readProperties() {


		MyLogger.getLogger()
				.trace("Loading configuration file with parameter : " + config.getInt("master.combo_size"));

		COMBO_SIZE = config.getInt("master.combo_size");
		NB_COLORS = config.getInt("master.nb_colors");
	}

	/**
	 * Updating properties within file
	 * 
	 * @param vSet
	 */
	public void updateProperties(Map<String, Integer> vSet) throws ConfigurationException {


		Set<Entry<String, Integer>> setHm = vSet.entrySet();
		Iterator<Entry<String, Integer>> it = setHm.iterator();

		while (it.hasNext()) {

			Entry<String, Integer> e = it.next();
			config.setProperty(e.getKey(), e.getValue());
			MyLogger.getLogger()
			.trace("updating parameter : " + e.getKey() + " with value : " + e.getValue());
			
		}

	}

	/**
	 * Method persisting changes to DB
	 * @see https://commons.apache.org/proper/commons-configuration/userguide/quick_start.html
	 * @see https://commons.apache.org/proper/commons-configuration/userguide/howto_filebased.html
	 * @param hm
	 */
	public void persistUpdateProperties(Map<String, Integer> vSet) {
		
		Parameters params = new Parameters();
		Set<Entry<String, Integer>> setHm = vSet.entrySet();
		Iterator<Entry<String, Integer>> it = setHm.iterator();

		try
		{
		    // obtain the configuration
		    FileBasedConfigurationBuilder<FileBasedConfiguration> builder = 
		    		new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    		.configure(params.fileBased()
		            .setFile(myFile));
		    
		    Configuration propConfig = builder.getConfiguration();

			while (it.hasNext()) {

				Entry<String, Integer> e = it.next();
				propConfig.setProperty(e.getKey(), e.getValue());
				MyLogger.getLogger()
				.trace("updating parameter : " + e.getKey() + " with value : " + e.getValue());
				
			}


		    // save configuration
		    builder.save();
		}
		catch (ConfigurationException cex)
		{
		    // Something went wrong
		}
		
	}
	
	

}
