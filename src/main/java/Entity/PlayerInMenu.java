package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Support on 03.04.2016.
 */
public class PlayerInMenu extends MapObject {

    // animations
    private BufferedImage[] sprites;
    private final int numFrames = 4;



    public PlayerInMenu(TileMap tm) {
        super(tm);

        width = 38;
        height = 32;

        //load sprites
        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Sprites/Player/playerInBackground.gif"
                    )
            );

            sprites = new BufferedImage[4];
            for (int i = 0; i<sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(200);

    }

    public void update(){
        setPosition(xtemp, ytemp);

        // animation
        animation.update();
    }

    public void draw(Graphics2D g){
        setMapPosition();
        g.drawImage(animation.getImage(),52, 182 - 30, null);
    }


}
