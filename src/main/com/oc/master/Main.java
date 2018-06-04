package main.com.oc.master;

import main.com.oc.master.model.GameModel;
import main.com.oc.master.model.observer.GameObservable;
import main.com.oc.master.utils.Keyboard;
import main.com.oc.master.utils.MyLogger;
import main.com.oc.master.utils.MyProperties;
import main.com.oc.master.view.SwingWindow;

/**
 * Main class to launch program via console or Swing or FX
 * @author bob
 * @version 1.0.1
 */
public class Main {
	
	
	public static void main(String[] args) {
		
		
		// Reading properties file here :
		MyProperties myProp = new MyProperties();
		myProp.readProperties();
		
		
		// start to ask whether the user needs to use the game in console mode or swing or fx :
		
		short gameInterface = 0;
		
		do {
		
			System.out.println("Do you wish to launch console (1), Swing (2) or Fx (3) mode for the game ?");
			
			gameInterface = (short)Keyboard.readInt();
			
			if (gameInterface != 1 && gameInterface != 2)
				gameInterface = 0;
		
		} while (gameInterface == 0);

		MyLogger.getLogger().trace("Game Interface chose " + gameInterface);
		
		/**
		 * Pick up the right interface based on the selection :
		 */
		switch(gameInterface) {
			
			case 1 : 
				// console case
				MyLogger.getLogger().trace("Launching console mode");
				
				
				break;
			case 2 :
				// case Swing
				MyLogger.getLogger().trace("Launching Swing mode");
				GameObservable model = new GameModel();
				SwingWindow wind = new SwingWindow(model);
				wind.setVisible(true);
				break;
			case 3 : 
				// case FX -- TODO 
				break;
			
			default : // no such option !
				MyLogger.getLogger().warn("What is this option ??");
				break;
			
		}
		
		System.out.println("Bye bye !");
		
		/*logger.trace("msg de trace"); // E/S methods
		logger.debug("msg de debogage"); // Display values of datas
		logger.info("msg d'information"); //loading of conf file, beg/end long process
		logger.warn("msg d'avertissement"); // error login, invalid data
		logger.error("msg d'erreur"); // all throwned exceptions
		logger.fatal("msg d'erreur fatale");  // error DB, exceptions which stop the program
		*/
		
	}

}
