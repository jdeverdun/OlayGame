package lesson14;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Run1.Run1;
import Run2.Run2;

public class Engine extends StateBasedGame{

	public Engine()  {
		super("ASY");
	}

	 public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Engine(), 1248, 864, false).start();
    }
	 
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
		//Music background = new Music("sound/love.ogg");
        //background.loop();
		this.addState(new Menu());
		this.addState(new Run1(3));
		this.addState(new Run2(3));
	}

}
