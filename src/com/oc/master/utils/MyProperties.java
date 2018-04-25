package com.oc.master.utils;

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
		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName("usergui.properties"));
		try
		{
		    Configuration config = builder.getConfiguration();
		    ...
		}
		catch(ConfigurationException cex)
		{
		    // loading of the configuration file failed
		}
	}
	
}
