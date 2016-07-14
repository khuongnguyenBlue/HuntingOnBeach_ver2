import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 14/07/2016.
 */
public class Background {
    BufferedImage blankBackground, loadedItem;
    Animation animation;
    public Background(){
        if(blankBackground == null){
            blankBackground = new BufferedImage(1280, 480, 1);
        }
        animation = new Animation("Resource/cat.png",512, 256,31);
    }
    public void update(){
        animation.update();
    }

    public void draw(){

//        try {
//            loadedItem= ImageIO.read(new File("Resource/Background/background.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        g.drawImage(loadedItem, 0, 0, null);


        try {
            loadedItem=ImageIO.read(new File("Resource/Background/image 1088.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blankBackground.getGraphics().drawImage(loadedItem, 0, 100, null);
        blankBackground.getGraphics().drawImage(loadedItem, 320, 100, null);
        blankBackground.getGraphics().drawImage(loadedItem, 640, 100, null);
        blankBackground.getGraphics().drawImage(loadedItem, 960, 100, null);

        try {
            loadedItem=ImageIO.read(new File("Resource/Background/image 1085.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blankBackground.getGraphics().drawImage(loadedItem, 0, 0, null);
        blankBackground.getGraphics().drawImage(loadedItem,640,0,null);

//        try {
//            loadedItem=ImageIO.read(new File("Resource/Background/image 132.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        blankBackground.getGraphics().drawImage(loadedItem, 420, 200, null);

//        try {
//            loadedItem=ImageIO.read(new File("Resource/Background/image 1096.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        g.drawImage(loadedItem, 0, 106, null);

        //animation.draw(g, 100, 100);



    };





}
