package Util;

public class logger {

	private static boolean ON = false;
//	private static boolean ON = true;
	
	public static void println(String message){
		if (ON) System.out.println(message);
	}
}
