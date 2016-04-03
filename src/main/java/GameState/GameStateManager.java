package GameState;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Support on 02.04.2016.
 */
public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int MENUSTATE1 = 2;
    public static final int LEVEL1STATE = 1;


    public GameStateManager(){

        gameStates = new ArrayList<GameState>();

        currentState = 0;
        gameStates.add(new MenuStateTest(this));
        gameStates.add(new Level1State(this));



    }

    public void setState( int state){
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update(){
        gameStates.get(currentState).update();

    }

    public void draw(Graphics2D g){
        gameStates.get(currentState).draw(g);

    }


    public void keyPresed(int k){
        gameStates.get(currentState).keyPresed(k);
    }

    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }

}
