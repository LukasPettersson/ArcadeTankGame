package gameObject;

import Player.player;

import java.awt.image.BufferedImage;

public class Item extends Stationary {

    final int MAX_ITEMS = 1;
    int  ItemType;

    public Item(int x, int y, BufferedImage bi, int ItemType) {
        super(x, y, bi);
        this.ItemType = ItemType;
    }


    public int getMAX_ITEMS() {
    return MAX_ITEMS;
    }

    public void activateItem(UserControlledCharacter userCharacter){

        switch(ItemType) {
            case 0: //Full Health
                userCharacter.setHitPoints(5);
                break;
            default:
                System.out.println("default case");
                break;
        }
    }
}
