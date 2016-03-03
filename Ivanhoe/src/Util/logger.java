package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class logger {

	private static boolean ON = false;
	private static String newline = System.lineSeparator();
//	private static boolean ON = true;
	private BufferedWriter out;
	
	public static void println(String message){
		if (ON) System.out.println(message);
	}
	
	public logger(String fileName){
		open(fileName);
	}
	public void write(String tempOut){
		try{
			out.append(tempOut+newline);
			out.flush();
		}
		catch (IOException e){
			System.out.println("Failed to log network data");
		}
	}
	
	public void open(String fileName){
		try{
			File path = new File ("logs");
			if (!path.exists()) {
				path.mkdirs();
			} 
			
			File file = new File("logs\\"+fileName+"_"+System.currentTimeMillis() + ".txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			out = new BufferedWriter(fw);
			
			out.append("NETWORK LOGFILE"+newline);
		}
		catch (IOException e){
			System.out.println("Failed to log network data");
		}
	}
	public void close() throws IOException{
		out.flush();
		out.close();
	}
}
