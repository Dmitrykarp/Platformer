package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import Entity.Player;
import Main.GamePanel;
import TileMap.*;

/**
 * Created by Support on 02.04.2016.
 */
public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    public Level1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0,0);

        bg = new Background("/Backgrounds/grassbg1.gif",0.1);

        player = new Player(tileMap);
        player.setPosition(100, 100);
    }
    public void update(){

        // update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

    }
    public void draw(Graphics2D g){

        //draw background
        bg.draw(g);

        // draw tile
        tileMap.draw(g);

        // draw player
        player.draw(g);
    }
    public void keyPresed(int k){

        if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A){ player.setLeft(true); }
        if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D){ player.setRight(true); }
        if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S){ player.setDown(true); }
        if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W){ player.setUp(true); }
        if (k == KeyEvent.VK_SPACE) player.setJumping(true);
        if (k == KeyEvent.VK_J) player.setGliding(true);
        if (k == KeyEvent.VK_L) player.setScratching();
        if (k == KeyEvent.VK_K) player.setFiring();


    }
    public void keyReleased(int k){

        if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A){ player.setLeft(false); }
        if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D){ player.setRight(false); }
        if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S){ player.setDown(false); }
        if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W){ player.setUp(false); }
        if (k == KeyEvent.VK_SPACE) player.setJumping(false);
        if (k == KeyEvent.VK_J) player.setGliding(false);

    }

}
