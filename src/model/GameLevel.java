package model;

import java.util.ArrayList;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class GameLevel {
    private static int gameLevel=1;
    public void upGameLvl(){
        gameLevel++;
    }
    public int numOfLv1(){
        int num = gameLevel*20 - 100;
        return (num>0?num:20);
    }
    public int numOfLv2(){
        int num = gameLevel*10 - 70;
        return (num>0?num:5);
    }
    public int numOfLv3(){
        int num = gameLevel*10 - numOfLv1() - numOfLv2();
        return (num>0?num:0);
    }
    public int numOfLv4(){
        int num = gameLevel*5 - numOfLv1() - numOfLv2() - numOfLv3();
        return  (num>0?num:0);
    }
}
