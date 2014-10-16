/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
package Dance1;


import java.util.ArrayList;

import lesson14.CharactersToolsGlobal;
import lesson14.Engine;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Run1.Run1.GameMode;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * Représente le joueur
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class Arrow {
    private float x = 600, y = 300;
    private boolean onStair = false;
    private Animation[] animations = new Animation[10];
    private float dx = 0, dy = 0;
    private Map map;
    // bot life

    public static final float default_arrivalY = 100;
    private float arrivalY = default_arrivalY;
    private int dureePause = 0;
    private boolean isfixed = false;
    private Direction orientation = null;

	private boolean disable = false;
    public static float arrowSpeed = 0.6f;
    private int ticTime = -1;
    
    public enum Direction{left,right,up,down};

    public Arrow(Map map, boolean isfixed, Direction dir) {
        this.map = map;
        this.orientation = dir;
        setIsfixed(isfixed);
        reset();
    }
    public Arrow(Arrow p) {// pour copy
        this.map = p.map;
        this.x = p.x;
        this.y = p.y;
        this.onStair = p.onStair;
        this.animations = p.animations;
        this.dx = p.dx;
        this.dy = p.dy;
    }

    public void init() throws SlickException {
    	switch(this.orientation){
    	case up:
    		this.animations = CharactersToolsGlobal.getArrowAnimations().get(1);
    		break;
    	case down:
    		this.animations = CharactersToolsGlobal.getArrowAnimations().get(2);
    		break;
    	case left:
    		this.animations = CharactersToolsGlobal.getArrowAnimations().get(0);
    		break;
    	case right:
    		this.animations = CharactersToolsGlobal.getArrowAnimations().get(3);
    		break;
    		
    	}
        
        
       // background.play();
    }

    public static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y, int duration) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), duration);
        }
        return animation;
    }

    public void render(Graphics g) {
    	if(isDisable())
    		return;
		if(!isfixed && (this.y - arrivalY)<5){
			if((this.y - arrivalY)<-3)
				setDisable(true);
			g.drawAnimation(animations[3], (int) x - 32, (int) y- (this.y - arrivalY) - 60);
		}else{
			if(!isfixed)
				g.drawAnimation(animations[2], (int) x - 32, (int) y - 60);
			else
				g.drawAnimation(animations[8], (int) x - 32, (int) y - 60);
		}
    }

   
	public void update(int delta) {
		if(isDisable())
			return;
        if (this.isMoving()) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
            this.x = futurX;
            this.y = futurY;
            
        }


    	
    }

    private float getFuturX(int delta) {
        return this.x + .15f * delta * dx;
    }

    private float getFuturY(int delta) {
        float futurY = this.y + .15f * delta * dy;
        if (this.onStair) {
            futurY = futurY - .15f * dx * delta;
        }
        return futurY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getDirection() {
        int direction = 3;
        if (dx > 0 && dx >= Math.abs(dy)) {
            direction = 3;
        } else if (dx < 0 && -dx >= Math.abs(dy)) {
            direction = 1;
        } else if (dy < 0) {
            direction = 0;
        } else if (dy > 0) {
            direction = 2;
        }
        return direction;
    }

    public void setDirection(int direction) {
        switch (direction) {
        case 0:  dx =  0; dy = -1; break;
        case 1:  dx = -1; dy =  0; break;
        case 2:  dx =  0; dy =  1; break;
        case 3:  dx =  1; dy =  0; break;
        default: dx =  0; dy =  0; break;
        }
    }

    public boolean isMoving() {
        return dx != 0 || dy != 0;
    }

    public void stopMoving() {
        dx = 0;
        dy = 0;
    }

    public boolean isOnStair() {
        return onStair;
    }

    public void setOnStair(boolean onStair) {
        this.onStair = onStair;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

	public Animation[] getAnimations() {
		return animations;
	}

	public void setAnimations(Animation[] animations) {
		this.animations = animations;
	}

	public void reset() {
		try {
			init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDisable(false);
		switch(this.orientation){
    	case up:
    		x = Engine.WINDOW_SIZE.width-(192-32);
    		break;
    	case down:
    		x = Engine.WINDOW_SIZE.width-(128-32);
    		break;
    	case left:
    		x = Engine.WINDOW_SIZE.width-(256-32);
    		break;
    	case right:
    		x = Engine.WINDOW_SIZE.width-32;
    		break;
    	}
		if(isfixed){
			y = arrivalY;
			dx = 0;
		    dy = 0;
		}else{
			y = (float) (Engine.WINDOW_SIZE.height);
			dx = 0;
		    dy = -arrowSpeed;
		}
	    onStair = false;
	    ticTime = -1;
	    arrivalY = default_arrivalY;
	    dureePause = 0;
	}
	/**
	 * @return the isfixed
	 */
	public boolean isIsfixed() {
		return isfixed;
	}
	/**
	 * @param isfixed the isfixed to set
	 */
	public void setIsfixed(boolean isfixed) {
		this.isfixed = isfixed;
	}
	/**
	 * @return the disable
	 */
	public boolean isDisable() {
		return disable;
	}
	/**
	 * @param disable the disable to set
	 */
	public void setDisable(boolean disable) {
		this.disable = disable;
	}

    public Direction getOrientation() {
		return orientation;
	}
	public void setOrientation(Direction orientation) {
		this.orientation = orientation;
	}
	public void setTicTime(Integer tic) {
		ticTime = tic;
	}
	public int getTicTime(){
		return ticTime;
	}

}
