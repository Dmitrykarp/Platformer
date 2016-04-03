package GameState;

import java.awt.*;

import Main.GamePanel;
import TileMap.*;

/**
 * Created by Support on 02.04.2016.
 */
public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;

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
    }
    public void update(){

    }
    public void draw(Graphics2D g){

        //draw background
        bg.draw(g);

        // draw tile
        tileMap.draw(g);

    }
    public void keyPresed(int k){}
    public void keyReleased(int k){}

}
