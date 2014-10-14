package Run2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import lesson14.CharactersToolsGlobal;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;




public class CharactersTools {


	public static void init(ArrayList<Player> characterlist){
		ArrayList<Animation[]> listanim = CharactersToolsGlobal.getCharAnimations();
		for(Player p:characterlist){
			try {
				int code = 0 + (int)(Math.random() * ((listanim.size()-1 - 0) + 1));
				p.init();
				p.setAnimations(listanim.get(code));
				p.setY(110 + (int)(Math.random() * ((820 - 110) + 1)));
				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
