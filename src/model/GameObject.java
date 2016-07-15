package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class GameObject {
    BufferedImage sprite;

    public int posX, posY;
    public int healthPoint;
    public boolean isAlive = true;

    public void draw(Graphics g){
        g.drawImage(sprite, posX, posY, null);

    }
    public BufferedImage resize (BufferedImage sbi, int n) {

        if (sbi != null) {
            int w = sbi.getWidth();
            int h = sbi.getHeight();
            Image tmp = sbi.getScaledInstance(w / n, h / n, Image.SCALE_SMOOTH);
            BufferedImage dbi = new BufferedImage(w / n, h / n, BufferedImage.TYPE_INT_ARGB);
            dbi.getGraphics().drawImage(tmp, 0, 0, null);
            return dbi;
        }
        return null;
    }
    public static BufferedImage resizeicon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public void update(){

    }
    public Rectangle getRectAround(){
        return new Rectangle(posX+2*sprite.getWidth()/10, posY+sprite.getHeight()*2/10, sprite.getWidth()*7/10, sprite.getHeight()*5/10);
    }
}
