package Player;

import World.SpriteData;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GUI {



    private SpriteData sd;
    private int x,y;

    public GUI(int x, int y){
        this.sd = new SpriteData();
        this.x = x;
        this.y = y;
    }

    public void displayLifePoints(player p1, Graphics2D g2d, AffineTransform rotation){
        if(p1.getLifes() == 3) {
            g2d.drawImage(sd.getSprite("3hearts"), rotation, null);
        }
        else if(p1.getLifes() == 2){
            g2d.drawImage(sd.getSprite("2hearts"), rotation, null);
        }
        else if(p1.getLifes() == 1){
            g2d.drawImage(sd.getSprite("1hearts"), rotation, null);
        }
    }

    public void displayHitPoints(player p1, Graphics2D g2d, AffineTransform rotation){
        switch(p1.getUsrCharacter().getHitPoints()){
            case 5:
                g2d.drawImage(sd.getSprite("5shields"), rotation, null);
            break;
            case 4:
                g2d.drawImage(sd.getSprite("4shields"), rotation, null);
            break;
            case 3:
                g2d.drawImage(sd.getSprite("3shields"), rotation, null);
                break;
            case 2:
                g2d.drawImage(sd.getSprite("2shields"), rotation, null);
                break;
            case 1:
                g2d.drawImage(sd.getSprite("1shields"), rotation, null);
                break;
            default:
                break;
        }
    }

    public void displayInventory(player p1,Graphics2D g2d, AffineTransform rotation){

        switch(p1.getInventory().getSize()){

            case 0:
                g2d.drawImage(sd.getSprite("emptyInventory"), rotation, null);
                break;
            case 1:
                g2d.drawImage(sd.getSprite("1ItemInventory"), rotation, null);
                break;
            case 2:
                g2d.drawImage(sd.getSprite("2ItemInventory"), rotation, null);
                break;
            case 3:
                g2d.drawImage(sd.getSprite("3ItemInventory"), rotation, null);
                break;
            /*
            default:
                g2d.drawImage(sd.getSprite("emptyInventory"), rotation, null);
                break;
            */
        }
    }
    public void drawImage(Graphics g,player p1) {
        AffineTransform lifeRotation = AffineTransform.getTranslateInstance(this.x,this.y);
        AffineTransform hitRotation = AffineTransform.getTranslateInstance(this.x,this.y + 20);
        AffineTransform ItemRotation = AffineTransform.getTranslateInstance(this.x,this.y + 40);
        Graphics2D g2d = (Graphics2D) g;
        displayLifePoints(p1,g2d,lifeRotation);
        displayHitPoints(p1,g2d,hitRotation);
        displayInventory(p1,g2d,ItemRotation);
    }

}
