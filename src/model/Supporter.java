package model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoangelato on 16/07/2016.
 */
public class Supporter extends GameObject {
    public int showTime;
    Player player;
    public Supporter(Player player){
        this.player = player;
        posX = 640;
        posY = 50;
    }

    @Override
    public void update() {
        super.update();
        if(player.usingSupporter1Time<showTime){
            posX-=speed;
        }
        else{
            posX+=speed;
        }

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (posX<640){
            g.drawImage(sprite, posX, posY, null);
        }
    }
}
