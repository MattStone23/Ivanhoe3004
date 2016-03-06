package network;

import java.util.Scanner;

//import java.util.Scanner;

import Util.timer;

public class ClientStarter {

	
	//private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c = new Client();
		timer.wait(1);
		c.sendMessage("CONNECT");
//		
		
		String in="temp";
		Scanner get = new Scanner(System.in); 
		while(!in.equals("quit")){
			System.out.print("INPUT:");
			in=get.nextLine();
			c.sendMessage(in);
		}
		c.stop();
		
	}

}
