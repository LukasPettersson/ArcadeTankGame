package World;

import Player.Controls;
import Player.GUI;
import Player.player;
import gameObject.Item;


import gameObject.Wall;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static javax.imageio.ImageIO.read;


public class World extends JPanel {

    public static final int WORLD_WIDTH = 2820;
    public static final int WORLD_HEIGHT = 2820;
    public static final int SCREEN_WIDTH =  1280;    //1280/20 = 64 16 BLOCK BUFFER
    public static final int SCREEN_HEIGHT = 720;    //720/20 = 36   18 BLOCK BUFFER

    private BufferedImage World;
    private Graphics2D buffer;
    private JFrame jFrame;
    static private BufferedImage backgroundImage;
    private player user1,user2;
    private ArrayList<Wall> walls;
    private ArrayList<ArrayList<Integer>> listInList;
    private ArrayList<Item> itemList;
    private CollisionDetector collisionDetector;
    private GUI gui= new GUI(500, 650);
    private GUI gui1= new GUI(690,650);

    private EventManager evManager;
    private SpriteData sd;

    public static void main(String[] args){
        World world = new World();
        world.init();
        world.runLoop();
    }

    public void runLoop(){
        try {
            while (true) {
                user1.getUsrCharacter().update();
               user2.getUsrCharacter().update();
                collisionDetector.resolveCollisions(user1, user2, walls,itemList);
                evManager.generateSpawnPowerUpEvent(listInList,itemList);
                repaint();
                if(user1.getLifes() == -1){
                    System.out.println("player 2 won");
                    resetGame(user1,user2);


                }else if(user2.getLifes() == -1){
                    System.out.println("player 1 won");
                    resetGame(user1,user2);

                }
                //makes our game run at 144 frames per second
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    public void resetGame(player user1,player user2){

        user1.setLifes(3);
        user1.getUsrCharacter().setHitPoints(5);
        user1.getUsrCharacter().setX( user1.getUsrCharacter().getSpawnPoint(0));
        user1.getUsrCharacter().setY(user1.getUsrCharacter().getSpawnPoint(1));
        user1.getUsrCharacter().setAngle(user1.getUsrCharacter().getDefaultAngle());
        user1.setLifes(3);
        user1.getUsrCharacter().setHitPoints(5);

        user2.setLifes(3);
        user2.getUsrCharacter().setHitPoints(5);
        user2.getUsrCharacter().setX( user2.getUsrCharacter().getSpawnPoint(0));
        user2.getUsrCharacter().setY(user2.getUsrCharacter().getSpawnPoint(1));
        user2.getUsrCharacter().setAngle(user2.getUsrCharacter().getDefaultAngle());
        user2.getUsrCharacter().setHitPoints(5);
        user2.setLifes(3);
    }
    public void init(){
        this.jFrame = new JFrame("TankGame");
        this.World = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);

        //
        evManager = new EventManager();
        listInList = new ArrayList<>();
        itemList = new ArrayList<>();
        walls = new ArrayList<>();
        collisionDetector = new CollisionDetector();
        sd = new SpriteData();
        initializeMap();
        //create the two tanks and other scenery, store walls into an arrayList
        this.user1 = new player(sd,"player1",500,500,0);
        this.user2 = new player(sd,"player2",2320,2320,180);

        //fetch the background image for the game and give an error message if it is not possible
        try{
                backgroundImage = read(World.class.getClassLoader().getResource("background1.jpg"));
            }catch(IOException ex){
                System.out.println(ex.getMessage());
        }
        //Player controls setup
        Controls PlayerOneControls = new Controls(KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_ENTER,
                KeyEvent.VK_DELETE,
                this.user1.getUsrCharacter());

        Controls PlayerTwoControls = new Controls(KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_D,
                KeyEvent.VK_A,
                KeyEvent.VK_SPACE,
                KeyEvent.VK_F,
                this.user2.getUsrCharacter());

        //JFrame setup
        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(PlayerOneControls);
        this.jFrame.addKeyListener(PlayerTwoControls);
        this.jFrame.setSize(this.SCREEN_WIDTH, this.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    public void initializeMap(){
        try{
        InputStreamReader isr = new InputStreamReader(World.class.getClassLoader().getResourceAsStream("maps/map3.txt"));
        BufferedReader mapReader = new BufferedReader(isr);

        String row = mapReader.readLine();
        String[] mapInfo = row.split("\t");
        int numCols = Integer.parseInt(mapInfo[0]);
        int numRows = Integer.parseInt(mapInfo[1]);
        int numSpawnLoc = 0;
        for(int i = 0; i < numRows;i++ ){
            row = mapReader.readLine();
            mapInfo = row.split("\t");
            for(int j = 0; j < numCols; j++){
                switch(mapInfo[j]){
                    case "9":
                        this.walls.add(new Wall(20*j,20*i,sd.getSprite("unbreakableWall"),false,-1));
                        break;
                    case "1": //breakable wall
                        this.walls.add(new Wall(20*j,20*i,sd.getSprite("breakableWall"),true,1));
                        break;
                    case "2": //set the possible positions that a powerup can spawn on, are set by only one tile in the matrix
                            listInList.add(new ArrayList<>());
                            listInList.get(numSpawnLoc).add(20*j); //x
                            listInList.get(numSpawnLoc).add(20*i); //y
                            numSpawnLoc++;
                        break;
                    default:
                        break;
                }
            }
        }
        }catch(IOException ex){
            System.out.print(ex.getMessage());
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = this.World.createGraphics( );
        //draw the background image into the buffer
        buffer.drawImage(backgroundImage, null, 0, 0);
        //draw the tanks and scenery into the buffer
        for (int i = 0; i < walls.size(); i++) {
            this.walls.get(i).drawImage(buffer);
        }

        for (int i = 0; i < itemList.size(); i++) {
            this.itemList.get(i).drawImage(buffer);
        }
        this.user1.getUsrCharacter().getProjectiles().forEach(projectile -> projectile.drawImage(buffer));
        this.user2.getUsrCharacter().getProjectiles().forEach(projectile -> projectile.drawImage(buffer));
        this.user1.getUsrCharacter().drawImage(buffer);
        this.user2.getUsrCharacter().drawImage(buffer);

        BufferedImage leftHalf =  this.World.getSubimage((user1.getUsrCharacter().getX() -SCREEN_WIDTH/4),(user1.getUsrCharacter().getY() - SCREEN_HEIGHT/2),this.SCREEN_WIDTH/2,this.SCREEN_HEIGHT);
        BufferedImage rightHalf =  this.World.getSubimage((user2.getUsrCharacter().getX() -SCREEN_WIDTH/4),(user2.getUsrCharacter().getY() - SCREEN_HEIGHT/2),this.SCREEN_WIDTH/2,this.SCREEN_HEIGHT);
        BufferedImage mm = this.World.getSubimage(400,400,this.WORLD_WIDTH - 800,this.WORLD_HEIGHT - 820);

        //push the buffer into the world object
        g2.drawImage(leftHalf,0,0,null);
        g2.drawImage(rightHalf,this.SCREEN_WIDTH/2,0,null);
        gui.drawImage(g,user1);
        gui1.drawImage(g,user2);
        g2.scale(.15,.15);
        g2.drawImage(mm,3300,0,null);
    }
}

