package Dance3;

import java.util.ArrayList;

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
    private ArrayList<Integer> mapStep;
    private ArrayList<Boolean> stepOpt;
    private int currentMap = -1;
	private ArrayList<Boolean> facultativeWall;
    public void init() throws SlickException {
		this.tiledMap = new TiledMap("map/dancefloor_move_alone_map.tmx");
		currentMap = 0;
		resetMapStep();
    }

    public void resetMapStep(){
    	mapStep = new ArrayList<Integer>(4);
    	for(int i = 0;i<4;i++)
    		mapStep.add(0);
    	stepOpt = new ArrayList<Boolean>(4);
    	for(int i = 0;i<4;i++)
    		stepOpt.add(true);
    	facultativeWall = new ArrayList<Boolean>(4);
    	for(int i = 0;i<4;i++)
    		facultativeWall.add(false);
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
            for(int i = 0 ; i < stepOpt.size(); i++){
            	if(stepOpt.get(i))
            		this.tiledMap.render(0, 0, 5+i);
            }
            for(int i = 0 ; i < mapStep.size(); i++){

            	int cmapstep = mapStep.get(i);
            	System.out.println(cmapstep);
                if(cmapstep<=3)
                	this.tiledMap.render(0, 0, 21+(i));
                if(cmapstep<=2)
                	this.tiledMap.render(0, 0, 17+(i));
                if(cmapstep<=1)
                	this.tiledMap.render(0, 0, 13+(i));
                if(cmapstep<=0)
                	this.tiledMap.render(0, 0, 9+(i));
            }
            for(int i = 0 ; i < facultativeWall.size(); i++){
            	if(facultativeWall.get(i))
            		this.tiledMap.render(0, 0, 25+i);
            }

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

	public ArrayList<Integer> getMapStep() {
		return mapStep;
	}

	public void setMapStep(ArrayList<Integer> mapStep) {
		this.mapStep = mapStep;
	}

	public ArrayList<Boolean> getStepOpt() {
		return stepOpt;
	}

	public void setStepOpt(ArrayList<Boolean> stepOpt) {
		this.stepOpt = stepOpt;
	}

	public ArrayList<Boolean> getFacultativeWall() {
		return facultativeWall;
	}

	public void setFacultativeWall(ArrayList<Boolean> facultativeWall) {
		this.facultativeWall = facultativeWall;
	}


}
