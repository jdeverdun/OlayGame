package lesson14;

import java.awt.event.MouseEvent;
import org.newdawn.slick.MouseListener;
import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class PlayerController implements KeyListener,MouseListener {

    private Player player;
    private ArrayList<Player> characters;
    private Input input;

    public PlayerController(Player player, ArrayList<Player> chars) {
        this.player = player;
        this.characters = chars;
    }

    public void update() {
        if (input.getControllerCount() > 0) {
            player.setDx(input.getAxisValue(0, 1));
            player.setDy(input.getAxisValue(0, 2));
        }
    }

    @Override
    public void setInput(Input input) {
        this.input = input;
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
        case Input.KEY_UP:break;
        case Input.KEY_LEFT:break;
        case Input.KEY_DOWN:
            this.player.setDx(0.3f);
            break;
        case Input.KEY_RIGHT:
            this.player.setDx(1);
            break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
        case Input.KEY_UP:break;
        case Input.KEY_DOWN:
            this.player.setDx(0);
            break;
        case Input.KEY_LEFT:break;
        case Input.KEY_RIGHT:
            this.player.setDx(0);
            break;
        }
    }


	@Override
	public void mouseClicked(int button, int x, int y, int clickcount) {
		for(Player p:characters){
			float dist = (float) Math.sqrt((p.getX()-x-32)*(p.getX()-x-32) + (p.getY()-10-y-32)*(p.getY()-10-y-32));
			if(dist<30){
				System.out.println("GG");
				p.kill();
				break;
			}
		}
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}


}
