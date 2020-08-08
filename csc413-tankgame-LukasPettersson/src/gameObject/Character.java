package gameObject;

import java.awt.image.BufferedImage;

public class Character extends Movable {

    int hitPoints;
    int defaultHitPoints;
    Character(int x, int y, BufferedImage bi, int vx, int vy, int angle, int hitPoints) {
        super(x, y, bi, vx, vy, angle, 3);
        this.hitPoints = hitPoints;
        this.defaultHitPoints = hitPoints;
    }
    public void reduceHitPoints(){
        hitPoints--;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }
    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }

    public int getDefaultHitPoints(){
        return this.defaultHitPoints;
    }
}
