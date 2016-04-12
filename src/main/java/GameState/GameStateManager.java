package GameState;

import java.awt.*;

/**
 * Created by Support on 02.04.2016.
 */
public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;

    public static final int NUMGAMESTATES = 6;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int HELP1STATE = 2;
    public static final int HELP2STATE = 3;
    public static final int HELP3STATE = 4;
    public static final int GAMEOVER = 5;


    public GameStateManager(){

        gameStates = new GameState[NUMGAMESTATES];

        currentState = MENUSTATE;
        loadState(currentState);
    }

    public void loadState(int state){
        if (state == MENUSTATE){
            gameStates[state] = new MenuState(this);
        }
        if (state == LEVEL1STATE){
            gameStates[state] = new Level1State(this);
        }
        if (state == HELP1STATE){
            gameStates[state] = new Help1State(this);
        }
        if (state == HELP2STATE){
            gameStates[state] = new Help2State(this);
        }
        if (state == HELP3STATE){
            gameStates[state] = new Help3State(this);
        }
        if (state == GAMEOVER){
            gameStates[state] = new GameOverState(this);
        }
    }

    public void unloadState(int state){
        gameStates[state] = null;
    }

    public void setState( int state){
        int temp = currentState;
        currentState = state;
        loadState(currentState);
        unloadState(temp);
    }

    public void update(){
        try{
            gameStates[currentState].update();
        }catch (Exception e){

        }

    }

    public void draw(Graphics2D g){
        try {
            gameStates[currentState].draw(g);
        }catch (Exception e){

        }

    }


    public void keyPresed(int k){
        gameStates[currentState].keyPresed(k);
    }

    public void keyReleased(int k){
        gameStates[currentState].keyReleased(k);
    }

}
