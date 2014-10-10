package FogWar1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;




public class CharactersTools {
	

	public static void init(ArrayList<Player> characterlist){
		Animation[] animations = new Animation[8];
		
		for(Player p:characterlist){
			try {
				int code = 0 + (int)(Math.random() * ((2 - 0) + 1));
				switch(code){
				case 0:
					// char 1
					p.init();
					SpriteSheet spriteSheet = new SpriteSheet("sprites/character.png", 64, 64);
			        animations[0] = Player.loadAnimation(spriteSheet, 0, 1, 0);
			        animations[1] = Player.loadAnimation(spriteSheet, 0, 1, 1);
			        animations[2] = Player.loadAnimation(spriteSheet, 0, 1, 2);
			        animations[3] = Player.loadAnimation(spriteSheet, 0, 1, 3);
			        animations[4] = Player.loadAnimation(spriteSheet, 1, 9, 0);
			        animations[5] = Player.loadAnimation(spriteSheet, 1, 9, 1);
			        animations[6] = Player.loadAnimation(spriteSheet, 1, 9, 2);
			        animations[7] = Player.loadAnimation(spriteSheet, 1, 9, 3);
			        p.setAnimations(animations);
			        p.setY(110 + (int)(Math.random() * ((820 - 110) + 1)));
			        p.setX(110 + (int)(Math.random() * ((1000 - 110) + 1)));
			        break;
				case 1:    
			        // char 2 
			        animations = new Animation[8];
					p.init();
					SpriteSheet spriteSheet2 = new SpriteSheet("sprites/character2.png", 64, 64);
			        animations[0] = Player.loadAnimation(spriteSheet2, 0, 1, 8);
			        animations[1] = Player.loadAnimation(spriteSheet2, 0, 1, 9);
			        animations[2] = Player.loadAnimation(spriteSheet2, 0, 1, 10);
			        animations[3] = Player.loadAnimation(spriteSheet2, 0, 1, 11);
			        animations[4] = Player.loadAnimation(spriteSheet2, 1, 9, 8);
			        animations[5] = Player.loadAnimation(spriteSheet2, 1, 9, 9);
			        animations[6] = Player.loadAnimation(spriteSheet2, 1, 9, 10);
			        animations[7] = Player.loadAnimation(spriteSheet2, 1, 9, 11);
			        p.setAnimations(animations);
			        p.setY(110 + (int)(Math.random() * ((820 - 110) + 1)));
			        p.setX(110 + (int)(Math.random() * ((1000 - 110) + 1)));
			        break;
				case 2:  
			        // char 3 
			        animations = new Animation[8];
					p.init();
					SpriteSheet spriteSheet3 = new SpriteSheet("sprites/character3.png", 64, 64);
			        animations[0] = Player.loadAnimation(spriteSheet3, 0, 1, 8);
			        animations[1] = Player.loadAnimation(spriteSheet3, 0, 1, 9);
			        animations[2] = Player.loadAnimation(spriteSheet3, 0, 1, 10);
			        animations[3] = Player.loadAnimation(spriteSheet3, 0, 1, 11);
			        animations[4] = Player.loadAnimation(spriteSheet3, 1, 9, 8);
			        animations[5] = Player.loadAnimation(spriteSheet3, 1, 9, 9);
			        animations[6] = Player.loadAnimation(spriteSheet3, 1, 9, 10);
			        animations[7] = Player.loadAnimation(spriteSheet3, 1, 9, 11);
			        p.setAnimations(animations);
			        p.setY(110 + (int)(Math.random() * ((820 - 110) + 1)));
			        p.setX(110 + (int)(Math.random() * ((1000 - 110) + 1)));
			        break;
			        default:break;
				}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
