/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package Dance3;

import java.awt.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

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

import Dance3.Arrow.Direction;
import Dance3.Camera;
import Dance3.CharactersTools;
import Dance3.Player.BotStatus;


/** DANCE
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class Dance3 extends BasicGameState {

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
	private GameMode currentGameMode = GameMode.Dance3;
	public static int numPlayers;
	public enum GameMode{Run1,Run2, Dance3};
	public static Music background;
	public static File currentMusic;

	// dance
	private boolean dance = false;
	private int danceIntTime = 0;
	private boolean gauche = true;
	private int timerToDance = 0;
	private final float baseDanceDuration = 540.0f;
	private int danceDuration = (int) baseDanceDuration;
	private int tic = 0;
	private LinkedList<Float[]> steps;
	private LinkedList<Float[]> originalSteps;
	private float offset = 8.539f;
	private float musicDuration;
	public Dance3(int numPlayers) {
		this.numPlayers = numPlayers;
		numberOfChar = numPlayers;
		
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
		for(int i = 0; i<n; i++){
			characters.get(i).setPlayer(true);
			characters.get(i).setNumPlayer(i+1);
			characters.get(i).setBaseMode(this);
			controller.add(new PlayerController(characters.get(i),characters));
			triggers.add(new TriggerController(map, characters.get(i)));
		}
		// last player
		camera = new Camera(characters.get(0));
		try {
			steps = CharactersTools.parseDanceFileSoundRef(Engine.INSTALL_FOLDER + "/src/danceTrack"+File.separator+currentMusic.getName().substring(0,currentMusic.getName().length()-4)+".txt");
			originalSteps = (LinkedList<Float[]>) steps.clone();
			//steps = CharactersTools.parseDanceFileSoundRef("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"butterfly.txt");
			//steps = CharactersTools.parseDanceFileSoundRef("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"Day-dream-off.txt");
			//steps = CharactersTools.parseDanceFile("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"Day-dream.txt");
			//steps = CharactersTools.parseDanceFile("C:/Users/Analyse/git/OlayGame/src/danceTrack"+File.separator+"Hardcore-Overdoze.txt");//Day-dream.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.setMapStep(new ArrayList<Integer>(4));
		
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
		if(hasWinner)
			g.drawString(winnerWord,600,400);



		this.map.renderForeground();
		for(Player player:characters){
			player.render(g);
			if(player.getStatus() != BotStatus.Dead){
				g.setColor(Color.green);
				g.drawString("Player "+player.getNumPlayer(),Engine.WINDOW_SIZE.width-(100*player.getNumPlayer()),10);
			}

		}
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
		//mode highlight
		if(stopTimer==360){
			for(Player player:characters){
				if(player.getStatus() != BotStatus.Dead && player.isPlayer() && player.isWinner()){
					player.setHighlight(true);
				}

			}
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
		File[] listm = new File(Engine.INSTALL_FOLDER + File.separator + "src" + File.separator + "sound" + File.separator + "dance").listFiles();
		Random r = new Random();
		currentMusic = listm[r.nextInt(listm.length)];
		resetAll(numPlayers);
		this.container = container;

		this.map.init();
		CharactersTools.init(characters);
		for(Player player:characters)
			player.init();
		this.hud.init();
		for(PlayerController contr:controller){
			contr.setInput(container.getInput());
			container.getInput().addKeyListener(contr);
			container.getInput().addMouseListener(contr);
		}
	//	if(background==null)
			
			try {
				background = new Music(currentMusic.getAbsolutePath());
				//background = new Music("sound/loituma.ogg");
				//background = new Music("sound/butterfly.ogg");
				//background = new Music("sound/Day-Dream.ogg");
				//background = new Music("sound/Hardcore-Overdoze.ogg");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		background.stop();
		background.play();
		musicDuration = Engine.GetMusicDurationMsecs(background);
	}

	@Override
	public void update(GameContainer container, StateBasedGame s, int delta) throws SlickException {
		/*for(int i = 0 ; i < numPlayers-1;i++){
    		if(characters.get(i).getStatus() != BotStatus.Dead && characters.get(numPlayers-1).getAvailableShot()<=0){
    			characters.get(i).setIsWinner(true);
    		}
    	}
    	if(!background.playing()){*/
		boolean hasWinner = false;
		/*for(int i = 0 ; i < numPlayers-1;i++){
			if(characters.get(i).getStatus() != BotStatus.Dead){
				characters.get(i).setIsWinner(true);
				hasWinner = true;
			}
		}*/
		boolean alldead = true;
		for(int i = 0 ; i < numPlayers;i++){
			if(characters.get(i).getStatus() != BotStatus.Dead)
				alldead = false;
		}

		if(!background.playing()){
			hasWinner = false;
			for(int i = 0 ; i < numPlayers;i++){
				if(characters.get(i).getStatus() != BotStatus.Dead){
					characters.get(i).setIsWinner(true); 
					hasWinner = true;
				}
			}
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
		boolean nextStep = true;
		for(int i = 0 ; i < numPlayers;i++){
			if(!characters.get(i).isReadyForNextStep()){
				nextStep = false;
				break;
			}
		}
		if(nextStep){
			//map.setMapStep(map.getMapStep()+1);
			for(int i = 0 ; i < numPlayers;i++){
				characters.get(i).setReadyForNextStep(false);
				characters.get(i).setDancing(false);
				characters.get(i).getLastPress().clear();
			}
		}else{
			for(int i = 0 ; i < numPlayers;i++){
				characters.get(i).setReadyForNextStep(false);
				//characters.get(i).getLastPress().clear();
			}
		}
		
		if(stopTimer == 0)
			s.enterState(States.GAME_DANCE3);
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

		int ticFromBottomToUp = 515;
		float position = background.getPosition();
		if(!steps.isEmpty() && (steps.getFirst()[0]-offset) < (position))
			steps.pop();
		if(!steps.isEmpty() && ((steps.getFirst()[0]-offset) - (position))<0.03f){// passage de 0.01 à 0.03 selon machine
			addArrow(steps.pop());

		}

		hud.XP_WIDTH_PERC = 1-(background.getPosition()/(musicDuration/1000.0f));
		for(Arrow arr : movingArrows){
			if(arr.isDisable())
				continue;
			if( (arr.getMusicTicTime()-0.03f-(position))<0.03f){// && (arr.getMusicTicTime()-(position))>0){
				for(Player player:characters){
					if(!player.isPlayer())
						player.move(arr.getOrientation());
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
		return States.GAME_DANCE3;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public LinkedList<Float[]> getOriginalSteps() {
		return originalSteps;
	}

	public void clearAllPress() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < numPlayers;i++){
			characters.get(i).clearLastPress();
		}
	}

	public ArrayList<Player> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Player> characters) {
		this.characters = characters;
	}

}
