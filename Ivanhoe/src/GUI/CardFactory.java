package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
			BufferedImage image = ImageIO.read(c.getClass().getResourceAsStream("/images/"+(c.getValue()==0?"0":c.toString())+".jpeg"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
	
	public static ImageIcon getShieldIcon(){
		try {                
			Card c = new Card();
			BufferedImage image = ImageIO.read(c.getClass().getResourceAsStream("/images/A13.jpeg"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(28, 40, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
	
	public static ImageIcon getStunIcon(){
		try {   
			Card c = new Card();
			BufferedImage image = ImageIO.read(c.getClass().getResourceAsStream("/images/A14.jpeg"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(28, 40, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
}
