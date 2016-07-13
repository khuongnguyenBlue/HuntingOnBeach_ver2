/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Enemies extends GameObject{
    public int level;
    public int explosionType;
    void checkIfHit(int x, int y, Player player){
        if (this.getRectAround().contains(x, y)){
            this.getHit(player);
        }
    }
    void getHit (Player player){
        if (this.isAlive){
            healthPoint-=player.damage;
            if (healthPoint<=0) {

                this.exploded();
            }
        }
    }
    void exploded(){
        isAlive=false;
        this.createExplosion();
    }
    Explosion createExplosion(){
        return new Explosion (posX, posY, this.explosionType);
    }
}
