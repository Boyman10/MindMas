package main.com.oc.master.utils;
import java.io.* ;

public class Keyboard {
	/**
	 * Specific class to read from Keyboard
	 * @author Delannoy
	 */


	public static String readString ()   
	{
		String read_line = null ;

		try
		{
			InputStreamReader reader = new InputStreamReader (System.in) ;
			BufferedReader entry = new BufferedReader (reader) ;
			read_line = entry.readLine() ;
		}
		catch (IOException err)
		{
			System.exit(0) ; 
		}
		return read_line ;
	}

	public static char readChar ()  
	{
		char c = 0 ;   // value to read

		try
		{
			String read_line = readString() ;
			c = read_line.charAt(0) ;
		}
		catch (IndexOutOfBoundsException err)
		{
			System.out.println ("*** data input error ***") ;
			System.exit(0) ;
		}
		return c ;
	}

	public static float readFloat ()   
	{
		float x = 0 ;   

		try
		{
			String read_line = readString() ;
			x = Float.parseFloat(read_line) ;
		}
		catch (NumberFormatException err)
		{
			System.out.println ("*** data input error ***") ;
			System.exit(0) ;
		}
		return x ;
	}

	public static double readDouble ()   
	{
		double x = 0 ;  
		try
		{
			String read_line = readString() ;
			x = Double.parseDouble(read_line) ;
		}
		catch (NumberFormatException err)
		{
			System.out.println ("*** data error ***") ;
			System.exit(0) ;
		}
		return x ;
	}
	public static int readInt ()       
	{
		int n = 0 ;   
		try
		{
			String read_line = readString() ;
			n = Integer.parseInt(read_line) ;
		}
		catch (NumberFormatException err)
		{
			System.out.println ("*** data error ***") ;
			System.exit(0) ;
		}
		return n ;
	}
}
