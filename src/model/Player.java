package model;

import stuff.Animation;
import stuff.GrenadeUsingAnimation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Player extends GameObject {
    public int type = 0;
    public int itemType;
    public int exp;
    public int money;
    public int checkGun;
    public int damage;
    public boolean isUsingItem=false;
    public int usingItemTime=0;
    public int spriteUsingItemPosX, spriteUsingItemPosY;
    public int grenadeIconPosX, grenadeIconPosY;
    public Animation shotAnimation,smokeAnimation;
    public GrenadeUsingAnimation GrenadeUsingAnimation, GrenadeAnimation;
    BufferedImage sprite1, sprite2, sprite3, spriteUsingGrenade, iconGrenade;
    String fileName1 = "Resource/Player/Player_TYPE1.png";
    String fileName2 = "Resource/Player/Player_TYPE2.png";
    String fileName3 = "Resource/Player/Player_TYPE3.png";
    public Player(){
        try {
            sprite1 = resize(ImageIO.read(new File(fileName1)),2);
            sprite = sprite1;
            sprite2 = resize(ImageIO.read(new File(fileName2)),2);
            sprite3 = resize(ImageIO.read(new File(fileName3)),2);
            spriteUsingGrenade= resize(ImageIO.read(new File("Resource/Player/Player_HAND.png")),2);
            iconGrenade = ImageIO.read(new File("Resource/char/image 547.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shotAnimation= new Animation("Resource/char/shot (",31,2);
        smokeAnimation=new Animation("Resource/char/smoke_shot (",31,5);
        GrenadeUsingAnimation= new GrenadeUsingAnimation(spriteUsingGrenade,31,300,350,5,5);
        GrenadeAnimation=new GrenadeUsingAnimation(iconGrenade,31,300,400,0,20);
        healthPoint = 2000;
//        spriteUsingItemPosX =300; spriteUsingItemPosY=400;
//        grenadeIconPosX=300; grenadeIconPosY=350;


    }
    public boolean isShooting=false;
    public void shot(){
        //System.out.println("shot");
        isShooting=true;
        if (type==0){
            damage=3;
        }
        else if (type==1) {
            damage = 5;
        }
        else damage = 8;

    }
    void changeGun(){
        type = checkGun%3;
        if (type==0){
            sprite = sprite1;
        }
        else if (type==1){
            sprite = sprite2;
        }
        else {
            sprite = sprite3;
        }
    }
    public void useItem(char c){
        switch (c){
            case 'z':
                isUsingItem=true;
                itemType = 1;
                break;
            case 'x':
                isUsingItem=true;
                itemType = 2;
                break;
            case 'c':
                isUsingItem=true;
                itemType = 3;
                break;
            default:
                break;

        }
    }
    @Override
    public void draw(Graphics g) {
        if (usingItemTime==0) {
            System.out.println(usingItemTime);
            if (isShooting) {
                shotAnimation.draw(g, posX - sprite.getWidth() * 3 / 11 + 210, posY + sprite.getHeight() * 2 / 10);
                smokeAnimation.draw(g, posX + 10, posY + 30);
            }
            g.drawImage(sprite, posX - sprite.getWidth() * 3 / 11, posY + sprite.getHeight() * 2 / 10, null);
        }else {
            System.out.println(usingItemTime);
            GrenadeUsingAnimation.draw(g);
            GrenadeAnimation.draw(g);
        }
        g.setColor(Color.green);
        g.drawString("$ "+money, 10, 420);
        g.drawString("HP: "+healthPoint, 10, 450);
        g.setColor(Color.white);
    }

    @Override
    public void update() {
        super.update();

//        if (!isUsingItem) {
//            spriteUsingItemPosX = 300;
//            spriteUsingItemPosY = 400;
//            grenadeIconPosX = 300;
//            grenadeIconPosY = 350;
//        }
//        if (usingItemTime == 0) {
//            isUsingItem = false;
//            spriteUsingItemPosX = 300;
//            spriteUsingItemPosY = 400;
//            grenadeIconPosX = 300;
//            grenadeIconPosY = 350;
//        }
//        spriteUsingItemPosX-=5*usingItemTime;
//        spriteUsingItemPosY-=5*usingItemTime;
//        grenadeIconPosY-=10*usingItemTime;
        if (healthPoint<=0){
            healthPoint=0;
            isAlive = false;
        }
    }
}
