package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Laptop88 on 7/16/2016.
 */
public class Mine extends GameObject{
    public Mine(BufferedImage sprite,int posX, int posY){
        this.sprite=sprite;
        this.posX=posX;
        this.posY=posY;
    }
}
