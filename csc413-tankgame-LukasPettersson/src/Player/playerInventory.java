package Player;

import gameObject.Item;
import gameObject.UserControlledCharacter;

import java.lang.reflect.Array;
import java.util.*;


public class playerInventory{
    private ArrayList<Item> itemQueue;
    private final int MAX_SIZE= 3;

    public playerInventory(){
        itemQueue = new ArrayList<>();
    }
    public int getMAX_SIZE() {
        return this.MAX_SIZE;
    }

    public void addItem(Item item){
        this.itemQueue.add(item);

    }
    public int getSize(){
        return this.itemQueue.size();
    }
    public void useItem(UserControlledCharacter usr){
        if (!(itemQueue.isEmpty())) {
            itemQueue.get(0).activateItem(usr);
            this.itemQueue.remove(0);
        }
    }
    public void clearInventory(){
        while(!(itemQueue.isEmpty())){
            itemQueue.remove(0);
        }
    }
}
