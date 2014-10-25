package lesson14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Run2.Player;




public class CharactersToolsGlobal {
	public static ArrayList<Animation[]> CHARACTER_ANIMATIONS_LIST = null;
	public static ArrayList<Animation> CLOUD_ANIMATIONS_LIST = null;
	private static ArrayList<Animation[]> ARROW_ANIMATIONS_LIST = null;
	public static Animation EXPLOSION_ANIM = null;

	public static ArrayList<Animation[]> getCharAnimations(){
		if(CHARACTER_ANIMATIONS_LIST!=null)
			return CHARACTER_ANIMATIONS_LIST;
		EXPLOSION_ANIM = loadExplosionAnim("sprites/explosion.png",256,128);
		ArrayList<Animation[]> listanim = new ArrayList<Animation[]>();
		try{
			Animation[] animations = new Animation[8];

			SpriteSheet spriteSheet = new SpriteSheet("sprites/character.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
			animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
			animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
			animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
			animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
			animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
			animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
			animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
			listanim.add(animations);
			// char 2 
			animations = new Animation[8];
			SpriteSheet spriteSheet2 = new SpriteSheet("sprites/character2.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet2, 0, 1, 8);
			animations[1] = loadAnimation(spriteSheet2, 0, 1, 9);
			animations[2] = loadAnimation(spriteSheet2, 0, 1, 10);
			animations[3] = loadAnimation(spriteSheet2, 0, 1, 11);
			animations[4] = loadAnimation(spriteSheet2, 1, 9, 8);
			animations[5] = loadAnimation(spriteSheet2, 1, 9, 9);
			animations[6] = loadAnimation(spriteSheet2, 1, 9, 10);
			animations[7] = loadAnimation(spriteSheet2, 1, 9, 11);
			listanim.add(animations);
			// char 3 
			animations = new Animation[8];
			SpriteSheet spriteSheet3 = new SpriteSheet("sprites/character3.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet3, 0, 1, 8);
			animations[1] = loadAnimation(spriteSheet3, 0, 1, 9);
			animations[2] = loadAnimation(spriteSheet3, 0, 1, 10);
			animations[3] = loadAnimation(spriteSheet3, 0, 1, 11);
			animations[4] = loadAnimation(spriteSheet3, 1, 9, 8);
			animations[5] = loadAnimation(spriteSheet3, 1, 9, 9);
			animations[6] = loadAnimation(spriteSheet3, 1, 9, 10);
			animations[7] = loadAnimation(spriteSheet3, 1, 9, 11);
			listanim.add(animations);
			// char 4 
			animations = new Animation[8];
			SpriteSheet spriteSheet4 = new SpriteSheet("sprites/character4.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet4, 0, 1, 8);
			animations[1] = loadAnimation(spriteSheet4, 0, 1, 9);
			animations[2] = loadAnimation(spriteSheet4, 0, 1, 10);
			animations[3] = loadAnimation(spriteSheet4, 0, 1, 11);
			animations[4] = loadAnimation(spriteSheet4, 1, 9, 8);
			animations[5] = loadAnimation(spriteSheet4, 1, 9, 9);
			animations[6] = loadAnimation(spriteSheet4, 1, 9, 10);
			animations[7] = loadAnimation(spriteSheet4, 1, 9, 11);
			listanim.add(animations);
			// char  5
			animations = new Animation[8];
			SpriteSheet spriteSheet5 = new SpriteSheet("sprites/character5.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet5, 0, 1, 8);
			animations[1] = loadAnimation(spriteSheet5, 0, 1, 9);
			animations[2] = loadAnimation(spriteSheet5, 0, 1, 10);
			animations[3] = loadAnimation(spriteSheet5, 0, 1, 11);
			animations[4] = loadAnimation(spriteSheet5, 1, 9, 8);
			animations[5] = loadAnimation(spriteSheet5, 1, 9, 9);
			animations[6] = loadAnimation(spriteSheet5, 1, 9, 10);
			animations[7] = loadAnimation(spriteSheet5, 1, 9, 11);
			listanim.add(animations);
			// char 6 
			animations = new Animation[8];
			SpriteSheet spriteSheet6 = new SpriteSheet("sprites/character6.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet6, 0, 1, 8);
			animations[1] = loadAnimation(spriteSheet6, 0, 1, 9);
			animations[2] = loadAnimation(spriteSheet6, 0, 1, 10);
			animations[3] = loadAnimation(spriteSheet6, 0, 1, 11);
			animations[4] = loadAnimation(spriteSheet6, 1, 9, 8);
			animations[5] = loadAnimation(spriteSheet6, 1, 9, 9);
			animations[6] = loadAnimation(spriteSheet6, 1, 9, 10);
			animations[7] = loadAnimation(spriteSheet6, 1, 9, 11);
			listanim.add(animations);
			// char 7
			animations = new Animation[8];
			SpriteSheet spriteSheet7 = new SpriteSheet("sprites/character7.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet7, 0, 1, 0);
			animations[1] = loadAnimation(spriteSheet7, 0, 1, 1);
			animations[2] = loadAnimation(spriteSheet7, 0, 1, 2);
			animations[3] = loadAnimation(spriteSheet7, 0, 1, 3);
			animations[4] = loadAnimation(spriteSheet7, 1, 9, 0);
			animations[5] = loadAnimation(spriteSheet7, 1, 9, 1);
			animations[6] = loadAnimation(spriteSheet7, 1, 9, 2);
			animations[7] = loadAnimation(spriteSheet7, 1, 9, 3);
			listanim.add(animations);
			// char 8
			animations = new Animation[8];
			SpriteSheet spriteSheet8 = new SpriteSheet("sprites/character8.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet8, 0, 1, 0);
			animations[1] = loadAnimation(spriteSheet8, 0, 1, 1);
			animations[2] = loadAnimation(spriteSheet8, 0, 1, 2);
			animations[3] = loadAnimation(spriteSheet8, 0, 1, 3);
			animations[4] = loadAnimation(spriteSheet8, 1, 9, 0);
			animations[5] = loadAnimation(spriteSheet8, 1, 9, 1);
			animations[6] = loadAnimation(spriteSheet8, 1, 9, 2);
			animations[7] = loadAnimation(spriteSheet8, 1, 9, 3);
			listanim.add(animations);
			CHARACTER_ANIMATIONS_LIST = listanim;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listanim;
	}



	public static ArrayList<Animation> getCloudAnimations(){
		if(CLOUD_ANIMATIONS_LIST!=null)
			return CLOUD_ANIMATIONS_LIST;
		ArrayList<Animation> listanim = new ArrayList<Animation>();
		try{
			Animation animations = new Animation();

			SpriteSheet spriteSheet = new SpriteSheet("sprites/cloud1.png", 158, 74);
			animations = loadAnimation(spriteSheet, 0, 1, 0);
			listanim.add(animations);
			SpriteSheet spriteSheet2 = new SpriteSheet("sprites/cloud2.png", 158, 74);
			animations = loadAnimation(spriteSheet2, 0, 1, 0);
			listanim.add(animations);
			CLOUD_ANIMATIONS_LIST = listanim;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listanim;
	}

	public static ArrayList<Animation[]> getArrowAnimations(){
		if(ARROW_ANIMATIONS_LIST!=null)
			return ARROW_ANIMATIONS_LIST;
		ArrayList<Animation[]> listanim = new ArrayList<Animation[]>();
		try{
			Animation animations[] = new Animation[10];

			SpriteSheet spriteSheet = new SpriteSheet("sprites/arrowleft.png", 64, 64);
			animations[0] = loadAnimation(spriteSheet, 0, 1, 3);
			animations[1] = loadAnimation(spriteSheet, 0, 1, 2);
			animations[2] = loadAnimation(spriteSheet, 0, 1, 1);
			animations[3] = loadAnimation(spriteSheet, 0, 1, 0);
			animations[4] = loadAnimation(spriteSheet, 1, 2, 3);
			animations[5] = loadAnimation(spriteSheet, 1, 2, 2);
			animations[6] = loadAnimation(spriteSheet, 1, 2, 1);
			animations[7] = loadAnimation(spriteSheet, 1, 2, 0);
			animations[8] = loadAnimationYLoop(spriteSheet, 0, 3, 0,  400);
			animations[9] = loadAnimationYLoop(spriteSheet, 1, 3, 0, 400);
			listanim.add(animations);
			Animation animations2[] = new Animation[10];
			SpriteSheet spriteSheet2 = new SpriteSheet("sprites/arrowup.png", 64, 64);
			animations2[0] = loadAnimation(spriteSheet2, 0, 1, 0);
			animations2[1] = loadAnimation(spriteSheet2, 1, 2, 0);
			animations2[2] = loadAnimation(spriteSheet2, 2, 3, 0);
			animations2[3] = loadAnimation(spriteSheet2, 3, 4, 0);
			animations2[4] = loadAnimation(spriteSheet2, 0, 1, 1);
			animations2[5] = loadAnimation(spriteSheet2, 1, 2, 1);
			animations2[6] = loadAnimation(spriteSheet2, 2, 3, 1);
			animations2[7] = loadAnimation(spriteSheet2, 3, 4, 1);
			animations2[8] = loadAnimationLoop(spriteSheet2, 2, -1, 0, 400);
			animations2[9] = loadAnimationLoop(spriteSheet2, 2, -1, 1, 400);
			listanim.add(animations2);
			Animation animations3[] = new Animation[10];
			SpriteSheet spriteSheet3 = new SpriteSheet("sprites/arrowdown.png", 64, 64);
			animations3[0] = loadAnimation(spriteSheet3, 3, 4, 3);
			animations3[1] = loadAnimation(spriteSheet3, 2, 3, 3);
			animations3[2] = loadAnimation(spriteSheet3, 1, 2, 3);
			animations3[3] = loadAnimation(spriteSheet3, 0, 1, 3);
			animations3[4] = loadAnimation(spriteSheet3, 3, 4, 2);
			animations3[5] = loadAnimation(spriteSheet3, 2, 3, 2);
			animations3[6] = loadAnimation(spriteSheet3, 1, 2, 2);
			animations3[7] = loadAnimation(spriteSheet3, 0, 1, 2);
			animations3[8] = loadAnimationLoop(spriteSheet3, 1, 4, 3, 400);
			animations3[9] = loadAnimationLoop(spriteSheet3, 1, 4, 2, 400);
			listanim.add(animations3);
			Animation animations4[] = new Animation[10];
			SpriteSheet spriteSheet4 = new SpriteSheet("sprites/arrowright.png", 64, 64);
			animations4[0] = loadAnimation(spriteSheet4, 3, 4, 0);
			animations4[1] = loadAnimation(spriteSheet4, 3, 4, 1);
			animations4[2] = loadAnimation(spriteSheet4, 3, 4, 2);
			animations4[3] = loadAnimation(spriteSheet4, 3, 4, 3);
			animations4[4] = loadAnimation(spriteSheet4, 2, 3, 0);
			animations4[5] = loadAnimation(spriteSheet4, 2, 3, 1);
			animations4[6] = loadAnimation(spriteSheet4, 2, 3, 2);
			animations4[7] = loadAnimation(spriteSheet4, 2, 3, 3);
			animations4[8] = loadAnimationYLoop(spriteSheet4, 3, 0, 3, 400);
			animations4[9] = loadAnimationYLoop(spriteSheet4, 2, 0, 3,  400);
			listanim.add(animations4);
			ARROW_ANIMATIONS_LIST = listanim;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listanim;
	}

	public static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		if(startX<endX)
			for (int x = startX; x < endX; x++) {
				animation.addFrame(spriteSheet.getSprite(x, y), 100);
			}
		else
			for (int x = startX; x > endX; x--) {
				animation.addFrame(spriteSheet.getSprite(x, y), 100);
			}
		return animation;
	}
	public static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y, int duration) {
		Animation animation = new Animation();
		if(startX<endX)
			for (int x = startX; x < endX; x++) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		else
			for (int x = startX; x > endX; x--) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		return animation;
	}
	public static Animation loadAnimationLoop(SpriteSheet spriteSheet, int startX, int endX, int y, int duration) {
		Animation animation = new Animation();
		if(startX<endX){
			for (int x = startX; x < endX; x++) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
			for (int x = endX-1; x > startX; x--) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		}else{
			for (int x = startX; x > endX; x--) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
			for (int x = endX+1; x < startX; x++) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		}
		return animation;
	}
	public static Animation loadAnimationY(SpriteSheet spriteSheet, int x, int startY, int endY, int duration) {
		Animation animation = new Animation();
		if(startY<endY)
			for (int y = startY; y < endY; y++) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		else
			for (int y = startY; y > endY; y--) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		return animation;
	}
	public static Animation loadAnimationYLoop(SpriteSheet spriteSheet, int x, int startY, int endY, int duration) {
		Animation animation = new Animation();
		if(startY<endY){
			for (int y = startY; y < endY; y++) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
			for (int y = endY-1; y > startY; y--) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		}else{
			for (int y = startY; y > endY; y--) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
			for (int y = endY+1; y < startY; y++) {
				animation.addFrame(spriteSheet.getSprite(x, y), duration);
			}
		}
		return animation;
	}
	private static Animation loadExplosionAnim(String string, int i, int j) {
		SpriteSheet spriteSheet;
		try {
			spriteSheet = new SpriteSheet(string, i, j);

			Animation animation = new Animation();
			for (int y = 0; y < 4; y++) {
				for(int x = 0; x < 3; x++)
				animation.addFrame(spriteSheet.getSprite(x, y), 100);
			}
			return animation;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
