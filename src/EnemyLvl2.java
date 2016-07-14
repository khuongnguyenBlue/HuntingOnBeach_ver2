/**
 * Created by Laptop88 on 7/13/2016.
 */
public class EnemyLvl2 extends Enemies {
    public static final String FILE_NAME = "Resource/Enemies/EnemyLv2.png";
    public EnemyLvl2(Player player) {
        super(FILE_NAME, player);
        maxHP = 120;
        healthPoint = maxHP;
        damage = 20;
    }
}
