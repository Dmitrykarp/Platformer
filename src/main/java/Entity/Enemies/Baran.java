package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Support on 07.04.2016.
 */
public class Baran extends Enemy {

    private BufferedImage[] sprites;

    public Baran(TileMap tm){
        super(tm);

        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 64;
        height = 60;
        cwidth = 35;
        cheight = 30;

        health = maxHealth = 2;
        damage = 1;

        //load sprite

        try{

            BufferedImage spritesheet = ImageIO.read( getClass().getResourceAsStream("/Sprites/Enemies/baran.gif"));

            sprites = new BufferedImage[2];
            for (int i=0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i*width,0,width,height);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right = true;
        facingRight = true;

    }

    private void getNextposition(){

        // movent
        if(left){
            dx -= moveSpeed;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }
        else if (right){
            dx += moveSpeed;
            if (dx > maxSpeed){
                dx = maxSpeed;
            }
        }

        // falling
        if (falling){
            dy += fallSpeed;
        }
    }

    public void update(){

        //update position
        getNextposition();
        checkTileMapCollision();
        setPosition(xtemp,ytemp);

        // check flinching
        if(flinching){
            long elapsed = (System.nanoTime() - flinchTime) / 1000000;
            if(elapsed > 400) flinching = false;
        }

        //if hit a wall
        if(right && dx ==0){
            right = false;
            left = true;
            facingRight = false;
        }
        else if (left && dx ==0){
            left = false;
            right = true;
            facingRight = true;
        }

        //animation update
        animation.update();

    }

    public void draw(Graphics2D g){

        if(notOnScreen()) return;

        setMapPosition();


        if (facingRight){
                g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width - 10), (int) (y + ymap - (height / 2)) + 20, -width / 2, height / 2, null);

        }
        else {
                g.drawImage(animation.getImage(),(int)(x + xmap - width / 2 ) + 10, (int) (y + ymap - height / 2) + 20, width / 2, height / 2, null);


        }



    }

}
