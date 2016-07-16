package screen;

import model.Player;
import screen.Screen;
import stuff.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Laptop88 on 7/16/2016.
 */
public class ShopScreen extends Screen implements MouseListener{
    GameWindow gameWindow;
    BufferedImage backGround, shopScreen;
    Rectangle gun1Rect, gun2Rect, gun3Rect;
    Rectangle repairRect, emergenRect;
    Rectangle grenadeRect, netRect, mineRect;
    Rectangle nextRect;
    Player player;
    public int gun2Cost = 3000;
    public int gun3Cost = 6000;
    public int mineCost = 1000;
    public int netCost = 300;
    public int grenadeCost = 2000;

    public ShopScreen(GameWindow gameWindow, Player player){
        this.gameWindow = gameWindow;
        this.player = player;
        try {
            backGround = ImageIO.read(new File("Resource/guide_screen/image 753.jpg"));
            shopScreen = ImageIO.read(new File("Resource/game_re/ShopScreen.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        gun2Rect = new Rectangle(94+320, 229, 79, 73);
        gun3Rect = new Rectangle(175+320, 229, 79, 73);
        repairRect = new Rectangle(13+320, 5, 71, 93);
        emergenRect = new Rectangle(86+320, 5, 71, 93);
        grenadeRect = new Rectangle(204+320, 323, 71, 93);
        netRect = new Rectangle(159+320, 5, 71, 93);
        mineRect = new Rectangle(235+320, 5, 71, 93);
        nextRect = new Rectangle(175+320, 449, 130, 23);



    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (gun2Rect.contains(e.getX(),e.getY()) && player.money){

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

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        g.drawImage(shopScreen, 320, 0, null);
    }
}
