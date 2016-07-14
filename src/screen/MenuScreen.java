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
public class MenuScreen extends Screen implements MouseListener{
    BufferedImage playImage;
    BufferedImage aboutImage;
    MouseEvent mouseEvent;

    public MenuScreen(){
        try {
            playImage = ImageIO.read(new File(""));
            aboutImage = ImageIO.read(new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        //if click play push Gameplay Screen
        //if click about push About Screen
    }

    @Override
    public void draw(Graphics g) {
        //ve man anh game
        //nut play
        //about
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
