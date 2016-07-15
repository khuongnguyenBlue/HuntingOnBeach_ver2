package model;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class EnemyLvl1 extends Enemies {
    public static final String FILE_NAME = "Resource/Enemies/EnemyLv1.png";

    public EnemyLvl1(Player player, int time) {
        super(FILE_NAME, player, time);
        shotPosX=posX+77;
        shotPosY=posY+9;
        maxHP = 100;
        healthPoint = maxHP;
        damage = 10;
    }

}
