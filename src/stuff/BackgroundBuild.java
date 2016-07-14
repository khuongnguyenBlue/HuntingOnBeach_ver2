package stuff;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 14/07/2016.
 */
public class BackgroundBuild {
    BufferedImage blankBackground;
    BufferedImage skyBackground, cityBackground, islandBackground, waterBackground, ship;

    Animation explosion;
    public BackgroundBuild(){
        if(blankBackground == null){
            blankBackground = new BufferedImage(1280, 480, 1);
        }
        try {
            skyBackground = ImageIO.read(new File("Resource/Background/image 1085.jpg"));
            cityBackground= ImageIO.read(new File("Resource/Background/image 1108.png"));
            islandBackground=ImageIO.read(new File("Resource/Background/image 1123.png"));
            waterBackground= ImageIO.read(new File("Resource/Background/image 1088.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        explosion = new Animation("Resource/char/exploding (",110, 110,31,9);
    }
    public void update(){
        explosion.update();
    }

    public void draw(){
        blankBackground.getGraphics().drawImage(waterBackground, 0, 100, null);
        blankBackground.getGraphics().drawImage(waterBackground, 320, 100, null);
        blankBackground.getGraphics().drawImage(waterBackground, 640, 100, null);
        blankBackground.getGraphics().drawImage(waterBackground, 960, 100, null);
        blankBackground.getGraphics().drawImage(skyBackground, 0, 0, null);
        blankBackground.getGraphics().drawImage(skyBackground,640,0,null);
        blankBackground.getGraphics().drawImage(cityBackground, 0, 70,null);
        blankBackground.getGraphics().drawImage(cityBackground, 640, 70,null);
        explosion.draw(blankBackground.getGraphics(),100,100);

    };

}
