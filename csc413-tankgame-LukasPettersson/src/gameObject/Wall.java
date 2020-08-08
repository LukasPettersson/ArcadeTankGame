package gameObject;

import java.awt.image.BufferedImage;

public class Wall extends Stationary{

    boolean isBreakable;
    int lifeCount;

    public Wall(int x, int y, BufferedImage bi, boolean isbreakable, int lifeCount) {
        super(x, y, bi);
        this.isBreakable = isbreakable;
        this.lifeCount = lifeCount;
    }
    public boolean isBreakable(){
        return this.isBreakable;
    }
    public void decrementLifeCount(){
        this.lifeCount--;
    }

    public int getLifeCount() {
        return lifeCount;
    }
}
