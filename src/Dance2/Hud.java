package Dance2;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class Hud {

	public static final int P_BAR_X = 10;
    public static final int P_BAR_Y = 10;
    public static final int BAR_X = 42 + P_BAR_X;
    public static final int LIFE_BAR_Y = 4 + P_BAR_Y;
    public static final int MANA_BAR_Y = 10 + P_BAR_Y;
    public static final int XP_BAR_Y = 30 + P_BAR_Y;
    public static final int BAR_WIDTH = 322;
    public static final int BAR_HEIGHT = 12;
    public static float XP_WIDTH_PERC = 0.0f;
    public static float MANA_WIDTH_PERC = 0.0f;
    private static final Color LIFE_COLOR = new Color(255, 0, 0);
    private static final Color MANA_COLOR = new Color(0, 0, 255);
    private static final Color XP_COLOR = new Color(0, 255, 0);

    private Image playerbars;

    public void init() throws SlickException {
        this.playerbars = new Image("hud/healthbar_0.png");//player-bar.png");
    }

    public void render(Graphics g) {
        g.resetTransform();
        g.drawImage(playerbars, P_BAR_X, P_BAR_Y);
        g.setColor(MANA_COLOR);
        g.fillRect(BAR_X + MANA_WIDTH_PERC * BAR_WIDTH, MANA_BAR_Y, 7, BAR_HEIGHT);
        g.setColor(XP_COLOR);
        g.fillRect(BAR_X, XP_BAR_Y, XP_WIDTH_PERC * BAR_WIDTH, BAR_HEIGHT);

        
    }

}
