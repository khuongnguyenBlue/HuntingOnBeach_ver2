package screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/14/2016.
 */
public class GameOverScreen extends Screen implements MouseListener{
    BufferedImage gameoverImage, gameoverIcon;

    public GameOverScreen(){
        try {
            gameoverImage= ImageIO.read(new File("Resource/game_re/image 766.jpg"));
            gameoverIcon= ImageIO.read(new File("Resource/icon/gameover_icon.png"));
            //gameoverIcon=resizeicon(gameoverIcon, 256,256);
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

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(gameoverImage, 0, 0, null);
        g.drawImage(gameoverIcon,100,50,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
