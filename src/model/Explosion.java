package model;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Explosion extends GameObject {
    public int type;

    public Explosion(int x, int y, int type){
        this.type = type;
        posX = x;
        posY = y;
        switch (type){
            case 0:

        }
    }

}
