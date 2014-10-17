package lesson14;

import java.awt.Dimension;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Dance1.Dance1;
import FogWar1.FogWar1;
import Run1.Run1;
import Run2.Run2;
import Run3.Run3;

public class Engine extends StateBasedGame{

	public static final Dimension WINDOW_SIZE = new Dimension(1248,864);
	
	public Engine()  {
		super("ASY");
	}

	 public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Engine(), WINDOW_SIZE.width, WINDOW_SIZE.height, false).start();
    }
	 
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
		/*Music background = new Music("sound/love.ogg");
        background.loop();*/
		this.addState(new Menu());
		this.addState(new Run1(3));
		this.addState(new Run2(3));
		this.addState(new Run3(3));
		this.addState(new FogWar1(3));
		this.addState(new Dance1(4));
	}

}
