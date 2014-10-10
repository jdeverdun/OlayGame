package lesson14;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class Menu extends BasicGameState{
 
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.drawString("1. Run !!!", 50, 50);
        g.drawString("2. Run & Dance!!!", 50, 100);
        g.drawString("3. Run & be disturb!!!", 50, 150);
        g.drawString("4. Fog War", 50, 200);
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_1))
        	game.enterState(States.GAME_RUN1);
        if(container.getInput().isKeyPressed(Input.KEY_2))
        	game.enterState(States.GAME_RUN2);
        if(container.getInput().isKeyPressed(Input.KEY_3))
        	game.enterState(States.GAME_RUN3);
        if(container.getInput().isKeyPressed(Input.KEY_4))
        	game.enterState(States.GAME_FOGWAR1);
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return States.MENU;
    }
 
}