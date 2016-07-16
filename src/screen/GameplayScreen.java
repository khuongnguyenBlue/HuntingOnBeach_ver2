package screen;

import model.*;
import stuff.BackgroundBuild;
import stuff.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Laptop88 on 7/14/2016.
 */
public class GameplayScreen extends Screen implements MouseMotionListener, MouseListener, KeyListener{
    BufferedImage background;
    BufferedImage bufferImage;
    BufferedImage yatchImage,shipImage;
    BufferedImage mouseIcon,iconMine;
    GameLevel gameLevel;
    EnemyFactory enemyFactory;
    BackgroundBuild drawnBackground= new BackgroundBuild();
    int mousePressedTime=0, countScreen;
    boolean isPressing = false;
    Player player=new Player();

    ArrayList<Enemies>  enemiesList = new ArrayList<>();
    ArrayList<Mine> minesList= new ArrayList<>();
    GameWindow gameWindow;
    public GameplayScreen(GameWindow gameWindow){
        try {
            yatchImage= ImageIO.read(new File("Resource/Background/image 132.png"));
            shipImage=ImageIO.read(new File("Resource/Background/image 1134.png"));
            iconMine = ImageIO.read(new File("Resource/char/image 36.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gameWindow = gameWindow;
        GameLevel gameLevel = new GameLevel();
        enemyFactory = new EnemyFactory(player);
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
        player.shotAnimation.update();
        player.smokeAnimation.update();
        if (isPressing) {
            player.shot();
            for (Enemies e: enemiesList){
                e.checkIfHit(player.posX+25, player.posY+25);
            }


        }

        if (player.isUsingItem) {
            switch (player.itemType){
                case 1:
                    player.usingItemTime++;
                    player.GrenadeUsingAnimation.update();
                    player.GrenadeAnimation.update();
                    if (player.usingItemTime==10) {
                        for(Enemies e: enemiesList){
                            if (e.isGoing) {
                                e.isAlive = false;
                            }
                        }
                    }
                    if (player.usingItemTime > 10) {
                        player.usingItemTime = 0;
                        player.isUsingItem=false;
                        player.GrenadeUsingAnimation.posX=300; player.GrenadeUsingAnimation.posY=400;
                        player.GrenadeAnimation.posX=300; player.GrenadeAnimation.posY=350;
                    }
                    break;
                case 2:
                    player.usingNetTime++;
                    if(player.NetAnimation.posX>=50) {
//                        player.NetAnimation.speedX = 0;
                        player.NetAnimation.update();
                    }
                        if (player.usingNetTime==10) {

                        for(Enemies e: enemiesList){

                            e.speed=-1;

                        }

                    }
                    if (player.usingNetTime > 50) {
                        player.usingNetTime = 0;
                        player.isUsingItem=false;
                        for(Enemies e: enemiesList){

                            e.speed=1 + (int) (Math.random() * 1);

                        }
                        player.NetAnimation.posX=200; player.NetAnimation.posY=150;

                    }
                    break;
                case 3:
                    if (minesList.size()<6){
                        minesList.add(new Mine(iconMine, 120 + (int) (Math.random()*100),150 + (int) (Math.random() * 200)));
                    }
                    for (Mine m: minesList){
                        for (Enemies e: enemiesList){
                            if ((new Rectangle(e.posX, e.posY, e.sprite.getWidth(), e.sprite.getHeight()).contains(m.posX, m.posY+m.sprite.getHeight()/2)&&m.isAlive)){

                                e.isAlive=false;
                                m.isAlive=false;
                            }
                        }
                    }


                    break;
                default: break;
            }

        }


        for (Enemies e: enemiesList){
            e.update();

        }

        for (Mine m:minesList){
            m.update();
        }

        if (player.isAlive==false){
            GameOverScreen gameOverScreen = new GameOverScreen();
            GameManager.getInstance().getStackScreen().push(gameOverScreen);
            gameWindow.addMouseListener(gameOverScreen);

        }
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
        for (Enemies e:enemiesList){
            e.draw(bufferGraphics);
        }
        for (Mine m: minesList){
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
