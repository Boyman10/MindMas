package test.com.oc.master.model.mind;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import main.com.oc.master.model.mind.MasterAI;

class MasterAITest {
	static final Logger LOGGER = LogManager.getLogger(MasterAITest.class);

	@Test
	void testMakeMove1() {
		LOGGER.debug("--------SCENARIO 1---------");
		// Passing nbColors and cSize
		MasterAI masterAI = new MasterAI((byte) 6, (byte) 4);

		LOGGER.debug("Secret Combo to find : \"WBGO\"");
		
		// TESTING A CLUE - Empty at first - Secret Combo : "WBGO"
		byte[] clue = new byte[2];
		char[] AIResult = masterAI.makeMove(clue);

		// Should be this result - public final static String vColors = "WBOYGIPRSMC";
		char[] result = { 'W', 'W', 'W', 'W' };

		LOGGER.debug("Comparing 1 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));

		// 1 NEXT MOVE NOW - same game : Secret Combo : "WBGO"
		clue[0] = 1;
		clue[1] = 0;

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'B';
		result[3] = 'B';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 2 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));
		
		
		// 2  NEXT MOVE NOW - same game : Secret Combo : "WBGO"
		clue[0] = 2;
		clue[1] = 0;

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'O';
		result[3] = 'O';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 3 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));
		
		
		// 3  NEXT MOVE NOW - same game : Secret Combo : "WBGO"
		clue[0] = 3;
		clue[1] = 0;

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'O';
		result[3] = 'Y';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 4 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));		
		
		// 4  NEXT MOVE NOW - same game : Secret Combo : "WBGO"
		clue[0] = 2; // decrement ! -> remove the current submitted color
		clue[1] = 1; // last color submitted -> move it to the right !

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'G'; // replace the bad spot with another color
		result[3] = 'O';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 5 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));	
		
		
	}
	
	@Test
	void testMakeMove2() {
		
		LOGGER.debug("--------SCENARIO 2---------");
		
		// Passing nbColors and cSize
		MasterAI masterAI = new MasterAI((byte) 10, (byte) 5);

		LOGGER.debug("Secret Combo to find : \"YOCMB\"");
		
		// TESTING A CLUE - Empty at first - Secret Combo : "YOCMB"
		byte[] clue = new byte[2];
		char[] AIResult = masterAI.makeMove(clue);

		// Should be this result - public final static String vColors = "WBOYGIPRSMC";
		char[] result = { 'W', 'W', 'W', 'W' , 'W'};

		LOGGER.debug("Comparing 1 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));

		// 1 NEXT MOVE NOW - same game : Secret Combo : "YOCMB"
		clue[0] = 0;
		clue[1] = 0;

		result[0] = 'B';
		result[1] = 'B';
		result[2] = 'B';
		result[3] = 'B';
		result[4] = 'B';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 2 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));
		
		
		// 2  NEXT MOVE NOW - same game : Secret Combo : "YOCMB"
		clue[0] = 1;
		clue[1] = 0;

		result[0] = 'B';
		result[1] = 'O';
		result[2] = 'O';
		result[3] = 'O';
		result[4] = 'O';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 3 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));
		
		
		// 3  NEXT MOVE NOW - same game : Secret Combo : "YOCMB"
		clue[0] = 1;
		clue[1] = 1;

		result[0] = 'O';
		result[1] = 'B';
		result[2] = 'Y';
		result[3] = 'Y';
		result[4] = 'Y';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 4 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));		
		
		// 4  NEXT MOVE NOW - same game : Secret Combo : "YOCMB"
		clue[0] = 0; // decrement ! -> remove the current submitted color
		clue[1] = 3; // last color submitted -> move it to the right !

		result[0] = 'Y';// replace the bad spot with another color
		result[1] = 'O';
		result[2] = 'B'; 
		result[3] = 'G';
		result[4] = 'G';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 5 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));	
		
		// 5  NEXT MOVE NOW - same game : Secret Combo : "YOCMB"
		clue[0] = 2; // decrement ! -> remove the current submitted color
		clue[1] = 1; // last color submitted -> move it to the right !

		result[0] = 'Y'; // keep that one and set its index to 1
		result[1] = 'O'; // set its last spot at 3
		result[2] = 'I'; // new one
		result[3] = 'B'; // set its last spot at 3
		result[4] = 'I'; // new one

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 6 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));	
				
		
	}
}
