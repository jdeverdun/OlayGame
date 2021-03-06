package Dance1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		for(int i = 0;i<characterlist.size();i++){
			Player p = characterlist.get(i);
			try {
				int code = 0 + (int)(Math.random() * ((listanim.size()-1 - 0) + 1));
				p.init();
				p.setAnimations(listanim.get(code));
				p.setY(167.0f + ((float)Math.random() * ((749.0f - 167.0f) + 1.0f)));
				p.setX(172.0f + ((float)Math.random() * ((871.0f - 172.0f) + 1.0f)));

				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static LinkedList<Integer[]> parseDanceFile(String file) throws FileNotFoundException, IOException{
		LinkedList<Integer[]> steps = new LinkedList<Integer[]>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line = br.readLine();
	        String[] content = line.split("@@");
	        steps.add(new Integer[]{Integer.parseInt(content[0]),Integer.parseInt(content[1])});
	        while ((line = br.readLine()) != null) {
	            content = line.split("@@");
	            steps.add(new Integer[]{Integer.parseInt(content[0]),Integer.parseInt(content[1])});
	        }
	    }
		return steps;
	}
	
	public static LinkedList<Float[]> parseDanceFileSoundRef(String file) throws FileNotFoundException, IOException{
		LinkedList<Float[]> steps = new LinkedList<Float[]>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line = br.readLine();
	        String[] content = line.split("@@");
	        steps.add(new Float[]{Float.parseFloat(content[0]),Float.parseFloat(content[1])});
	        while ((line = br.readLine()) != null) {
	            content = line.split("@@");
	            steps.add(new Float[]{Float.parseFloat(content[0]),Float.parseFloat(content[1])});
	        }
	    }
		return steps;
	}

}
