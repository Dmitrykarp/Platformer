package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Support on 07.04.2016.
 */
public class HUD {

    private Player player;
    private BufferedImage image;
    private Font font;

    public HUD(Player p){
        player = p;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/Sprites/HUD/hud.gif"));
            font = new Font("Arial", Font.PLAIN, 14);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g){
        g.drawImage(image, 0, 2, null);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(player.getHealth() +"/" + player.getMaxHealth(),32 ,19);
        g.drawString(player.getFire()/200 +"/" +player.getMaxFire()/200, 27, 44);
    }

}
