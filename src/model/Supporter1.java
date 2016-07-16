package model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/16/2016.
 */
public class Supporter1 extends Supporter {
    String fileName = "Resource/Player/image 1068.png";
    public Supporter1(Player player) {
        super(player);
        showTime = 160;
        speed = 3;
        damage = 1;
        try {
            sprite = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
