/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package Run1;

import java.awt.Menu;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import lesson14.Engine;
import lesson14.Hud;
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

import Run1.Player;
import Run1.Player.BotStatus;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class Run1 extends BasicGameState {

	private int numberOfChar = 30;
    private GameContainer container;
    private Map map = new Map();
    private ArrayList<Player> characters;
    private ArrayList<TriggerController> triggers;
    private Camera camera;
    private ArrayList<PlayerController> controller;
    private Hud hud = new Hud();
    private int startTimer = 180;
    private int stopTimer = -1;
    private GameMode currentGameMode = GameMode.Run1;
    private int numPlayers;
    public enum GameMode{Run1};

   

    public Run1(int numPlayers) {
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
		// players
		triggers = new ArrayList<TriggerController>();
		controller = new ArrayList<PlayerController>();
		for(int i = 0; i<n; i++){
			characters.get(i).setPlayer(true);
			characters.get(i).setNumPlayer(i+1);
			controller.add(new PlayerController(characters.get(i),characters));
			triggers.add(new TriggerController(map, characters.get(i)));
		}
		camera = new Camera(characters.get(0));
		
		startTimer = 180;
		stopTimer = -1;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame s) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame s, Graphics g) throws SlickException {
        //this.camera.place(container, g);
        this.map.renderBackground();
        if(checkForEquality()){
        	g.drawString("No WINNER snif :'(",600,400);
        	if(stopTimer==-1)
        		stopTimer = 180;
        }else{
	        for(Player player:characters){
	        	player.render(g);
	        	if(player.isWinner()){
	            	g.drawString("Player "+player.getNumPlayer()+" WIN",600,400);
	            	if(stopTimer==-1)
	            		stopTimer = 180;
	        	}
	        }
        }
        this.map.renderForeground();
        for(Player player:characters){
			player.render(g);
			if(player.getStatus() != BotStatus.Dead){
				g.setColor(Color.green);
				g.drawString("Player "+player.getNumPlayer(),Engine.WINDOW_SIZE.width-(100*player.getNumPlayer()),10);
			}

		}
        //this.hud.render(g);
        // cursor
        for(Player player:characters){
        	if(player.getCursor() != null)
        		player.getCursor().render(g);
        }
        if(startTimer > 120)
        	g.drawString("3", 500, 500);
        else
        	if(startTimer > 60)
            	g.drawString("2", 500, 500);
        	else
        		if(startTimer > 0)
                	g.drawString("1", 500, 500);
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
    	resetAll(numPlayers);
        this.container = container;
        
        this.map.init();
        CharactersTools.init(characters);
        for(Player player:characters)
	        player.init();
        //this.hud.init();
        for(PlayerController contr:controller){
        	contr.setInput(container.getInput());
        	container.getInput().addKeyListener(contr);
        	container.getInput().addMouseListener(contr);
        }
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame s, int delta) throws SlickException {
    	if(container.getInput().isKeyPressed(Input.KEY_ESCAPE))
        	s.enterState(States.MENU);
    	for(PlayerController contr:controller)
    		contr.update();
    	for(TriggerController tri:triggers)
    		tri.update();
    	if(stopTimer == 0)
    		s.enterState(States.GAME_RUN1);
        if(startTimer == 0 && stopTimer == -1){
        	int count = 0;
	        for(Player player:characters){
	        	if(count>0 && player.isAwaitingOrder()){
	        		player.randomOrder(currentGameMode);
	        	}
	        	player.update(delta);
	        	count++;
	        }
	        // cursor
	        for(Player player:characters){
	        	if(player.getCursor() != null)
	        		player.getCursor().update(delta);
	        }
        }else{
        	if(startTimer != 0)
        		startTimer--;
        	if(stopTimer > 0)
        		stopTimer--;
        }
    	
       //this.camera.update(container);
    }

    
    @Override
    public int getID(){
    	return States.GAME_RUN1;
    }
}
