package screen;

import model.*;
import stuff.BackgroundBuild;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/14/2016.
 */
public class GameplayScreen extends Screen implements MouseMotionListener, MouseListener{
    BufferedImage background;
    BufferedImage bufferImage;
    BufferedImage yatchImage,shipImage;


    BackgroundBuild drawnBackground= new BackgroundBuild();
    int mousePressedTime=0, countScreen;
    boolean isPressing = false;
    Player player=new Player();
    Enemies enemy1= new EnemyLvl1(player);
    Enemies enemy2= new EnemyLvl2(player);
    Enemies enemy3= new EnemyLvl3(player);
    Enemies enemy4= new EnemyLvl4(player);
    public GameplayScreen(){
        try {
            yatchImage= ImageIO.read(new File("Resource/Background/image 132.png"));
            shipImage=ImageIO.read(new File("Resource/Background/image 1134.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static BufferedImage resizeicon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    @Override
    public void update() {
        drawnBackground.update();

        if (isPressing) {
            player.shot();

            enemy1.checkIfHit(player.posX+25, player.posY+25);
            enemy2.checkIfHit(player.posX+25, player.posY+25);
            enemy3.checkIfHit(player.posX+25, player.posY+25);
            enemy4.checkIfHit(player.posX+25, player.posY+25);
        }
        player.shotAnimation.update();
        player.smokeAnimation.update();
        enemy1.update();
        enemy2.update();
        enemy3.update();
        enemy4.update();
    }

    @Override
    public void draw(Graphics g) {
        if(bufferImage == null){
            bufferImage = new BufferedImage(640, 480, 1);
        }
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.drawImage(bufferImage, 0, 0, null);
        drawnBackground.draw();
        {bufferGraphics.drawImage(drawnBackground.blankBackground.getSubimage(countScreen%640,0,640,480),0,0,null);
            countScreen+=5;}
        bufferGraphics.drawImage(yatchImage,400,200,null);
        bufferGraphics.drawImage(shipImage,0,350,null);
        enemy1.draw(bufferGraphics);
        enemy2.draw(bufferGraphics);
        enemy3.draw(bufferGraphics);
        enemy4.draw(bufferGraphics);
        player.draw(bufferGraphics);
        g.drawImage(bufferImage, 0, 0, null);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            isPressing=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton()==1) {
            mousePressedTime=0;
            isPressing = false;
            player.isShooting=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isPressing) {
            player.posX = e.getX() - 25;
            player.posY = e.getY() - 25;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!isPressing) {
            player.posX = e.getX() - 25;
            player.posY = e.getY() - 25;
        }
    }
}
