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
    BufferedImage playerType1, playerType2, playerType3;
    BufferedImage playerType;
    Rectangle gun1Rect, gun2Rect, gun3Rect;
    Rectangle repairRect, emergenRect;
    Rectangle grenadeRect, netRect, mineRect;
    Rectangle nextRect;
    Player player;
    public int gun2Cost = 1000;
    public int gun3Cost = 3400;
    public int repairCost = 500;
    public int emergentCost = 150;
    public int mineCost = 400;
    public int netCost = 200;
    public int grenadeCost = 800;
    public boolean nextRound=false;

    public ShopScreen(GameWindow gameWindow, Player player){
        this.gameWindow = gameWindow;
        this.player = player;
        try {
            shopScreen = ImageIO.read(new File("Resource/game_re/ShopScreen.png"));
            playerType1 = resizeIcon(ImageIO.read(new File("Resource/Player/Player_TYPE1.png")), 148, 121);
            playerType2 = resizeIcon(ImageIO.read(new File("Resource/Player/Player_TYPE2.png")), 148, 121);
            playerType3 = resizeIcon(ImageIO.read(new File("Resource/Player/Player_TYPE3.png")), 148, 121);


        } catch (IOException e) {
            e.printStackTrace();
        }
        gun2Rect = new Rectangle(414, 241, 78, 67);
        gun3Rect = new Rectangle(493, 241, 78, 67);
        repairRect = new Rectangle(337, 35, 69, 70);
        emergenRect = new Rectangle(407, 35, 69, 70);
        grenadeRect = new Rectangle(521, 328, 69, 70);
        netRect = new Rectangle(477, 35, 69, 70);
        mineRect = new Rectangle(50, 35, 69, 70);
        nextRect = new Rectangle(492, 444, 128, 22);



    }
    public static BufferedImage resizeIcon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            if (gun2Rect.contains(e.getX(), e.getY()) && player.money >= gun2Cost) {
                player.money -= gun2Cost;
                player.type = 2;
            }
            if (gun3Rect.contains(e.getX(), e.getY()) && player.money >= gun3Cost) {
                player.money -= gun3Cost;
                player.type = 3;
            }
            if (repairRect.contains(e.getX(), e.getY()) && player.money >= repairCost) {
                player.money -= repairCost;
                player.healthPoint += repairCost;
            }
            if (emergenRect.contains(e.getX(), e.getY()) && player.money >= emergentCost) {
                player.money -= emergentCost;
                player.maxHP += emergentCost;
            }
            if (netRect.contains(e.getX(), e.getY()) && player.money >= netCost) {
                player.money -= netCost;
                player.numOfNet++;
            }
            if (mineRect.contains(e.getX(), e.getY()) && player.money >= mineCost) {
                player.money -= mineCost;
                player.numOfMine++;
            }
            if (grenadeRect.contains(e.getX(), e.getY()) && player.money >= grenadeCost) {
                player.money -= grenadeCost;
                player.numOfGrenade++;
            }
            if (nextRect.contains(e.getX(), e.getY())) {
                GameManager.getInstance().getStackScreen().pop();
            }

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
        g.drawImage(shopScreen, 8, 30, null);
        switch (player.type){
            case 0:
                g.drawImage(playerType1, 350, 340, null);
                break;
            case 1:
                g.drawImage(playerType2, 350, 340, null);
                break;
            case 2:
                g.drawImage(playerType3, 350, 340, null);
        }
        g.setColor(Color.green);
        g.drawString(""+player.money, 150, 260);
        g.drawString(""+player.healthPoint, 150, 280);
        g.drawString(""+player.exp, 150, 325);
        g.setColor(Color.white);
    }
}
