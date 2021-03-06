package screen;

import model.*;
import stuff.Animation;
import stuff.BackgroundBuild;
import stuff.GameWindow;
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
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Laptop88 on 7/14/2016.
 */
public class GameplayScreen extends Screen implements MouseMotionListener, MouseListener, KeyListener{
    BufferedImage background;
    BufferedImage bufferImage;
    BufferedImage yatchImage,shipImage;
    BufferedImage mouseIcon,iconMine;
    BufferedImage C_icon,X_icon,Z_icon, QuantityBoard_icon, MoneyBoard_icon;
    BufferedImage spriteSupporter1,spriteSupporter2;
    Animation shotAnimation;
    BackgroundBuild drawnBackground= new BackgroundBuild();
    int mousePressedTime=0, countScreen;
    boolean isPressing = false;
    public static Player player=new Player();
    EnemyFactory enemyFactory = new EnemyFactory(player);
    public int numOfDeath;
    public int wonTimeCount;
    BufferedImage health_and_armor;
    ArrayList<Enemies>  enemiesList;
    ArrayList<Mine> minesList;
    GameWindow gameWindow;
    GameLevel gameLevel = new GameLevel();

    public GameplayScreen(GameWindow gameWindow){
        try {
            yatchImage= ImageIO.read(new File("Resource/Background/image 132.png"));
            shipImage= ImageIO.read(new File("Resource/Background/image 1134.png"));
            iconMine = ImageIO.read(new File("Resource/char/image 36.png"));
            C_icon = ImageIO.read(new File("Resource/char/C_icon.png"));
            X_icon = ImageIO.read(new File("Resource/char/X_icon.png"));
            Z_icon = ImageIO.read(new File("Resource/char/Z_icon.png"));
            QuantityBoard_icon = ImageIO.read(new File("Resource/char/QuantityBoard_icon.png"));
            MoneyBoard_icon = ImageIO.read(new File("Resource/char/MoneyBoard_icon.png"));
            spriteSupporter1= ImageIO.read(new File("Resource/Player/image 1068.png"));
            spriteSupporter2= resize(ImageIO.read(new File("Resource/Player/image 1074.png")),2);

            health_and_armor = ImageIO.read(new File("Resource/char/Health_and_ammo_board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shotAnimation= new Animation("Resource/char/shot (",31,2);
        this.gameWindow = gameWindow;
        enemiesList = initEnemies(enemyFactory, new ArrayList<Enemies>());
        minesList = new ArrayList<>();


    }
    ArrayList<Enemies> initEnemies (EnemyFactory enemyFactory, ArrayList<Enemies> enemiesList){
        for (int i=0; i<gameLevel.numOfLv1(); i++){
            enemiesList.add(enemyFactory.getEnemy(1));
        }
        for (int i=0; i<gameLevel.numOfLv2(); i++){
            enemiesList.add(enemyFactory.getEnemy(2));
        }
        for (int i=0; i<gameLevel.numOfLv3(); i++){
            enemiesList.add(enemyFactory.getEnemy(3));
        }
        for (int i=0; i<gameLevel.numOfLv4(); i++){
            enemiesList.add(enemyFactory.getEnemy(4));
        }
        return enemiesList;
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
        if (numOfDeath==enemiesList.size()){

            ShopScreen shopScreen = new ShopScreen(gameWindow, player);
            gameWindow.addMouseListener(shopScreen);
            GameManager.getInstance().getStackScreen().push(shopScreen);
            gameLevel.upGameLvl();
            enemiesList = initEnemies(enemyFactory, new ArrayList<Enemies>());
            minesList = new ArrayList<Mine>();
        }
        else {
            //System.out.println(numOfDeath);
            numOfDeath = 0;
            drawnBackground.update();
            player.shotAnimation.update();
            player.smokeAnimation.update();
            if (isPressing) {
                player.shot();
                player.armor--;

                for (Enemies e : enemiesList) {
                    e.checkIfHit(player.posX + 25, player.posY + 25);
                }


            }

            if (player.isUsingItem) {
                switch (player.itemType) {
                    case 1:
                        player.usingGrenadeTime++;
                        player.GrenadeUsingAnimation.update();
                        player.GrenadeAnimation.update();
                        if (player.usingGrenadeTime == 10) {
                            for (Enemies e : enemiesList) {
                                if (e.isGoing && e.isAlive) {
                                    e.isAlive = false;
                                    player.money += e.maxHP;
                                    e.healthPoint = 0;

                                }
                            }
                        }
                        if (player.usingGrenadeTime > 10) {
                            player.usingGrenadeTime = 0;
                            player.isUsingItem=false;
                            player.GrenadeUsingAnimation.posX = 300;
                            player.GrenadeUsingAnimation.posY = 400;
                            player.GrenadeAnimation.posX = 300;
                            player.GrenadeAnimation.posY = 350;
                        }
                        break;
                    case 2:
                        player.usingNetTime++;
                        if (player.NetAnimation.posX >= 50) {
//                        player.NetAnimation.speedX = 0;
                            player.NetAnimation.update();
                        }
                        if (player.usingNetTime == 10) {

                            for (Enemies e : enemiesList) {

                                e.speed = -1;

                            }

                        }
                        if (player.usingNetTime > 50) {
                            player.usingNetTime = 0;
                            player.isUsingItem = false;
                            for (Enemies e : enemiesList) {

                                e.speed = 1 + (int) (Math.random() * 1);

                            }
                            player.NetAnimation.posX = 200;
                            player.NetAnimation.posY = 150;

                        }
                        break;
                    case 3:
                        if (minesList.size() < 6) {
                            minesList.add(new Mine(iconMine, 120 + (int) (Math.random() * 100), 150 + (int) (Math.random() * 200)));
                        }
                        for (Mine m : minesList) {
                            for (Enemies e : enemiesList) {
                                if ((new Rectangle(e.posX, e.posY, e.sprite.getWidth(), e.sprite.getHeight()).contains(m.posX, m.posY + m.sprite.getHeight() / 2) && m.isAlive)) {

                                    e.isAlive = false;
                                    m.isAlive = false;
                                }
                            }
                        }
                        break;
                    case 4:
                        player.usingSupporter1Time++;
                        Supporter1 supporter1 = new Supporter1(player);
                        supporter1.update();
                        if(player.usingSupporter1Time>0){
                            for (Enemies e:enemiesList) {
                                if (e.isAlive && e.isGoing) {
                                e.healthPoint -= supporter1.damage;
                                    if (e.healthPoint < 0) {
                                    e.healthPoint = 0;
                                    e.isAlive = false;
                                    }
                                }
                            }
                        }
                        if(player.usingSupporter1Time>120) {
                            player.usingSupporter1Time=0;
                            player.isUsingItem=false;
                        }

                        break;
                    case 5:
                        player.usingSupporter2Time++;
                        Supporter2 supporter2 = new Supporter2(player);
                        supporter2.update();
                        if(player.usingSupporter2Time>0){
                            for (Enemies e:enemiesList) {
                                if (e.isAlive && e.isGoing) {
                                    e.healthPoint -= supporter2.damage;
                                    if (e.healthPoint < 0) {
                                        e.healthPoint = 0;
                                        e.isAlive = false;
                                    }
                                }
                            }
                        }
                        if(player.usingSupporter2Time>120) {
                            player.usingSupporter2Time=0;
                            player.isUsingItem=false;
                        }
                        break;
                    case 6:
                        Supporter3 supporter3 = new Supporter3(player);
                    default:
                        break;
                }
            }
            for (Enemies e: enemiesList){
                e.update();
                if (e.isExploded){
                    numOfDeath++;
                }
            }



        }

        if (player.isAlive==false){
            GameOverScreen gameOverScreen = new GameOverScreen();
            GameManager.getInstance().getStackScreen().push(gameOverScreen);
            gameWindow.addMouseListener(gameOverScreen);

        }
        shotAnimation.update();
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
        bufferGraphics.drawImage(Z_icon, 520, 370,null);
        bufferGraphics.drawImage(X_icon, 520, 400,null);
        bufferGraphics.drawImage(C_icon, 520, 430,null);
        bufferGraphics.drawImage(QuantityBoard_icon, 580, 371,null);
        bufferGraphics.drawImage(QuantityBoard_icon, 580, 402,null);
        bufferGraphics.drawImage(QuantityBoard_icon, 580, 431,null);
        bufferGraphics.drawImage(MoneyBoard_icon, 10, 355,null);
        bufferGraphics.drawImage(health_and_armor, 10, 400, null);

        if(player.usingSupporter1Time>0) {
            if (640-5*player.usingSupporter1Time>=320){
                bufferGraphics.drawImage(spriteSupporter1,640-5*player.usingSupporter1Time, 50,null);
                shotAnimation.draw(g,640-5*player.usingSupporter1Time+154, 107);
            }
        }

        if(player.usingSupporter2Time>0) {
            if (640-5*player.usingSupporter2Time>=320){
                bufferGraphics.drawImage(spriteSupporter2,640-5*player.usingSupporter2Time, 50,null);
                shotAnimation.draw(g,640-5*player.usingSupporter1Time+234/2, 50+136/2);
            }
        }


        for (Enemies e:enemiesList){
            e.draw(bufferGraphics);
        }
        for (Mine m:minesList){
            if (m.isAlive) {
                m.draw(bufferGraphics);
        }}
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

    @Override
    public void keyTyped(KeyEvent e) {

        player.useItem(e.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
