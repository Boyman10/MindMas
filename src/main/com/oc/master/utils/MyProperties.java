package main.com.oc.master.utils;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
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

	/**
	 * Constructor class
	 */
	public MyProperties() {
		
		configs = new Configurations();
		try {
			config = configs.properties(new File("conf/mastermind.properties"));
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
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
	 * @param hm
	 */
	public void persistUpdateProperties(Map<String, Integer> hm) {
		
		try
		{
		    // obtain the configuration
		    FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder("paths.xml");
		    XMLConfiguration config = builder.getConfiguration();
		    
		    // update property
		    config.addProperty("newProperty", "newValue");

		    // save configuration
		    builder.save();
		}
		catch (ConfigurationException cex)
		{
		    // Something went wrong
		}
		
	}
	
	

}
