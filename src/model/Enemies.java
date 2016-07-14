package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Enemies extends GameObject{
    public int damage;
    public int speed = 1+(int)(Math.random()*1);
    public int explosionType;
    public int maxHP;
    int count = 0;
    Player player;
    public Enemies(String fileName, Player player){
        posY = 150+(int)(Math.random()*200);
        this.player = player;
        try {
            sprite = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void checkIfHit(int x, int y){
        if (this.getRectAround().contains(x, y)){
            this.getHit();
        }
    }
    void getHit (){
        if (isAlive){
            healthPoint-=player.damage;
            if (healthPoint<=0) {
                healthPoint = 0;
                this.exploded();
            }
        }
    }
    void exploded(){
        isAlive=false;
        player.money += this.maxHP;
        this.createExplosion();
    }
    Explosion createExplosion(){

        return new Explosion (posX, posY, this.explosionType);
    }

    @Override
    public void update() {
        super.update();
        if (isAlive) {
            posX += speed;
            if (checkIfStop()) {
                shot();
            }
        }
    }

    boolean checkIfStop(){
        if (posX>180){
            speed=0;
            return true;
        }
        return false;
    }
    void shot(){
        //tao hieu ung ban dan
        //tru mau
        count++;
        if (count==50){
            player.healthPoint-=this.damage;
            count=0;
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.white);
        g.drawString(""+healthPoint,posX-5, posY );
        g.setColor(Color.orange);
        g.fillRect(posX+sprite.getWidth()*2/10, posY, healthPoint/6, 2);

    }
}
