import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class GameObject {
    BufferedImage sprite;
    public int posX, posY;
    public int healthPoint;
    boolean isAlive = true;
    void draw(Graphics g){
        g.drawImage(sprite, posX, posY, null);
    }
    void update(){

    }
    public Rectangle getRectAround(){
        return new Rectangle(posX+sprite.getWidth()*2/10, posY+(sprite.getHeight()*3)/10, sprite.getWidth(), (7*sprite.getHeight())/10);
    }
}
