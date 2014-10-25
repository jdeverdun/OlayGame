package Dance2;

import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class TriggerController {

    private Map map;
    private Player player;

    public TriggerController(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public void update() throws SlickException {
        this.player.setOnStair(false);
        for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) {
            if (isInTrigger(objectID)) {
                if (map.getMapStep()==0 && "trigger1".equals(this.map.getObjectType(objectID))) {
                	if(!player.isDancing()) player.setDancing(true);
                	if(player.checkLastTiming()){
                		player.setDancing(false);
                		player.setReadyForNextStep(true);
                		player.getLastPress().clear();
                	}
                } else if (map.getMapStep()==1 && "trigger2".equals(this.map.getObjectType(objectID))) {
                	if(!player.isDancing()) player.setDancing(true);
                	if(player.checkLastTiming()){
                		player.setDancing(false);
                		player.setReadyForNextStep(true);
                	}
                } else if (map.getMapStep()==2 && "trigger3".equals(this.map.getObjectType(objectID))) {
                	if(!player.isDancing()) player.setDancing(true);
                	if(player.checkLastTiming()){
                		player.setDancing(false);
                		player.setReadyForNextStep(true);
                	}
                }else if (map.getMapStep()==3 && "trigger4".equals(this.map.getObjectType(objectID))) {
                	if(!player.isDancing()) player.setDancing(true);
                	if(player.checkLastTiming()){
                		player.setDancing(false);
                		player.setReadyForNextStep(true);
                	}
                }else if (map.getMapStep()==4 && "trigger5".equals(this.map.getObjectType(objectID))) {
                	if(!player.isDancing()) player.setDancing(true);
                	if(player.checkLastTiming()){
                		player.setDancing(false);
                		player.setReadyForNextStep(true);
                	}
                }
            }
        }
    }


	private boolean isInTrigger(int id) {
        return this.player.getX() > this.map.getObjectX(id)
                && this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
                && this.player.getY() > this.map.getObjectY(id)
                && this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
    }

    private void teleport(int objectID) {
        this.player.setX(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x",
                Float.toString(this.player.getX()))));
        this.player.setY(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y",
                Float.toString(this.player.getY()))));
    }

    private void winGame(int objectID) {
		player.setIsWinner(true);
	}

    private void changeMap(int objectID) throws SlickException {
        this.teleport(objectID);
        String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
        if (!"undefined".equals(newMap)) {
            this.map.changeMap("map/" + newMap);
        }
    }

}
