package gameObject;

import Player.playerInventory;
import World.SpriteData;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UserControlledCharacter extends Character {

    protected boolean UpPressed;
    protected boolean DownPressed;
    protected boolean RightPressed;
    protected boolean LeftPressed;
    protected boolean ShootPressed;
    protected boolean useItemPressed;
    private int shootTimer,itemTimer,shootCooldownTimer;
    protected int[] spawnPoint = new int[2]; //stored as x,y
    private ArrayList<Projectile> projectiles;
    private SpriteData sd = new SpriteData();
    private playerInventory inventory;
    public UserControlledCharacter(int x, int y, BufferedImage bi, int vx, int vy, int angle, int hitPoints, playerInventory inventory) {
        super(x, y, bi, vx, vy, angle, hitPoints);
        this.spawnPoint[0] = x;
        this.spawnPoint[1] = y;
        this.projectiles = new ArrayList<>();
        this.shootTimer = 0;
        this.itemTimer = 0;
        this.inventory = inventory;
        this.shootCooldownTimer = 36;

    }

    //are used to see when buttons are pressed
    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShootPressed() {this.ShootPressed = true;}

    public void toggleUseItemPressed() {
        this.useItemPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShootPressed() {this.ShootPressed = false;}

    public void unToggleUseItemPressed() { this.useItemPressed = false; }

    public void update() {
        if (this.UpPressed) {
            moveForwards();
        }
        if (this.DownPressed) {
            moveBackwards();
        }

        if (this.LeftPressed) {
            rotateLeft();
        }
        if (this.RightPressed) {
            rotateRight();
        }
        if(this.useItemPressed){

            if(this.itemTimer <=0){
                //what do I call here to make them use an item
                this.inventory.useItem(this);
                itemTimer = 36;
            }

        }
        if (this.ShootPressed) {
                if (shootTimer <= 0) {
                    projectiles.add(new Projectile(this.x, this.y, sd.getSprite("bullet"), this.angle, 1));
                    shootTimer = shootCooldownTimer;
                }
        }
        shootTimer--;
        itemTimer--;
        for(int i = 0; i < projectiles.size(); i++){
            this.projectiles.get(i).update();
            //if thr projectile is off screen
            if(projectiles.get(i).checkBorders()){
                this.projectiles.remove(i);
            }
        }
    }

    public int getSpawnPoint(int idx){
        return spawnPoint[idx];
    }
    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }

}
