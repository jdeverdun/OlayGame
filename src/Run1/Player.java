/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
package Run1;


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
public class Player {
    private float x = 100, y = 300;
    private boolean onStair = false;
    private Animation[] animations = new Animation[8];
    private float dx = 0, dy = 0;
    private Map map;
    private boolean isPlayer = false;
    private CursorPlayer cursor;
    // joueur
    private int numPlayer = -1;
    private int availableShot = 2;
    // bot life
    private BotStatus status = BotStatus.None;
    private float arrivalX = Float.MAX_VALUE;
    private int dureePause = 0;
	private boolean isWinner = false;
    
    
    public enum BotStatus{MoveLeft,Wait,MoveUp,MoveRight,MoveDown,Dead,None};// que fait le bot

    public Player(Map map) {
        this.map = map;
        isWinner = false;
        isPlayer = false;
        cursor = null;
        setAvailableShot(2);
        
    }
    public Player(Player p) {// pour copy
        this.map = p.map;
        this.x = p.x;
        this.y = p.y;
        this.onStair = p.onStair;
        this.animations = p.animations;
        this.dx = p.dx;
        this.dy = p.dy;
        this.isWinner = p.isWinner;
        this.isPlayer = p.isPlayer;
        this.cursor = p.cursor;
        setAvailableShot(2);
    }

    public void init() throws SlickException {
        /*SpriteSheet spriteSheet = new SpriteSheet("sprites/character.png", 64, 64);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);*/
    	
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
        if(status == BotStatus.Dead)
        	g.drawAnimation(animations[0],(int) x - 32,(int) y - 60);
        else
        	g.drawAnimation(animations[getDirection() + (isMoving() ? 4 : 0)], (int) x - 32, (int) y - 60);
        
    }

    public void update(int delta) {
    	if(status == BotStatus.Dead){
    		this.setDx(0.0f);
    		this.setDy(0.0f);
    		return;
    	}
        // bot ia
    	if(!isPlayer){
	        if(status != BotStatus.None){
	        	switch(status){
	        	case MoveRight:
	        		if(this.x>=arrivalX){
	        			status = BotStatus.None;
	    	        	arrivalX = Float.MAX_VALUE;
	    	        	dureePause = 0;
	    	        	this.setDx(0.0f);
	        		}else{
	        			this.setDx(0.3f);
	        		}
	        		break;
	        	case Wait:
	        		if(dureePause <= 0){
	        			status = BotStatus.None;
			        	arrivalX = Float.MAX_VALUE;
			        	dureePause = 0;
			        	this.setDx(0.0f);
	        		}else{
	        			dureePause--;
	        			this.setDx(0.0f);
	        		}
	        		break;
	        	}
	        }
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

	public boolean isAwaitingOrder() {
		// TODO Auto-generated method stub
		return status==BotStatus.None;
	}
	public void randomOrder(GameMode currentGameMode) {

		// pause ou bouge ?
		if(Math.random()<0.6){
			status = BotStatus.Wait;
			dureePause = 1 + (int)(Math.random() * ((2000 - 1) + 1));
		}else{
			// duree du mouvement
			float distance = 1 + (int)(Math.random() * ((150 - 1) + 1));
			arrivalX = getX() + distance;
			switch(currentGameMode){
			case Run1:
				status = BotStatus.MoveRight;
				break;
			}
		}
		
	}
	public CursorPlayer getCursor() {
		return cursor;
	}
	public void setCursor(CursorPlayer cursor) {
		this.cursor = cursor;
	}
	public void kill() {
		status = BotStatus.Dead;
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
		if(isPlayer){
			cursor = new CursorPlayer(map);
			try {
				cursor.init();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void reset() {
		x = 100;
		y = 300;
	    onStair = false;
	    dx = 0;
	    dy = 0;
	    status = BotStatus.None;
	    arrivalX = Float.MAX_VALUE;
	    dureePause = 0;
		isWinner = false;
		numPlayer = -1;
		setAvailableShot(2);
	}
	/**
	 * @return the numPlayer
	 */
	public int getNumPlayer() {
		return numPlayer;
	}
	/**
	 * @param numPlayer the numPlayer to set
	 */
	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}
	public BotStatus getStatus() {
		return status;
	}
	public void setStatus(BotStatus status) {
		this.status = status;
	}
	/**
	 * @return the availableShot
	 */
	public int getAvailableShot() {
		return availableShot;
	}
	/**
	 * @param availableShot the availableShot to set
	 */
	public void setAvailableShot(int availableShot) {
		this.availableShot = availableShot;
	}

}
