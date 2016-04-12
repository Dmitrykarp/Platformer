package GameState;

import Audio.AudioPlayer;
import Entity.PlayerInGameOver;
import Entity.PlayerInMenu;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Support on 02.04.2016.
 */
public class GameOverState extends GameState {

    private Background bg;
    private AudioPlayer bgMusic;
    private PlayerInGameOver player = new PlayerInGameOver(new TileMap(32)); //TODO upd

    public GameOverState(GameStateManager gsm){

        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/gameoverbg.gif", 0);
            bg.setVector(0,0);
        }catch (Exception e){
            e.printStackTrace();
        }

        bgMusic = new AudioPlayer("/Music/Game_Over.mp3");
        bgMusic.play();
        bgMusic.setLoop();
    }

    public void init(){}
    public void update(){
        bg.update();
        player.update();
    }
    public void draw(Graphics2D g){
        // draw bg
        bg.draw(g);
        player.draw(g);

    }

    public void select(){
        bgMusic.stop();
        gsm.setState(GameStateManager.MENUSTATE);
    }

    public void keyPresed(int k){
        if(k == KeyEvent.VK_ESCAPE){
            select();
        }
    }
    public void keyReleased(int k){}

}
