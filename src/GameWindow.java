import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoangelato on 13/07/2016.
 */
public class GameWindow extends Frame implements  Runnable{
    BufferedImage background;
    @Override
    public void run() {

    }
    public GameWindow(){
        initGame();
    }
    void initGame(){
        this.setTitle("Hunting on beach");
        loadGame();
        this.setSize(background.getWidth()*2, background.getHeight()*2);

    }
    void loadGame(){
        loadImage();
        InitCursor();

    }
    void loadImage(){

    }
    void InitCursor(){
        this.setCursor(this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
    }


}
