package stuff;

import model.*;
import screen.GameManager;
import screen.GameplayScreen;
import screen.MenuScreen;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Hoangelato on 13/07/2016.
 */
public class GameWindow extends Frame implements Runnable{
    BufferedImage bufferedImage;
    BufferedImage mouseIcon;
    @Override
    public void run() {
        gameLoop();
    }
    public GameWindow() throws IOException {
        initGame();
    }
    void initCursor(){
        this.setCursor(this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
    }

    void initGame(){
        this.setTitle("Hunting on beach");
        this.setSize(640,480);
        this.setVisible(true);
        initCursor();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        MenuScreen menuScreen = new MenuScreen(this);
        this.addMouseListener(menuScreen);
        GameManager.getInstance().getStackScreen().push(menuScreen);
        music();
    }

    public static void music()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            BGM = new AudioStream(new FileInputStream("Resource/Sound/sound_18.wav"));
            AudioPlayer.player.start(BGM);
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error){error.printStackTrace();}

        MGP.start(loop);
    }


    public void drawCursor(Graphics g,int posX, int posY) {
        try {
            mouseIcon = ImageIO.read(new File("Resource/Char/aim_icon.png"));
            mouseIcon = resizeicon(mouseIcon, 50, 50);
        } catch (Exception e) {
        }
        g.drawImage(mouseIcon, posX, posY, null);
    }



    public static BufferedImage resizeicon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    void gameUpdate(){
        GameManager.getInstance().getStackScreen().peek().update();


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

        }
    }

    @Override
    public void update(Graphics g) {



        if(bufferedImage == null){
            bufferedImage = new BufferedImage(640, 480, 1);
        }
        Graphics bufferGraphics = bufferedImage.getGraphics();
        GameManager.getInstance().getStackScreen().peek().draw(bufferGraphics);

        PointerInfo pInfo = MouseInfo.getPointerInfo();
        drawCursor(bufferGraphics,pInfo.getLocation().x-25,pInfo.getLocation().y-25);
        g.drawImage(bufferedImage, 0, 0, null);
    }




}
