package gameObject;

import World.World;

import java.awt.image.BufferedImage;

public class Projectile extends Movable{

    int damage;

    Projectile(int x, int y, BufferedImage bi,int angle, int damage) {
        super(x, y, bi, 50, 50, angle, 10);
        this.damage = damage;
    }


    public void update(){
            moveForwards();
        }

    public boolean checkBorders() {
       if(this.getX() > World.WORLD_WIDTH || this.getX() < 0){
           return true;
       }
        else if(this.getY() > World.WORLD_HEIGHT || this.getY() < 0){
            return true;
        }
        else
       {
           return false;
       }
    }
}
