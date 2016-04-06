package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gameEntities.Card;

public class CardFactory {

	
	public static ImageIcon getCard(Card c){
		if (c==null){
			return null;
		}
		try {                
			BufferedImage image = ImageIO.read(new File("Resources/"+(c.getValue()==0?"0":c.toString())+".jpeg"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
	
	public static ImageIcon getShieldIcon(){
		try {                
			BufferedImage image = ImageIO.read(new File("Resources/A13.jpeg"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(28, 40, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
	
	public static ImageIcon getStunIcon(){
		try {                
			BufferedImage image = ImageIO.read(new File("Resources/A14.jpeg"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(28, 40, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
}
