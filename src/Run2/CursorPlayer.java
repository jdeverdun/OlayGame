/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
package Run2;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
public class CursorPlayer {
    private float x = 100, y = 300;
    private boolean onStair = false;
    private Animation[] animations = new Animation[8];
    private float dx = 0, dy = 0;
    private Map map;
    private boolean isPlayer = false;
    // bot life
    private CursorStatus status = CursorStatus.None;
    private float arrivalX = Float.MAX_VALUE;
    private int dureePause = 0;
	private boolean isWinner = false;
    
    
    public enum CursorStatus{Disabled,Enabled,None};// que fait le bot

    public CursorPlayer(Map map) {
        this.map = map;
        status = CursorStatus.Enabled;
        isWinner = false;
        isPlayer = false;
    }
    public CursorPlayer(CursorPlayer p) {// pour copy
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
        SpriteSheet spriteSheet = new SpriteSheet("sprites/cursor/crosshair.png", 64, 64);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
    	
    	/*SpriteSheet spriteSheet = new SpriteSheet("sprites/character2.png", 64, 64);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 8);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 9);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 10);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 11);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 8);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 9);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 10);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 11);*/
    }

    public static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval((int) x - 16, (int) y - 8, 32, 16);
        if(status == CursorStatus.Enabled)// masquer skin
        	g.drawAnimation(animations[0], (int) x - 32, (int) y - 32);
        
    }

    public void update(int delta) {
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

}
