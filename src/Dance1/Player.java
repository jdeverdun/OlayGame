/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
package Dance1;


import java.awt.Point;

import lesson14.Engine;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Dance1.Arrow.Direction;
import Dance1.Dance1.GameMode;
import Dance1.Player.BotStatus;


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
    // joueur
    private int numPlayer = -1;
    private int availableShot = 3;
    // bot life
    private BotStatus status = BotStatus.None;
    private float arrivalX = Float.MAX_VALUE;
    private int dureePause = 0;
	private boolean isWinner = false;
    
	private boolean isDancing = false;
	private boolean gauche = true;
	public int compteur = 0;
	private boolean shooter = false;
	private boolean highlight;
	
    public enum BotStatus{MoveLeft,Wait,MoveUp,MoveRight,MoveDown,Dance,Dead,None};// que fait le bot

    public Player(Map map) {
        this.map = map;
        isWinner = false;
        isPlayer = false;
        isDancing = false;
        shooter = false;
        gauche = true;
        compteur = 0;
        highlight = false;
        setAvailableShot(Dance1.numPlayers+1);
        
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
        this.shooter = p.shooter;
        setAvailableShot(Dance1.numPlayers);
    }

    public Player(Map map2, boolean b) {
		this(map2);
		shooter = b;
		isPlayer = true;
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


    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval((int) x - 16, (int) y - 8, 32, 16);
        
        if(isPlayer()){
	        if(status == BotStatus.Dead)
	        	g.drawAnimation(animations[0],(int) x - 32,(int) y - 60);
	        else{
	        	if(highlight)
	        		animations[2].draw((int) x - 64, (int) y - 120, Math.round(animations[2].getWidth()*2f), Math.round(animations[2].getHeight()*2f));
	        	else
	        		g.drawAnimation(animations[getDirection() + (isMoving() ? 4 : 0)], (int) x - 32, (int) y - 60);
	        }
        }
        if(!isPlayer()){

        	if(isDancing() && status != BotStatus.Dead){
		        if(gauche)
		        	g.drawAnimation(animations[5], (int) x - 32, (int) y - 60);
		        else
		        	g.drawAnimation(animations[7], (int) x - 32, (int) y - 60);


		    	
        	}else{
        		if(status == BotStatus.Dead)
    	        	g.drawAnimation(animations[0],(int) x - 32,(int) y - 60);
    	        else
    	        	g.drawAnimation(animations[getDirection() + (isMoving() ? 4 : 0)], (int) x - 32, (int) y - 60);
        	}
        }
    }

    public void update(int delta) {
    	if(status == BotStatus.Dead || (!isPlayer() && isDancing()) || (isPlayer() && isDancing() && this.dx>0.01f)){// ||
    			//(isPlayer() && !isDancing() && this.dx<0.01f)){
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

    public float distanceTo(Player p){
    	return (float) Point.distance(p.getX(), p.getY(), getX(), getY());
    	//return (float) Math.sqrt(Math.pow(p.getX()-getX(), 2)+Math.pow(p.getY()-getY(), 2));
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
	public void randomOrder() {

		// pause ou bouge ?
		if(Math.random()<0.6){
			status = BotStatus.Wait;
			dureePause = 1 + (int)(Math.random() * ((1800 - 1) + 1));
		}else{
			// duree du mouvement
			float distance = 1 + (int)(Math.random() * ((150 - 1) + 1));
			arrivalX = getX() + distance;
			status = BotStatus.MoveRight;
		}
		
	}
	
	public void kill() {
		status = BotStatus.Dead;
	}
	public void setIsWinner(boolean b) {
		isWinner  = true;
	}
	public boolean isShooter() {
		return shooter;
	}
	public void setShooter(boolean shooter) {
		this.shooter = shooter;
	}
	public boolean isGauche() {
		return gauche;
	}
	public void setGauche(boolean gauche) {
		this.gauche = gauche;
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
	    status = BotStatus.None;
	    arrivalX = Float.MAX_VALUE;
	    dureePause = 0;
		isWinner = false;
		numPlayer = -1;
        isDancing = false;
        highlight = false;
		setAvailableShot(Dance1.numPlayers);
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
		if(this.availableShot==0){
			status = BotStatus.Dead;
		}
	}
	public boolean isDancing() {
		return isDancing;
	}
	public void setDancing(boolean isDancing) {
		this.isDancing = isDancing;
	}
	public void logKey(int key) {
		System.out.println(Dance1.background.getPosition()+"@@"+key);
		
	}
	public void move(Direction key){
		if(status==BotStatus.Dead)
			return;
		if(key == Direction.up){
    		this.setDx(0.0f);
    		this.setDy(-0.0001f);
    	}else if(key == Direction.down){
    		this.setDx(0.0f);
    		this.setDy(0.0001f);
    	}else if(key == Direction.right){
    		this.setDy(0.0f);
    		this.setDx(0.0001f);
    	}else if(key == Direction.left){
    		this.setDy(0.0f);
    		this.setDx(-0.0001f);//return;
    	}
	}

	public void incrementCompteur(){
		compteur++;
	}
	public void setCompteur(int t){
		compteur = t;;
	}
	public void setHighlight(boolean b) {
		highlight = b;
	}
}
