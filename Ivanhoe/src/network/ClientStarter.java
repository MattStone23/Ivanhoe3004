package network;

import java.net.InetAddress;
import java.util.Scanner;

import Util.config;

//import java.util.Scanner;

import Util.timer;

public class ClientStarter {

	
	//private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Client c = new Client("134.117.28.215",config.PORT);
		String in="temp";
		Server s = null;
		Scanner get = new Scanner(System.in); 
		while (!"JOIN".equals(in)&&!"HOST".equals(in)){
			System.out.println("Would you like to Host a game a play a game?\nHOST\nJOIN");
			in = get.nextLine();
		}
		
		if ("HOST".equals(in)){
			System.out.println("STARTING SERVER");
			s = new Server();
			timer.wait(1);
			in = "0.0.0.0";
		}
		else{
			boolean validIP=false;
			while (!validIP){
				System.out.println("Please enter the Server's IP");
				in = get.nextLine();
				//TODO validIP = isValidIP(in);
				validIP=true;
			}
		}
		
		
		Client c = new Client(in,config.PORT);
		timer.wait(1);
		c.sendMessage("CONNECT");
//		
		
		
		while(!in.equals("quit")){
			System.out.print("INPUT:");
			in=get.nextLine();
			c.sendMessage(in);
		}
		c.stop();
		s.shutdown();
		
	}

}
