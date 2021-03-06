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
    public int numOfNet = 2;
    public int numOfMine = 1;
    public int numOfGrenade = 1;
    public int maxArmor;
    public int armor;
    public int exp;
    public int money;
    public int checkGun;
    public int damage;
    public boolean isUsingItem=false;
    public int usingGrenadeTime=0, usingNetTime=0,usingSupporter1Time=0, usingSupporter2Time=0;
    public Animation shotAnimation,smokeAnimation;
    public GrenadeUsingAnimation GrenadeUsingAnimation, GrenadeAnimation;
    public GrenadeUsingAnimation NetAnimation;
    BufferedImage sprite1, sprite2, sprite3;
    BufferedImage spriteUsingGrenade, iconGrenade;
    BufferedImage iconNet;
    String fileName1 = "Resource/Player/Player_TYPE1.png";
    String fileName2 = "Resource/Player/Player_TYPE2.png";
    String fileName3 = "Resource/Player/Player_TYPE3.png";
    public Player(){
        try {
            sprite1 = resize(ImageIO.read(new File(fileName1)),2);
            sprite = sprite1;
            sprite2 = resize(ImageIO.read(new File(fileName2)),2);
            sprite3 = resize(ImageIO.read(new File(fileName3)),1);
            spriteUsingGrenade= resize(ImageIO.read(new File("Resource/Player/Player_HAND.png")),2);
            iconGrenade = ImageIO.read(new File("Resource/char/image 547.png"));
            iconNet = resizeicon(ImageIO.read(new File("Resource/char/image 956.png")),200,200);

        } catch (IOException e) {
            e.printStackTrace();
        }
        shotAnimation= new Animation("Resource/char/shot (",31,2);
        smokeAnimation=new Animation("Resource/char/smoke_shot (",31,5);
        GrenadeUsingAnimation= new GrenadeUsingAnimation(spriteUsingGrenade,31,300,350,5,5);
        GrenadeAnimation=new GrenadeUsingAnimation(iconGrenade,31,300,400,0,20);
        NetAnimation=new GrenadeUsingAnimation(iconNet,31,200,150,10,0);
        maxHP = 2000;

        healthPoint = maxHP;
//        spriteUsingItemPosX =300; spriteUsingItemPosY=400;
//        grenadeIconPosX=300; grenadeIconPosY=350;


        healthPoint = 2000;

    }

    public static BufferedImage resizeicon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public boolean isShooting=false;
    public void shot(){
        isShooting=true;
        if (type==0){
            damage=3;
            //maxArmor = 10;

        }
        else if (type==1) {
            damage = 5;
            //maxArmor = 20;

        }
        else {
            damage = 8;
            //maxArmor = 30;

        }

    }
    void changeGun(){
        type = checkGun%3;

    }
    public void useItem(char c){
        switch (c){
            case 'z':
                if (numOfGrenade>0){
                    isUsingItem=true;
                    itemType = 1;
                    numOfGrenade--;
                }
                break;
            case 'x':
                if (numOfNet>0){
                    isUsingItem=true;
                    itemType = 2;
                    numOfNet--;
                }

                break;
            case 'c':
                if (numOfMine>0){
                    isUsingItem=true;
                    itemType = 3;
                    numOfMine--;
                }

                break;
            case '1':
                isUsingItem=true;
                itemType = 4;
                break;
            case '2':
                isUsingItem=true;
                itemType = 5;
                break;
            case '3':
                isUsingItem=true;
                itemType = 6;
                break;
            default:
                break;

        }
    }
    @Override
    public void draw(Graphics g) {
        if (usingGrenadeTime==0) {

            if (isShooting) {
                shotAnimation.draw(g, posX - sprite.getWidth() * 3 / 11 + 210, posY + sprite.getHeight() * 2 / 10);
                smokeAnimation.draw(g, posX + 10, posY + 30);
            }
            if (type==0){
                sprite = sprite1;
            }
            else if (type==1){
                sprite = sprite2;
            }
            else {
                sprite = sprite3;
            }
            g.drawImage(sprite, posX - sprite.getWidth() * 3 / 11, posY + sprite.getHeight() * 2 / 10, null);

            switch (itemType) {
                case 2:
                    if (usingNetTime>0) NetAnimation.draw(g);
                    break;

                default:
                    break;
            }

        }else {

            GrenadeUsingAnimation.draw(g);
            GrenadeAnimation.draw(g);
        }
        g.setColor(Color.green);
        g.drawString(""+money, 50, 380);
        g.drawString("HP: "+healthPoint+"/"+maxHP, 50, 430);
        g.drawString(numOfGrenade+"", 592, 392);
        g.drawString(numOfNet+"", 592, 425);
        g.drawString(numOfMine+"", 592, 453);
        g.setColor(Color.white);
    }

    @Override
    public void update() {
        super.update();
        if (healthPoint<=0){
            healthPoint=0;
            isAlive = false;

        }
    }
}
