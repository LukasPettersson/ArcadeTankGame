package gameObject;




import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;

import static javax.imageio.ImageIO.read;

public class gameObject {
    //taken from the top left of the image
    protected int x,y;              // position of the object in the screen
    private BufferedImage assetImage;
    protected int angle;
    protected Rectangle hitBox;

    protected int getAngle(){
        return angle;
    }
    gameObject(int x, int y, BufferedImage bi){
        //load in an asset image to the class object
        assetImage = bi;
        //set default spawn location
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle(x,y,this.assetImage.getWidth(),this.assetImage.getHeight());
    }
    public Rectangle getHitBoxBounds(){
        return hitBox.getBounds();
    }
    public Rectangle getHitBox(){return hitBox;}
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.assetImage.getWidth() / 2.0, this.assetImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.assetImage, rotation, null);
    }

}
