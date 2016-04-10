package GameState;

import Entity.PlayerInMenu;
import TileMap.Background;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Support on 10.04.2016.
 */
public class Help1State extends GameState {

    private Background bg;
    private Background homebutton;
    private Background rightbutton;
    private Background leftbutton;

    private PlayerInMenu player = new PlayerInMenu(new TileMap(32)){
        public void draw(Graphics2D g){
            setMapPosition();
            g.drawImage(animation.getImage(),145, 145, null);
        }
    };
    private int currentChoise = 2;
    private int width = 30;
    private int height = 30;

    // animations button
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
            //HOME, RIGHT, LEFT
            3, 3, 3

    };

    public Help1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }


    @Override
    public void init() {

       try {
           bg = new Background("/Backgrounds/helpbg1.gif",0);

           BufferedImage spritesheet = ImageIO.read(
                   getClass().getResourceAsStream(
                           "/Tilesets/helpbutton.gif"
                   )
           );

           sprites = new ArrayList<BufferedImage[]>();
           for(int i = 0; i< 3; i++) {
               BufferedImage[] bi = new BufferedImage[numFrames[i]];
               for (int j = 0; j < numFrames[i]; j++) {
                   bi[j] = spritesheet.getSubimage(j*width, i*height, width, height);
               }
               sprites.add(bi);
           }
           homebutton = new Background(sprites.get(0)[0]);
           homebutton.setVector(0, 0);
           homebutton.setPosition(147, 181);
           rightbutton = new Background(sprites.get(1)[0]);
           rightbutton.setVector(0, 0);
           rightbutton.setPosition(179, 181);
           leftbutton = new Background(sprites.get(2)[2]);
           leftbutton.setVector(0, 0);
           leftbutton.setPosition(114, 181);

       }catch (Exception e){
           e.printStackTrace();
       }

    }

    public void select(){
        if (currentChoise == 1){
            //menu
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (currentChoise == 2){
            //help 2
            gsm.setState(GameStateManager.HELP2STATE);
        }
    }

    private void checkButton(){
        if (currentChoise == 0){
            homebutton = new Background(sprites.get(0)[0]);
            homebutton.setVector(0, 0);
            homebutton.setPosition(147, 181);
            rightbutton = new Background(sprites.get(1)[0]);
            rightbutton.setVector(0, 0);
            rightbutton.setPosition(179, 181);
            leftbutton = new Background(sprites.get(2)[2]);
            leftbutton.setVector(0, 0);
            leftbutton.setPosition(114, 181);
        }
        if (currentChoise == 1){
            homebutton = new Background(sprites.get(0)[1]);
            homebutton.setVector(0, 0);
            homebutton.setPosition(147, 181);
            rightbutton = new Background(sprites.get(1)[0]);
            rightbutton.setVector(0, 0);
            rightbutton.setPosition(179, 181);
            leftbutton = new Background(sprites.get(2)[2]);
            leftbutton.setVector(0, 0);
            leftbutton.setPosition(114, 181);
        }
        if (currentChoise == 2){
            homebutton = new Background(sprites.get(0)[0]);
            homebutton.setVector(0, 0);
            homebutton.setPosition(147, 181);
            rightbutton = new Background(sprites.get(1)[1]);
            rightbutton.setVector(0, 0);
            rightbutton.setPosition(179, 181);
            leftbutton = new Background(sprites.get(2)[2]);
            leftbutton.setVector(0, 0);
            leftbutton.setPosition(114, 181);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        player.draw(g);
        homebutton.draw(g);
        leftbutton.draw(g);
        rightbutton.draw(g);
    }

    @Override
    public void update() {
        bg.update();
        player.update();
        checkButton();
        homebutton.update();
        leftbutton.update();
        rightbutton.update();
    }

    @Override
    public void keyPresed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_LEFT){
            if (currentChoise > 0)currentChoise--;
            if (currentChoise == 0) currentChoise = 1;
        }
        if(k == KeyEvent.VK_RIGHT){
            if (currentChoise < 3)currentChoise++;
            if (currentChoise == 3) currentChoise =2;
        }

    }

    @Override
    public void keyReleased(int k) {}
}
