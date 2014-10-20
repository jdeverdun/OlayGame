/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package Dance1;

import java.awt.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


import lesson14.Engine;
import lesson14.States;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Dance1.Arrow.Direction;
import Dance1.Camera;
import Dance1.CharactersTools;
import Dance1.Player.BotStatus;


/** DANCE
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class Dance1 extends BasicGameState {

	private int numberOfChar = 30;
	private int numberOfClouds = 6;
	private GameContainer container;
	private Map map = new Map();
	private ArrayList<Player> characters;
	private ArrayList<Cloud> clouds;
	private ArrayList<Arrow> staticArrows;
	private ArrayList<Arrow> movingArrows;
	private int maxMovingArrows = 20;
	private ArrayList<TriggerController> triggers;
	private Camera camera;
	private ArrayList<PlayerController> controller;
	private Hud hud = new Hud();
	private int startTimer = 180;
	private int stopTimer = -1;
	private GameMode currentGameMode = GameMode.Dance1;
	private int numPlayers;
	public enum GameMode{Run1,Run2, Dance1};
	public static Music background;


	// dance
	private boolean dance = false;
	private int danceIntTime = 0;
	private boolean gauche = true;
	private int timerToDance = 0;
	private final float baseDanceDuration = 540.0f;
	private int danceDuration = (int) baseDanceDuration;
	private int tic = 0;
	private LinkedList<Float[]> steps;
	private float offset = 8.539f;
	private Player shooter;
	public Dance1(int numPlayers) {
		this.numPlayers = numPlayers;

		resetAll(numPlayers);
	}

	public void resetAll(int n){
		if(characters!=null){
			for(Player p : characters){
				p.reset();
			}
		}
		characters = new ArrayList<Player>(numberOfChar);
		for(int i = 0; i<numberOfChar; i++){
			characters.add(new Player(map));
		}
		//clouds
		clouds = new ArrayList<Cloud>(numberOfClouds);
		for(int i = 0; i<numberOfClouds; i++){
			clouds.add(new Cloud(map));
		}
		//arrow

		staticArrows = new ArrayList<Arrow>(4);
		staticArrows.add(new Arrow(map,true,Direction.left));
		staticArrows.add(new Arrow(map,true,Direction.right));
		staticArrows.add(new Arrow(map,true,Direction.up));
		staticArrows.add(new Arrow(map,true,Direction.down));

		// moving arrow
		movingArrows = new ArrayList<Arrow>(maxMovingArrows);
		for(int i = 0;i<maxMovingArrows;i++){
			movingArrows.add(new Arrow(map,false,Direction.left));
			movingArrows.get(i).setDisable(true);
		}
		// players
		triggers = new ArrayList<TriggerController>();
		controller = new ArrayList<PlayerController>();
		for(int i = 0; i<n-1; i++){
			characters.get(i).setPlayer(true);
			characters.get(i).setNumPlayer(i+1);
			controller.add(new PlayerController(characters.get(i),characters));
			triggers.add(new TriggerController(map, characters.get(i)));
		}
		// last player
		shooter = new Player(map,true);
		controller.add(new PlayerController(shooter,characters));
		camera = new Camera(characters.get(0));
		try {
			steps = CharactersTools.parseDanceFileSoundRef("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"Day-dream-off.txt");
			//steps = CharactersTools.parseDanceFile("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"Day-dream.txt");
			//steps = CharactersTools.parseDanceFile("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"Hardcore-Overdoze.txt");//Day-dream.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startTimer = 180;
		stopTimer = -1;
		danceDuration = (int) baseDanceDuration;
		dance = false;
		danceIntTime = 0;
		gauche = true;
		timerToDance = 0;
		tic = 0;
		if(dance){
			Hud.XP_WIDTH_PERC = (baseDanceDuration-(float)danceDuration)/baseDanceDuration;
		}else{
			Hud.XP_WIDTH_PERC = (baseDanceDuration-(float)timerToDance)/baseDanceDuration;
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame s) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame s, Graphics g) throws SlickException {
		//this.camera.place(container, g);
		this.map.renderBackground();
		boolean hasWinner = false;
		String winnerWord = "Winner : ";
		for(Player player:characters){
			player.render(g);
			if(player.isWinner()){
				winnerWord += "Player "+player.getNumPlayer() +" ";
				hasWinner = true;
				if(stopTimer==-1)
					stopTimer = 360;
			}

		}
		if(shooter.isWinner()){
			hasWinner = true;
			winnerWord += "Shooter winner ! ";
			if(stopTimer==-1)
				stopTimer = 360;
		}
		if(hasWinner)
			g.drawString(winnerWord,600,400);


		for(Player player:characters){
			player.render(g);
			if(player.getStatus() != BotStatus.Dead){
				g.setColor(Color.green);
				g.drawString("Player "+player.getNumPlayer(),Engine.WINDOW_SIZE.width-(100*player.getNumPlayer()),10);
			}

		}
		this.map.renderForeground();
		for(Cloud cloud:clouds){
			cloud.render(g);
		}
		for(Arrow arrow:staticArrows){
			arrow.render(g);
		}
		for(Arrow arrow:movingArrows){
			arrow.render(g);
		}
		this.hud.render(g);

		if(startTimer > 120)
			g.drawString("3", 500, 500);
		else
			if(startTimer > 60)
				g.drawString("2", 500, 500);
			else
				if(startTimer > 0)
					g.drawString("1", 500, 500);
				else
					if(dance){
						//g.drawString("Dance duration : "+danceDuration, 600, 400);
						Hud.XP_WIDTH_PERC = (baseDanceDuration-(float)danceDuration)/baseDanceDuration;
					}else{
						//g.drawString("Next dance : "+timerToDance, 600, 400);
						Hud.XP_WIDTH_PERC = (baseDanceDuration-(float)timerToDance)/baseDanceDuration;
					}

	}

	/**
	 * test si il reste des joueurs vivant ou sinon si il reste des balles
	 * @param player
	 * @return
	 */
	private boolean checkForEquality() {
		for(Player p:characters){
			if(p.isPlayer() && p.getStatus() != BotStatus.Dead){
				return false;
			}
		}
		return true;
	}

	@Override
	public void enter(GameContainer container,StateBasedGame s) throws SlickException{
		container.setMouseCursor("sprites/cursor/crosshair.png", 32,32);
		resetAll(numPlayers);
		this.container = container;

		this.map.init();
		CharactersTools.init(characters);
		for(Player player:characters)
			player.init();
		this.hud.init();
		for(PlayerController contr:controller){
			contr.setInput(container.getInput());
			if(!contr.getPlayer().isShooter())
				container.getInput().addKeyListener(contr);
			else
				container.getInput().addMouseListener(contr);
		}
		if(background==null)
			try {
				background = new Music("sound/Day-Dream.ogg");
				//background = new Music("sound/Hardcore-Overdoze.ogg");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		background.stop();
		background.play();
	}

	@Override
	public void update(GameContainer container, StateBasedGame s, int delta) throws SlickException {
		if(shooter.getStatus() == BotStatus.Dead)
			container.setDefaultMouseCursor();
		/*for(int i = 0 ; i < numPlayers-1;i++){
    		if(characters.get(i).getStatus() != BotStatus.Dead && characters.get(numPlayers-1).getAvailableShot()<=0){
    			characters.get(i).setIsWinner(true);
    		}
    	}
    	if(!background.playing()){*/
		boolean hasWinner = false;
		for(int i = 0 ; i < numPlayers-1;i++){
			if(characters.get(i).getStatus() != BotStatus.Dead && shooter.getAvailableShot()<=0){
				characters.get(i).setIsWinner(true);
				hasWinner = true;
			}
		}
		boolean alldead = true;
		for(int i = 0 ; i < numPlayers-1;i++){
			if(characters.get(i).getStatus() != BotStatus.Dead)
				alldead = false;
		}
		if(!hasWinner && alldead)
			shooter.setIsWinner(true);
		//}
		if(!background.playing()){
			hasWinner = false;
			for(int i = 0 ; i < numPlayers-1;i++){
				if(characters.get(i).getStatus() != BotStatus.Dead && shooter.getAvailableShot()<=0){
					characters.get(i).setIsWinner(true); 
					hasWinner = true;
				}
			}
			if(!hasWinner)
				shooter.setIsWinner(true);
		}
		if(timerToDance == 0 || (danceDuration > 0 && danceDuration<baseDanceDuration)){
			dance = true;
			danceDuration--;
			timerToDance = (int) baseDanceDuration;

		}else{
			if(danceDuration == 0){
				timerToDance--;
				dance = false;
			}
			if(timerToDance == 0)
				danceDuration = (int) baseDanceDuration;
		}

		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			container.setDefaultMouseCursor();
			background.stop();
			s.enterState(States.MENU);

		}
		for(PlayerController contr:controller)
			contr.update();
		for(Cloud cloud:clouds){
			cloud.update(delta);
		}
		for(Arrow arrow:staticArrows){
			arrow.update(delta);
		}
		for(Arrow arrow:movingArrows){
			arrow.update(delta);
		}
		for(TriggerController tri:triggers)
			tri.update();
		if(stopTimer == 0)
			s.enterState(States.GAME_DANCE1);
		if(startTimer == 0 && stopTimer == -1){
			// HUD
			Hud.MANA_WIDTH_PERC = ((float)danceIntTime/60.0f);
			if(gauche)
				Hud.MANA_WIDTH_PERC = 1-Hud.MANA_WIDTH_PERC;

			danceIntTime++;
			if(danceIntTime>60){
				gauche = !gauche;
				danceIntTime = 0;
			}
			for(Player player:characters){
				player.update(delta);
			}

		}else{
			if(startTimer != 0)
				startTimer--;
			if(stopTimer > 0)
				stopTimer--;
		}
		tic++;
		// check for next steps

		int ticFromBottomToUp = 515;//Math.max(offset, (int)((Engine.WINDOW_SIZE.height-Arrow.default_arrivalY)/(.15f * delta * Arrow.arrowSpeed )));
		//System.out.println((int)((Engine.WINDOW_SIZE.height-Arrow.default_arrivalY)/(.15f * delta * Arrow.arrowSpeed )));
		
		/*
		if(!steps.isEmpty() && (steps.getFirst()[0]-ticFromBottomToUp) < (tic))
			steps.pop();
		if(!steps.isEmpty() && (steps.getFirst()[0]-ticFromBottomToUp) == (tic)){
			addArrow(steps.pop());

		}
		*/
		float position = background.getPosition();
		if(!steps.isEmpty() && (steps.getFirst()[0]-offset) < (position))
			steps.pop();
		if(!steps.isEmpty() && ((steps.getFirst()[0]-offset) - (position))<0.01f){
			addArrow(steps.pop());

		}

		for(Arrow arr : movingArrows){
			if(arr.isDisable())
				continue;
			if( (arr.getMusicTicTime()-(position))<0.03f){// && (arr.getMusicTicTime()-(position))>0){
				for(Player player:characters){
					if(!player.isPlayer())
						player.move(arr.getOrientation());
				}
			}else{
				if((arr.getMusicTicTime() - (position)) < 0.15f && (arr.getMusicTicTime() - (position)) > 0){
					for(Player player:characters){
						if(!player.isPlayer() && Math.random()<(0.03*(arr.getMusicTicTime() - (position))/0.15f))
							player.move(arr.getOrientation());
					}
				}
			}
		}

		//this.camera.update(container);
	}


	private void addArrow(Float[] pop) {
		Direction dir = Direction.up;
		switch(Math.round(pop[1])){
		case Input.KEY_UP:
			dir = Direction.up;break;
		case Input.KEY_DOWN:
			dir = Direction.down;break;
		case Input.KEY_LEFT:
			dir = Direction.left;break;
		case Input.KEY_RIGHT:
			dir = Direction.right;break;
		}
		for(Arrow arr : movingArrows){
			if(arr.isDisable()){
				arr.setOrientation(dir);
				arr.reset();
				arr.setMusicTicTime(pop[0]);
				return;
			}
		}
	}


	@Override
	public int getID(){
		return States.GAME_DANCE1;
	}
}
