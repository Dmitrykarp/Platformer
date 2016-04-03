package GameState;

import java.awt.*;

/**
 * Created by Support on 02.04.2016.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void init();
    public abstract void draw(Graphics2D g);
    public abstract void update();
    public abstract void keyPresed(int k);
    public abstract void keyReleased(int k);



}
