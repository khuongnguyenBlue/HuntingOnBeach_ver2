package model;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class EnemyLvl4 extends Enemies{
    public static final String FILE_NAME = "Resource/Enemies/EnemyLv4.png";

    public EnemyLvl4(Player player) {
        super(FILE_NAME, player);
        shotPosX=posX+121;
        shotPosY=posY+6;
        maxHP = 600;
        healthPoint = maxHP;
        damage = 50;
    }
    boolean checkIfStop() {
        if (posX > 120) {
            speed = 0;
            return true;
        }
        return false;
    }
}
