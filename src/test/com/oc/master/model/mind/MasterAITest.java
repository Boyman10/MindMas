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
	void testMakeMove() {

		// Passing nbColors and cSize
		MasterAI masterAI = new MasterAI((byte) 6, (byte) 4);

		LOGGER.debug("Secret Combo to find : \"WBGO\"");
		
		// TESTING A CLUE - Empty at first - Secret Combo : "WBGO"
		char[] clue = new char[2];
		char[] AIResult = masterAI.makeMove(clue);

		// Should be this result - public final static String vColors = "WBOYGIPRSMC";
		char[] result = { 'W', 'W', 'W', 'W' };

		LOGGER.debug("Comparing : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));

		// 1 NEXT MOVE NOW - same game :
		clue[0] = '1';
		clue[1] = '0';

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'B';
		result[3] = 'B';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 2 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));
		
		
		// 2  NEXT MOVE NOW - same game :
		clue[0] = '2';
		clue[1] = '0';

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'O';
		result[3] = 'O';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 3 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));
		
		
		// 3  NEXT MOVE NOW - same game :
		clue[0] = '2';
		clue[1] = '1';

		result[0] = 'W';
		result[1] = 'B';
		result[2] = 'O';
		result[3] = 'O';

		AIResult = masterAI.makeMove(clue);
		
		LOGGER.debug("Comparing 3 : " + Arrays.toString(result) + " with : " + Arrays.toString(AIResult));

		assertTrue("Comparing result of Algo", Arrays.equals(result, AIResult));		
	}

}
