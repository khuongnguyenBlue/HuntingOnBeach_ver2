package stuff;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hoangelato on 16/07/2016.
 */
public class GrenadeUsingAnimation {
    private ArrayList<BufferedImage> frames = new ArrayList<>();
    private int index = 0;
    private int countTime = 0;
    private int time;
    public int posX, posY, speedX, speedY;
    public GrenadeUsingAnimation(BufferedImage sprite,int time, int posX, int posY, int speedX, int speedY){
        this.time = time;
        this.posX = posX;
        this.posY = posY;
        this.speedX=speedX;
        this.speedY=speedY;

        for (int i = 1; i < 24; i++){
                frames.add(sprite);
            }


    }

    public void update(){
        countTime += 17;
        if (countTime > this.time) {
            countTime = 0;
            if (index == frames.size() - 1) {
                index = 0;
            } else {
                index++;
            }
        }
        posX-=speedX;
        posY-=speedY;
    }
    public void draw(Graphics g){
        g.drawImage(frames.get(index), posX, posY,null);
    }


}
