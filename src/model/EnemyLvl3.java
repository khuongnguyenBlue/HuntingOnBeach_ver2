package model;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class EnemyLvl3 extends Enemies {
    public static final String FILE_NAME = "Resource/Enemies/EnemyLv3.png";
    public EnemyLvl3(Player player) {
        super(FILE_NAME, player);
        shotPosX=posX+128;
        shotPosY=posY+5;
        maxHP = 400;
        healthPoint = maxHP;
        damage = 30;
    }
}
