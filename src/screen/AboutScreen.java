package screen;

import stuff.GameWindow;

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
public class AboutScreen extends Screen implements MouseListener{
    public int state;
    GameWindow gameWindow;
    BufferedImage background;
    BufferedImage guide1, guide2, guide3, guide4, guide5, guide6, guideBox, guideBoat, guideYou;
    public AboutScreen(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        try {
            background = ImageIO.read(new File("Resource/guide_screen/image 753.jpg"));
            guide1 = ImageIO.read(new File("Resource/guide_screen/Guide_1.png"));
            guide2 = ImageIO.read(new File("Resource/guide_screen/Guide_2.png"));
            guide3 = ImageIO.read(new File("Resource/guide_screen/Guide_3.png"));
            guide4 = ImageIO.read(new File("Resource/guide_screen/Guide_4.png"));
            guide5 = ImageIO.read(new File("Resource/guide_screen/Guide_5.png"));
            guide6 = ImageIO.read(new File("Resource/guide_screen/Guide_6.png"));
            guideBox = ImageIO.read(new File("Resource/guide_screen/guidebox.png"));
            guideBoat = ImageIO.read(new File("Resource/guide_screen/Guide_baovethuyen.png"));
            guideYou = ImageIO.read(new File("Resource/guide_screen/Guide_ban.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(guideBoat, 400, 60, null);
        g.drawImage(guideYou, 180, 300, null);
        g.drawString("Kenny Khuong & Hoangelato", 470, 460);
        switch (state){
            case 0:
                g.drawImage(guide1, 10, 30, null);
                break;
            case 1:
                g.drawImage(guide2, 10, 30, null);
                break;
            case 2:
                g.drawImage(guide3, 10, 30, null);
                break;
            case 3:
                g.drawImage(guide4, 10, 30, null);
                break;
            case 4:
                g.drawImage(guide5, 10, 30, null);
                break;
            case 5:
                g.drawImage(guide6, 10, 30, null);
                break;
            case 6:
                state=0;
                GameManager.getInstance().getStackScreen().pop();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        state++;
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
