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
    public String fileName;
    public int posX, posY;
    public int healthPoint;
    boolean isAlive = true;
    public GameObject(){
        try {
            sprite = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void draw(Graphics g){
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
    void update(){

    }
    public Rectangle getRectAround(){
        return new Rectangle(posX+sprite.getWidth()*2/10, posY+(sprite.getHeight()*3)/10, sprite.getWidth(), (7*sprite.getHeight())/10);
    }
}
