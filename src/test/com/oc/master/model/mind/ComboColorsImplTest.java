package test.com.oc.master.model.mind;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import main.com.oc.master.model.mind.ComboColorsImpl;

class ComboColorsImplTest {
	
	static final Logger LOGGER = LogManager.getLogger(ComboColorsImplTest.class);
	
	@Test
	void testCompareCombo() {
		
		ComboColorsImpl comboCol = new ComboColorsImpl();
		
		String source = "CBWP";
		String secret = "PBOO";
		
		// result size always 2
		int REDIX=10;// redix 10 is for decimal number, for hexa use redix 16 
		
		char[] result = new char[2];
		result[0] = Character.forDigit(1,REDIX);   
		result[1] = Character.forDigit(1,REDIX);  
		
		LOGGER.debug("Comparing : " + (new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray())))
				+ " with : " + new String(result));
		assertTrue("source equals to target/secret combo", 
				(new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray()))).equals(new String(result)));

		// second try
		source = "OGGG";
		secret = "PBOO";
		
		result = new char[2];
		result[0] = Character.forDigit(0,REDIX);   
		result[1] = Character.forDigit(1,REDIX);  
		
		LOGGER.debug("Comparing : " + (new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray())))
				+ " with : " + new String(result));
		assertTrue("source equals to target/secret combo", 
				(new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray()))).equals(new String(result)));
		
		// 3rd try
		source = "OGPB";
		secret = "OGPB";
		
		result = new char[2];
		result[0] = Character.forDigit(4,REDIX);   
		result[1] = Character.forDigit(0,REDIX);  
		
		LOGGER.debug("Comparing : " + (new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray())))
				+ " with : " + new String(result));
		assertTrue("source equals to target/secret combo", 
				(new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray()))).equals(new String(result)));
				
		
		// 4th try 
		source = "BLMC";
		secret = "OGPW";
		
		result = new char[2];
		result[0] = Character.forDigit(0,REDIX);   
		result[1] = Character.forDigit(0,REDIX);  
		
		LOGGER.debug("Comparing : " + (new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray())))
				+ " with : " + new String(result));
		assertTrue("source equals to target/secret combo", 
				(new String(comboCol.compareCombo(source.toCharArray(), secret.toCharArray()))).equals(new String(result)));
				
	
	}

}
