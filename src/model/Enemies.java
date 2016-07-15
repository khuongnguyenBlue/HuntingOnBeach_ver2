package model;

import stuff.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Enemies extends GameObject {
    public int damage;
    public int speed = 1 + (int) (Math.random() * 1);
    public int explosionType;
    public int maxHP;
    int count = 0;
    int explosiontime = 0;
    Player player;
    Animation explosionAnimation,shotAnimation;
    protected int shotPosX, shotPosY;
    public Enemies(String fileName, Player player) {
        posY = 150 + (int) (Math.random() * 200);
        this.player = player;
        try {
            sprite = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        explosionAnimation = new Animation("Resource/char/exploding (", 31, 9);
        shotAnimation =new Animation("Resource/char/enemy_shot (",31,3);
    }

    public void checkIfHit(int x, int y) {
        if (this.getRectAround().contains(x, y)) {
            this.getHit();
        }
    }

    void getHit() {
        if (isAlive) {
            healthPoint -= player.damage;
            if (healthPoint <= 0) {
                healthPoint = 0;
                isAlive = false;
                player.money += this.maxHP;
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if (isAlive) {
            posX += speed;
            shotPosX+= speed;
            if (checkIfStop()) {
                shot();
            }
        } else explosiontime++;
        explosionAnimation.update();
        shotAnimation.update();
    }

    boolean checkIfStop() {
        if (posX > 180) {
            speed = 0;
            return true;
        }
        return false;
    }

    void shot() {
        //tao hieu ung ban dan
        //tru mau
        count++;
        if (count == 50) {
            player.healthPoint -= this.damage;
            count = 0;
        }
    }


    public void draw(Graphics g,boolean isAlive) {
        if(isAlive) {
            g.drawImage(sprite, posX, posY, null);
            shotAnimation.draw(g, shotPosX, shotPosY);
        }
        else {
            if (explosiontime <= 30)
                explosionAnimation.draw(g, posX + sprite.getWidth() / 2, posY - 30);
        }
        if (explosiontime==0){
            g.setColor(Color.white);
            g.drawString("" + healthPoint, posX - 5, posY);
            g.setColor(Color.orange);
            g.fillRect(posX + sprite.getWidth() * 2 / 10, posY, healthPoint / 6, 2);
        }
    }
}

