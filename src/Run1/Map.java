package Run1;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class Map {
    private TiledMap tiledMap;
    private int currentMap = -1;
    public void init() throws SlickException {
    	float r = (float) Math.random();
    	if(r<0.33){
    		this.tiledMap = new TiledMap("map/run_map.tmx");
    		currentMap = 0;
    	}else{
    		if(r<0.66){
	    		this.tiledMap = new TiledMap("map/run_map2.tmx");
	    		currentMap = 1;
    		}else{
    			this.tiledMap = new TiledMap("map/run_map3.tmx");
	    		currentMap = 2;
    		}
    	}
    }

    public void renderBackground() {
    	switch(currentMap){
    	case 0:
	        this.tiledMap.render(0, 0, 0);
	        this.tiledMap.render(0, 0, 1);
	        this.tiledMap.render(0, 0, 2);
	        break;
    	case 1:case 2:
    		this.tiledMap.render(0, 0, 0);
	        this.tiledMap.render(0, 0, 1);
	        this.tiledMap.render(0, 0, 2);
    		break;
    	}
    }

    public void renderForeground() {
    	switch(currentMap){
    	case 0:
    		this.tiledMap.render(0, 0, 3);
            this.tiledMap.render(0, 0, 4);
	        break;
    	case 1:case 2:
    		this.tiledMap.render(0, 0, 3);
            this.tiledMap.render(0, 0, 4);
            this.tiledMap.render(0, 0, 5);
            this.tiledMap.render(0, 0, 6);
    		break;
    	}
        
    }

    public boolean isCollision(float x, float y) {
        int tileW = this.tiledMap.getTileWidth();
        int tileH = this.tiledMap.getTileHeight();
        int logicLayer = this.tiledMap.getLayerIndex("logic");
        Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    public void changeMap(String file) throws SlickException {
        this.tiledMap = new TiledMap(file);
    }

    public int getObjectCount() {
        return this.tiledMap.getObjectCount(0);
    }

    public String getObjectType(int objectID) {
        return this.tiledMap.getObjectType(0, objectID);
    }

    public float getObjectX(int objectID) {
        return this.tiledMap.getObjectX(0, objectID);
    }

    public float getObjectY(int objectID) {
        return this.tiledMap.getObjectY(0, objectID);
    }

    public float getObjectWidth(int objectID) {
        return this.tiledMap.getObjectWidth(0, objectID);
    }

    public float getObjectHeight(int objectID) {
        return this.tiledMap.getObjectHeight(0, objectID);
    }

    public String getObjectProperty(int objectID, String propertyName, String def) {
        return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
    }

}
