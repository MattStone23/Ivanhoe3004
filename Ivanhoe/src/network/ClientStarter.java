package network;

import java.util.Scanner;

import Util.timer;

public class ClientStarter {

	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c = new Client();
		String[] commands = {"CONNECT",
							"STARTGAME",
							"STARTTOURN|R",
							"DRAW",
							"PLAY|R|4",
							"PLAY|A|12|3",
							"ENDTURN"};
		timer.wait(1);
		for (String x : commands){
			System.out.println("\t\t"+x);
			c.sendMessage(x);
			timer.wait(1);
		}
		
	}

}
