package gameObject;

import World.World;

import java.awt.image.BufferedImage;

public class Movable extends gameObject {



    int vx;                         // x velocity
    int vy;                         // y velocity

    protected int R;        // how fast the object moves
    private final int ROTATION_SPEED = 4;   //how fast the object rotates
    protected int defaultAngle;
    Movable(int x, int y, BufferedImage bi, int vx, int vy, int angle, int R) {
        super(x, y, bi);
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.defaultAngle = angle;
        this.R = R;
    }

    public int getR(){
        return this.R;
    }
    protected void rotateLeft() {
        this.angle -= this.ROTATION_SPEED;
    }

    protected void rotateRight() {
        this.angle += this.ROTATION_SPEED;
    }

    protected void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        this.x += vx;
        this.y += vy;
        if(!(this instanceof Projectile)){
            checkBorder();
        }
        this.hitBox.setLocation(x,y);

    }

    protected void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    private void checkBorder() {
        if (x < 5) {
            x = 5;
        }
        if (x >= World.WORLD_WIDTH - 42) {
            x = World.WORLD_WIDTH - 42;
        }
        if (y < 5) {
            y = 5;
        }
        if (y >= World.WORLD_HEIGHT - 40) {
            y = World.WORLD_HEIGHT - 40;
        }
    }

    //position getters and setters
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int updatedX){
        this.x = updatedX;
    }
    public void setY(int updatedY){
        this.y = updatedY;
    }
    public void setAngle(int angle){
        this.angle = angle;
    }
    public int getAngle(){return this.angle;}
    public int getDefaultAngle(){
        return defaultAngle;
    }
}
