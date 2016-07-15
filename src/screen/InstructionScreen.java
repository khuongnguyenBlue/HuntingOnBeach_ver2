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
 * Created by Hoangelato on 16/07/2016.
 */
public class InstructionScreen extends Screen implements MouseListener{
    GameWindow gameWindow;
    BufferedImage background;
    BufferedImage instruction1, instruction2;
    BufferedImage guideBox;
    public InstructionScreen(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        try {
            background = ImageIO.read(new File("Resource/guide_screen/image 753.jpg"));
            instruction1 = ImageIO.read(new File("Resource/guide_screen/baovethuyen_arrowbox.png"));
            instruction2 = ImageIO.read(new File("Resource/guide_screen/daylaban_arrowbox.png"));
            guideBox = ImageIO.read(new File("Resource/guide_screen/guidebox.png"));
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
        g.drawImage(instruction1, 400, 70, null);
        g.drawImage(instruction2, 150, 300, null);
        g.drawImage(guideBox, 100, 100, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameplayScreen gameplayScreen = new GameplayScreen(gameWindow);
        gameWindow.addMouseListener(gameplayScreen);
        gameWindow.addMouseMotionListener(gameplayScreen);
        gameWindow.addKeyListener(gameplayScreen);
        GameManager.getInstance().getStackScreen().push(gameplayScreen);
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
