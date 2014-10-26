package lesson14;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import Dance1.Dance1;
import Dance2.Dance2;
import Dance3.Dance3;
import FogWar1.FogWar1;
import Run1.Run1;
import Run2.Run2;
import Run3.Run3;

public class Engine extends StateBasedGame{

	public static final Dimension WINDOW_SIZE = new Dimension(1248,864);
	public static String INSTALL_FOLDER;
	public static Audio SHOT_SOUND;
	public static Audio EXPLOSION_SOUND;
	public Engine()  {
		super("ASY");
	}

	 public static void main(String[] args) throws SlickException {
		 try {
			INSTALL_FOLDER = new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new AppGameContainer(new Engine(), WINDOW_SIZE.width, WINDOW_SIZE.height, false).start();
    }
	 
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
		initSounds();
		/*Music background = new Music("sound/love.ogg");
        background.loop();*/
		this.addState(new Menu());
		this.addState(new Run1(4));
		this.addState(new Run2(4));
		this.addState(new Run3(4));
		this.addState(new FogWar1(4));
		this.addState(new Dance1(5));
		this.addState(new Dance2(2));
		this.addState(new Dance3(2));
	}

	private void initSounds() {
		if(SHOT_SOUND==null)
			try {
				SHOT_SOUND = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sound/tir.ogg"));
				//m = new Music("sound/tir.ogg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(EXPLOSION_SOUND==null)
			try {
				EXPLOSION_SOUND = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sound/explosion-sourde.ogg"));
				//m = new Music("sound/tir.ogg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static float GetMusicDurationMsecs(Music zik)
    {
        // Get Music's (private) Audio object reference:
        Audio audio = null;
        Field fields_music[] = zik.getClass().getDeclaredFields();

        for(int i = 0; i < fields_music.length; i++)
        {
            if(fields_music[i].getName().compareTo("sound") == 0)
            {
                try
                {
                    fields_music[i].setAccessible(true);
                    audio = (Audio) (fields_music[i].get(zik));
                }
                catch(Exception e){e.toString();}
            }
        }

        // Access Audio object's 'length' field (private):
        float duration_secs = 0.0f;
        Field fields_audio[] = audio.getClass().getDeclaredFields();

        for(int i = 0; i < fields_audio.length; i++)
        {
            if(fields_audio[i].getName().compareTo("length") == 0)
            {
                try
                {
                    fields_audio[i].setAccessible(true);
                    duration_secs = (Float) (fields_audio[i].get(audio));
                    //_Utils.Log("\nRetrieved Music duration = " + duration_secs + "\n");
                }
                catch(Exception e){e.toString();}
            }
        }

        return duration_secs * 1000.0f;
    }

}
