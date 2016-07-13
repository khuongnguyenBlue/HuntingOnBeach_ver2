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
    BufferedImage sprite2, sprite3;
    String fileName1 = "Resource/Player/Player_TYPE1.png";
    String fileName2 = "Resource/Player/Player_TYPE2.png";
    String fileName3 = "Resource/Player/Player_TYPE3.png";
    public Player(){
        try {
            sprite = resize(ImageIO.read(new File(fileName1)),2);
            sprite2 = resize(ImageIO.read(new File(fileName2)),2);
            sprite3 = resize(ImageIO.read(new File(fileName3)),2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void shot(){
        System.out.println("shot");
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
            sprite = sprite;
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
