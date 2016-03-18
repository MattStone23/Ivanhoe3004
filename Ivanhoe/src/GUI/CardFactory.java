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
			BufferedImage image = ImageIO.read(new File("Resources/Card"+(c.getValue()==0?"":c.toString())+".png"));
			ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(45, 60, Image.SCALE_DEFAULT));
			return imageIcon;
		} catch (IOException ex) {
			System.out.println("exception :"+ex);
		}
		return null;
	}
}
