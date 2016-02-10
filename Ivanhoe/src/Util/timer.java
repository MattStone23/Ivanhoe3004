package Util;

public class timer {
	public static void wait(int seconds){
		try{
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie){	
		}
	}
}
