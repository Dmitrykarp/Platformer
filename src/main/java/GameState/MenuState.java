package GameState;

import Entity.PlayerInMenu;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Support on 02.04.2016.
 */
public class MenuState extends GameState {

    private Background bg;
    private Background skyBg;
    private Background treeAple;
    private PlayerInMenu player = new PlayerInMenu(new TileMap(32));

    private int currentChoise = 0;
    private String[] options = {
            "Старт",
            "Помощь",
            "Выход"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gsm){

        this.gsm = gsm;

        try {

            bg = new Background("/Backgrounds/menubg2.gif", 0);
            skyBg = new Background("/Backgrounds/sky.gif",1);
            treeAple = new Background("/Backgrounds/Tree.gif",1);
            bg.setVector(0,0);
            treeAple.setVector(0,0);
            treeAple.setPosition(-70,-17);
            skyBg.setVector(-0.5,0);

            titleColor = new Color(128,0,0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);

            font = new Font("Arial", Font.PLAIN, 12);


        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void init(){}
    public void update(){
        skyBg.update();
        bg.update();
        treeAple.update();
        player.update();
    }
    public void draw(Graphics2D g){
        // draw bg
        skyBg.draw(g);
        bg.draw(g);
        treeAple.draw(g);
        player.draw(g);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Вилы судьбы",100,30);

        //draw menu options
        g.setFont(font);
        for(int i=0; i<options.length; i++){
            if(i == currentChoise){
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.drawString(options[i], 180, 90 + i * 15);

        }

    }

    public void select(){
        if (currentChoise == 0){
            //start
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if (currentChoise == 1){
            //help
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (currentChoise == 2){
            System.exit(0);
        }
    }

    public void keyPresed(int k){
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP){
            currentChoise--;
            if(currentChoise == -1){
                currentChoise = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN){
            currentChoise++;
            if(currentChoise == options.length){
                currentChoise = 0;
            }
        }
    }
    public void keyReleased(int k){}

}
