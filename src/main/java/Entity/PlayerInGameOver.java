package Entity;

import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Support on 03.04.2016.
 */
public class PlayerInGameOver extends MapObject {

    // animations
    private BufferedImage[] sprites;
    private BufferedImage[] spritesDown;
    private boolean animPlayOne;
    private final int numFrames = 12;
    private Animation animationTwo;



    public PlayerInGameOver(TileMap tm) {
        super(tm);

        width = 38;
        height = 32;
        animPlayOne = false;

        //load sprites
        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Sprites/Player/playerInGameOver.gif"
                    )
            );

            sprites = new BufferedImage[12];
            spritesDown = new BufferedImage[2];
            for (int i = 0; i<sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height );
                if(i==10) spritesDown[0] = spritesheet.getSubimage(i * width, 0, width, height );
                if(i==11) spritesDown[1] = spritesheet.getSubimage(i * width, 0, width, height );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(200);

        animationTwo = new Animation();
        animationTwo.setFrames(spritesDown);
        animationTwo.setDelay(200);

    }

    public void update(){
        setPosition(xtemp, ytemp);

        // animation
        if (!animPlayOne) {
            animation.update();
            if (animation.hasPlayedOnce()){
                animPlayOne = true;
            }
        } else {
            animationTwo.update();
        }
    }

    public void draw(Graphics2D g){
        if (animPlayOne){
            setMapPosition();
            g.drawImage(animationTwo.getImage(),145, 130, null);
        }else {
            setMapPosition();
            g.drawImage(animation.getImage(), 145, 130, null);
        }
    }


}
