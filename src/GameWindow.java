import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoangelato on 13/07/2016.
 */
public class GameWindow extends Frame implements  Runnable{
    BufferedImage background;
    BufferedImage bufferImage;

    @Override
    public void run() {
        gameLoop();
    }
    public GameWindow(){
        initGame();
    }
    void initGame(){
        this.setTitle("Hunting on beach");
        loadGame();
        this.setSize(1280, 800);
    }
    void loadGame(){
        loadImage();
        initCursor();

    }
    void loadImage(){

    }
    void initCursor(){
        this.setCursor(
            this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
    }

    void gameUpdate(){

    }

    void gameLoop(){
        while (true) {
            try {
                gameUpdate();
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }
    @Override
    public void update(Graphics g) {
        if(bufferImage == null){
            bufferImage = new BufferedImage(1280, 800, 1);
        }
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.drawImage(background, 0, 0, null);
        g.drawImage(bufferImage, 0, 0, null);

    }



}
