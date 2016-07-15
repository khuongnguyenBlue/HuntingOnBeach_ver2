package model;

/**
 * Created by Laptop88 on 7/15/2016.
 */
public class EnemyFactory {
    Player player;
    public int time;
    public EnemyFactory(Player player){
        this.player = player;
    }
    public Enemies getEnemy (int lvl){
        time = (int)(Math.random()*2000);
        if (lvl==1){
            return new EnemyLvl1(player, time);
        }
        if (lvl==2){
            return new EnemyLvl2(player, time);
        }
        if (lvl==3){
            return new EnemyLvl3(player, time);
        }
        return new EnemyLvl4(player, time);
    }
}
