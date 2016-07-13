import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Player extends GameObject {
    public int type;
    public int exp;
    public int money;
    public int checkGun;
    public int damage;
    BufferedImage sprite1 = ImageIO.read(new File("Resource/Player/Player_TYPE1"));
    BufferedImage sprite2 = ImageIO.read(new File("Resource/Player/Player_TYPE2"));
    BufferedImage sprite3 = ImageIO.read(new File("Resource/Player/Player_TYPE3"));
    public Player() throws IOException {
        sprite = sprite1;

    }
    public void shot(){
        if (type==0){
            damage=20;
        }
        else if (type==1) {
            damage = 40;
        }
        else damage = 60;
    }
    void changeGun(){
        type = checkGun%3;
        if (type==0){
            sprite = sprite1;
        }
        else if (type==1){
            sprite = sprite2;
        }
        else {
            sprite = sprite3;
        }
    }
    void useItem(int item){
        if (item==1){
            //nem luu dan, tat ca mat nua mau
        }
        else{
            if (item==2){
                //thuy loi
            }
            else {
                //support plane xuat hien
            }
        }
    }

}
