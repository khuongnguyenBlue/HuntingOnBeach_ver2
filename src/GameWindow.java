import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoangelato on 13/07/2016.
 */
public class GameWindow extends Frame implements  Runnable{
    BufferedImage background;
    BufferedImage bufferImage;
    int mousepressedtime=0;
    int pressingornot=0;
    Player player;


    @Override
    public void run() {
        gameLoop();
    }
    public GameWindow(){
        initGame();
    }
    void initGame(){
        this.setTitle("Hunting on beach");
        loadBackgound();
        this.setSize(1280,800);
        this.setVisible(true);
        try
        {
            this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("Resource/Char/aim_icon.png").getImage(),new Point(0,0),"custom cursor"));

        }catch(Exception e){}


        //cam bien chuot
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                player.posX = e.getX() - 25;
                player.posY = e.getY() - 25;

            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    player.shot();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    mousepressedtime++;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==1) {
                    mousepressedtime=0;
                    pressingornot=0;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }



    void loadBackgound(){

    }


    void gameUpdate(){

        //

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
