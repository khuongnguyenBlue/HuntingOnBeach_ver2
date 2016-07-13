import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 13/07/2016.
 */
public class GameWindow extends Frame implements  Runnable{
    BufferedImage background;
    BufferedImage bufferImage;
    BufferedImage blankBackground, loadedItem;
    int mousepressedtime=0;
    int pressingornot=0;
    Player player=new Player();
    Enemies enemy1= new EnemyLvl1(player);
    Enemies enemy2= new EnemyLvl2(player);
    Enemies enemy3= new EnemyLvl3(player);
    Enemies enemy4= new EnemyLvl4(player);

    @Override
    public void run() {
        gameLoop();
    }
    public GameWindow() throws IOException {
        initGame();
    }
    void initGame(){
        this.setTitle("Hunting on beach");
        loadBackground();
        this.setSize(640,480);
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



    void loadBackground(){
        if(blankBackground == null){
            blankBackground = new BufferedImage(1280, 800, 1);
        }
        Graphics bufferBackground=blankBackground.getGraphics();
        try {
            loadedItem=ImageIO.read(new File("Resource/Background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferBackground.drawImage(loadedItem, 0, 0, null);


    }


    void gameUpdate(){
        player.update();
        enemy1.update();
        enemy2.update();
        enemy3.update();
        enemy4.update();
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
            bufferImage = new BufferedImage(640, 480, 1);
        }
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.drawImage(blankBackground, 0, 0, null);

        g.drawImage(bufferImage, 0, 0, null);

    }



}
