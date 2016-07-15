package model;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class EnemyLvl2 extends Enemies {
    public static final String FILE_NAME = "Resource/Enemies/EnemyLv2.png";
    public EnemyLvl2(Player player, int time) {
        super(FILE_NAME, player, time);
        shotPosX=posX+68;
        shotPosY=posY+9;
        maxHP = 150;
        healthPoint = maxHP;
        damage = 20;
    }
}
