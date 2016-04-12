package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Audio.AudioPlayer;
import Entity.Enemies.Baran;
import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
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
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private HUD hud;
    private AudioPlayer bgMusic;

    public Level1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public void init(){
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0,0);
        tileMap.setTween(0.07);

        bg = new Background("/Backgrounds/grassbg1.gif",0.1);

        player = new Player(tileMap);
        player.setPosition(100, 100);

        populateEnemies();

        explosions = new ArrayList<Explosion>();

        hud = new HUD(player);

        bgMusic = new AudioPlayer("/Music/level1-1.mp3");
        bgMusic.play();
        bgMusic.setLoop();

    }

    private void populateEnemies(){
        enemies = new ArrayList<Enemy>();

        Baran b;
        Point[] points = new Point[]{
                new Point(200,190),
                new Point(860,190),
                new Point(1525,190),
                new Point(1680,190),
                new Point(1800,190),
        };

        for (int i = 0; i<points.length; i++){
         b = new Baran(tileMap);
            b.setPosition(points[i].x, points[i].y);
            enemies.add(b);
        }
    }

    private void checkPlayerIsDead(){
        if(player.isDead()){
            bgMusic.stop();
            gsm.setState(GameStateManager.GAMEOVER);
        }
    }

    public void update(){

        // update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

        //set background
        bg.setPosition(tileMap.getx(), tileMap.gety());

        // attack enemies
        player.checkAttack(enemies);

        //update all enemies
        for (int i = 0; i < enemies.size(); i ++){
            Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()){
               enemies.remove(i);
               i--;
               explosions.add( new Explosion(e.getx(), e.gety()));
            }
        }

        // update explosions
        for (int i = 0; i < explosions.size(); i ++){
            explosions.get(i).update();
            if (explosions.get(i).shouldRemove()){
                explosions.remove(i);
                i++;
            }
        }
        checkPlayerIsDead();
    }

    public void draw(Graphics2D g){

        //draw background
        bg.draw(g);

        // draw tile
        tileMap.draw(g);

        // draw enemies
        for (int i = 0; i < enemies.size(); i ++){
            enemies.get(i).draw(g);
        }

        // draw explosions
        for (int i = 0; i < explosions.size(); i ++){
            explosions.get(i).setMapPosition((int)tileMap.getx(), (int) tileMap.gety());
            explosions.get(i).draw(g);
        }

        // draw player
        player.draw(g);

        //draw HUD
        hud.draw(g);
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
