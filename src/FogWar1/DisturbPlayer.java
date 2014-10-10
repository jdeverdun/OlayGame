/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
package FogWar1;


import java.util.ArrayList;

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
public class DisturbPlayer {
    private float x = 600, y = 300;
    private boolean onStair = false;
    private Animation[] animations = new Animation[2];
    private float dx = 0, dy = 0;
    private Map map;
    private boolean isPlayer = false;
    // bot life
    private CursorStatus status = CursorStatus.None;
    private float arrivalX = Float.MAX_VALUE;
    private int dureePause = 0;
	private boolean isWinner = false;
	//timers
	private int tic = 0;
    private ArrayList<Integer[]> t1;
    private ArrayList<Integer[]> t2;
    private Music background;

    public enum CursorStatus{Disabled,Enabled,None};// que fait le bot

    public DisturbPlayer(Map map) {
        this.map = map;
        status = CursorStatus.Enabled;
        isWinner = false;
        isPlayer = false;
        try {
			background = new Music("sound/He-man.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public DisturbPlayer(DisturbPlayer p) {// pour copy
        this.map = p.map;
        this.x = p.x;
        this.y = p.y;
        this.onStair = p.onStair;
        this.animations = p.animations;
        this.dx = p.dx;
        this.dy = p.dy;
        this.isWinner = p.isWinner;
        this.isPlayer = p.isPlayer;
        this.status = p.status;
    }

    public void init() throws SlickException {
    	t1 = new ArrayList<Integer[]>();
    	t2 = new ArrayList<Integer[]>();
    	SpriteSheet spriteSheet = new SpriteSheet("sprites/heman.png", 500, 375);
        this.animations[0] = loadAnimation(spriteSheet, 1, 8, 0,200);
        spriteSheet = new SpriteSheet("sprites/he-dance.png", 320, 240);
        this.animations[1] = loadAnimation(spriteSheet, 1, 20, 0,100);
        tic = 0;
        
        // timers des tics
        //t1
        t1.add(new Integer[]{31,38});
        t1.add(new Integer[]{44,51});
        t1.add(new Integer[]{86,92});
        t1.add(new Integer[]{99,106});
        //t2 
        t2.add(new Integer[]{0,4});
        t2.add(new Integer[]{14,18});
        t2.add(new Integer[]{83,86});

        background.stop();
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
       // g.drawAnimation(animations[0], (int) x - 32, (int) y - 32);
    	if(isTimer1())
    		animations[0].draw(70, 70, Math.round(500*2.15), Math.round(375*2.15));
    	else if(isTimer2())
    		animations[1].draw(70, 50, Math.round(500*2.3), Math.round(375*2.3));
        
    }

    private boolean isTimer2() {
    	for(Integer[] v:t2)
    		if(tic>v[0]*60 && tic<(v[1]*60))
    			return true;
		return false;
	}
	private boolean isTimer1() {
		for(Integer[] v:t1)
    		if(tic>v[0]*60 && tic<(v[1]*60))
    			return true;
		return false;
	}
	public void update(int delta) {
    	tic++;
    	if(status == CursorStatus.Disabled){
    		this.setDx(0.0f);
    		this.setDy(0.0f);
    		return;
    	}
        if (this.isMoving()) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
            boolean collision = this.map.isCollision(futurX, futurY);
            if (collision) {
                stopMoving();
            } else {
                this.x = futurX;
                this.y = futurY;
            }
            
            
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


	public void disable() {
		status = CursorStatus.Disabled;
	}
	public void setIsWinner(boolean b) {
		isWinner  = true;
	}
	public boolean isWinner() {
		// TODO Auto-generated method stub
		return isWinner;
	}
	public boolean isPlayer() {
		return isPlayer;
	}
	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}
	public void reset() {
		x = 100;
		y = 300;
	    onStair = false;
	    dx = 0;
	    dy = 0;
	    status = CursorStatus.Enabled;
	    arrivalX = Float.MAX_VALUE;
	    dureePause = 0;
		isWinner = false;
	}
	public void stopMusic() {
		background.stop();
	}

}
