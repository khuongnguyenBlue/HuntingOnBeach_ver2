/**
 * Created by Laptop88 on 7/13/2016.
 */
public class EnemyLvl1 extends Enemies {
    public static final String FILE_NAME = "Resource/Enemies/EnemyLv1.png";

    public EnemyLvl1(Player player) {
        super(FILE_NAME, player);
        maxHP = 150;
        healthPoint = maxHP;
        damage = 10;
    }

}
