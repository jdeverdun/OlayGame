/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson14;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class Olay extends BasicGame {

	private int numberOfChar = 30;
    private GameContainer container;
    private Map map = new Map();
    private ArrayList<Player> characters;
    private TriggerController triggers;
    private Camera camera;
    private PlayerController controller;
    private Hud hud = new Hud();
    private GameMode currentGameMode = GameMode.Run1;
    enum GameMode{Run1};

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Olay(), 1248, 864, false).start();
    }

    public Olay() {
        super("Olay");
        characters = new ArrayList<Player>(numberOfChar);
		for(int i = 0; i<numberOfChar; i++){
			characters.add(new Player(map));
		}
		controller = new PlayerController(characters.get(0),characters);
		camera = new Camera(characters.get(0));
		triggers = new TriggerController(map, characters.get(0));
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
    	container.setMouseCursor("sprites/cursor/crosshair.png", 0, 0);
        Music background = new Music("sound/nyan-cat.ogg");
        background.loop();
        this.map.init();
        CharactersTools.init(characters, currentGameMode);
        for(Player player:characters)
	        player.init();
        //this.hud.init();
        this.controller.setInput(container.getInput());
        container.getInput().addKeyListener(controller);
        container.getInput().addMouseListener(controller);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        //this.camera.place(container, g);
        this.map.renderBackground();
        for(Player player:characters)
        	player.render(g);
        this.map.renderForeground();
        //this.hud.render(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.controller.update();
        this.triggers.update();
        int count = 0;
        for(Player player:characters){
        	if(count>0 && player.isAwaitingOrder()){
        		player.randomOrder(currentGameMode);
        	}
        	player.update(delta);
        	count++;
        }
       //this.camera.update(container);
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
    }
}
