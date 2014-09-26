package Run2;

import java.awt.event.MouseEvent;
import org.newdawn.slick.MouseListener;
import java.util.ArrayList;

import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class PlayerController implements KeyListener,MouseListener {

	// commandes par defaut
	public static final int UP_PLAYER1 = Input.KEY_UP;
	public static final int DOWN_PLAYER1 = Input.KEY_DOWN;
	public static final int LEFT_PLAYER1 = Input.KEY_LEFT;
	public static final int RIGHT_PLAYER1 = Input.KEY_RIGHT;
	public static final int CURSOR_UP_PLAYER1 = Input.KEY_NUMPAD5;
	public static final int CURSOR_DOWN_PLAYER1 = Input.KEY_NUMPAD2;
	public static final int CURSOR_LEFT_PLAYER1 = Input.KEY_NUMPAD1;
	public static final int CURSOR_RIGHT_PLAYER1 = Input.KEY_NUMPAD3;
	public static final int SHOOT_PLAYER1 = Input.KEY_NUMPAD0;
	
	public static final int UP_PLAYER2 = Input.KEY_Z;
	public static final int DOWN_PLAYER2 = Input.KEY_S;
	public static final int LEFT_PLAYER2 = Input.KEY_Q;
	public static final int RIGHT_PLAYER2 = Input.KEY_D;
	public static final int CURSOR_UP_PLAYER2 = Input.KEY_T;
	public static final int CURSOR_DOWN_PLAYER2 = Input.KEY_G;
	public static final int CURSOR_LEFT_PLAYER2 = Input.KEY_F;
	public static final int CURSOR_RIGHT_PLAYER2 = Input.KEY_H;
	public static final int SHOOT_PLAYER2 = Input.KEY_R;
	
	public static final int UP_PLAYER3 = Input.KEY_I;
	public static final int DOWN_PLAYER3 = Input.KEY_K;
	public static final int LEFT_PLAYER3 = Input.KEY_J;
	public static final int RIGHT_PLAYER3 = Input.KEY_L;
	public static final int CURSOR_UP_PLAYER3 = 27;
	public static final int CURSOR_DOWN_PLAYER3 = 41;
	public static final int CURSOR_LEFT_PLAYER3 = Input.KEY_M;
	public static final int CURSOR_RIGHT_PLAYER3 = 43;
	public static final int SHOOT_PLAYER3 = Input.KEY_P;
	
	
	// local 
	public int up_local;
	public int down_local;
	public int left_local;
	public int right_local;
	public int cursor_up_local;
	public int cursor_down_local;
	public int cursor_left_local;
	public int cursor_right_local;
	public int shoot_local;
	
    private Player player;
    private ArrayList<Player> characters;
    private Input input;

    

    public PlayerController(Player player, ArrayList<Player> chars) {
        this.player = player;
        this.characters = chars;
        setCommands();
    }
    public void setCommands(){
    	switch(player.getNumPlayer()){
        case 1:
        	
        	up_local = UP_PLAYER1;
        	left_local = LEFT_PLAYER1;
        	down_local = DOWN_PLAYER1;
        	right_local = RIGHT_PLAYER1;
        	cursor_up_local = CURSOR_UP_PLAYER1;
        	cursor_down_local = CURSOR_DOWN_PLAYER1;
        	cursor_right_local = CURSOR_RIGHT_PLAYER1;
        	cursor_left_local = CURSOR_LEFT_PLAYER1;
        	shoot_local = SHOOT_PLAYER1;
        	break;
        case 2:
        	up_local = UP_PLAYER2;
        	left_local = LEFT_PLAYER2;
        	down_local = DOWN_PLAYER2;
        	right_local = RIGHT_PLAYER2;
        	cursor_up_local = CURSOR_UP_PLAYER2;
        	cursor_down_local = CURSOR_DOWN_PLAYER2;
        	cursor_right_local = CURSOR_RIGHT_PLAYER2;
        	cursor_left_local = CURSOR_LEFT_PLAYER2;
        	shoot_local = SHOOT_PLAYER2;
        	break;
        case 3:
        	up_local = UP_PLAYER3;
        	left_local = LEFT_PLAYER3;
        	down_local = DOWN_PLAYER3;
        	right_local = RIGHT_PLAYER3;
        	cursor_up_local = CURSOR_UP_PLAYER3;
        	cursor_down_local = CURSOR_DOWN_PLAYER3;
        	cursor_right_local = CURSOR_RIGHT_PLAYER3;
        	cursor_left_local = CURSOR_LEFT_PLAYER3;
        	shoot_local = SHOOT_PLAYER3;
        	break;
        }
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
    	if(key == up_local)
    		return;
    	else if(key == down_local)
    		this.player.setDx(0.3f);
    	else if(key == right_local)
    		this.player.setDx(0.0001f);
    	else if(key == left_local)
    		this.player.setDx(-0.0001f);//return;
    	else if(key == cursor_down_local)
    		this.player.getCursor().setDy(2.5f);
    	else if(key == cursor_up_local)
    		this.player.getCursor().setDy(-2.5f);
    	else if(key == cursor_right_local)
    		this.player.getCursor().setDx(2.5f);
    	else if(key == cursor_left_local)
    		this.player.getCursor().setDx(-2.5f);
    }


	@Override
    public void keyReleased(int key, char c) {
    	if(key == up_local)
    		return;
    	else if(key == down_local)
    		this.player.setDx(0);
    	else if(key == right_local)
    		return;
    	else if(key == left_local)
    		return;
    	else if(key == cursor_down_local)
    		this.player.getCursor().setDy(0);
    	else if(key == cursor_up_local)
    		this.player.getCursor().setDy(0);
    	else if(key == cursor_right_local)
    		this.player.getCursor().setDx(0);
    	else if(key == cursor_left_local)
    		this.player.getCursor().setDx(0);
    	else if(key == shoot_local){
        	if(player.getAvailableShot()<=0){
    			return;
        	}
    		for(Player p:characters){
    			float dist = (float) Math.sqrt((p.getX()-this.player.getCursor().getX())*(p.getX()-this.player.getCursor().getX()) + (p.getY()-10-this.player.getCursor().getY())*(p.getY()-10-this.player.getCursor().getY()));
    			if(dist<40){
    				p.kill();
    				player.setAvailableShot(player.getAvailableShot()-1);
    				if(player.getAvailableShot()<=0){
    	        		this.player.getCursor().disable();
    	        	}
    				break;
    			}
    		}
        }
    }


	@Override
	public void mouseClicked(int button, int x, int y, int clickcount) {
		
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


	public void reset() {
		// TODO Auto-generated method stub
		
	}


}
