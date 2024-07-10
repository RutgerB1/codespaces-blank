package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Joris;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 Tiles
    final int scale = 3;	// scales x3

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 16;	// game screen width in tiles
    public final int maxScreenRow = 12;	//game screen height in tiles
    public final int screenWidth = maxScreenColumn * tileSize;	//	height in pixels
    public final int screenHeight = maxScreenRow * tileSize;	// width in pixels


    //WORLD SETTINGS

    public final int maxWorldCol = 60;
    public final int maxWorldRow = 60;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS IS HERE!
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cCheck = new CollisionChecker(this);
    //public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this , keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {

        //aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                // Here we UPDATE game information and,
                update();
                // We DRAW the new situation based on the information
                repaint();
                delta = 0;
                drawCounter ++;
            }

            if(timer > 1000000000){
                System.out.println("FPS="+drawCounter);
                drawCounter = 0;
                timer = 0;
            }
        }

    }
    public void update() {

        player.update();


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        long drawStart = 0;
        if (keyH.showDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);


        //DEBUG
        if (keyH.showDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passedTime = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("drawtime:" + passedTime, 10, 400);
            System.out.println("drawtime = "+passedTime);
        }


        g2.dispose();

    }

}
