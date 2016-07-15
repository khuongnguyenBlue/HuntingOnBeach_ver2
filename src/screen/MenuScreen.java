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
public class MenuScreen extends Screen implements MouseListener{
    BufferedImage menuImage;
    BufferedImage playImage;
    BufferedImage continueImage;
    BufferedImage shopImage;
    BufferedImage aboutImage;
    BufferedImage background;
    Rectangle menuRect, playRect, continueRect, aboutRect;
    GameWindow gameWindow;
    public MenuScreen(GameWindow gameWindow){
        try {
            playImage = ImageIO.read(new File("Resource/icon/play_icon.png"));
           // menuImage = ImageIO.read(new File("Resource/icon/menu_icon.png"));
            aboutImage = ImageIO.read(new File("Resource/icon/about_icon.png"));
            shopImage = ImageIO.read(new File("Resource/icon/shop_icon.png"));
           // continueImage = ImageIO.read(new File("Resource/icon/continue_icon.png"));
            background = ImageIO.read(new File("Resource/game_re/image 634.jpg"));
           //menuRect = new Rectangle((background.getWidth()-menuImage.getWidth())/2, background.getHeight()/2-20,menuImage.getWidth(), menuImage.getHeight());
            playRect = new Rectangle((background.getWidth()-playImage.getWidth())/2, background.getHeight()/2+40,playImage.getWidth(), playImage.getHeight());
            //continueRect = new Rectangle((background.getWidth()-playImage.getWidth())/2, background.getHeight()/2+80,playImage.getWidth(), playImage.getHeight());
            aboutRect = new Rectangle((background.getWidth()-playImage.getWidth())/2, background.getHeight()/2+120,playImage.getWidth(),playImage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gameWindow = gameWindow;
    }

    @Override
    public void update() {
        //if click play push Gameplay Screen

        //if click about push About Screen
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        //g.drawImage(menuImage, (int)menuRect.getX(), (int)menuRect.getY(), null);
        g.drawImage(playImage, (int)playRect.getX(), (int)playRect.getY(), null);
       // g.drawImage(continueImage, (int)continueRect.getX(), (int)continueRect.getY(), null);
        g.drawImage(aboutImage, (int)aboutRect.getX(), (int)aboutRect.getY(), null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (playRect.contains(e.getX(),e.getY())){
            GameplayScreen gameplayScreen = new GameplayScreen();
            gameWindow.addMouseListener(gameplayScreen);
            gameWindow.addMouseMotionListener(gameplayScreen);
            GameManager.getInstance().getStackScreen().push(gameplayScreen);
        }
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
