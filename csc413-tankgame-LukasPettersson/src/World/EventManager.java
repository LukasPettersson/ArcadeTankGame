package World;

import gameObject.Item;


import java.util.ArrayList;
import java.util.Random;
public class EventManager {
    /**
     this class' object is to create events on the world and put them in there,

     - It spawns random items at random location assuming that the location is an open spoce.

     - It initializes all items through a through a HashMap like we did in the first project to
     create new objects when we need them.

     - It initializes two players with their respective controls and ties them together

     - Manages the inventories of the two players
     */
    private SpriteData sd;
    private Item item;
    private Random rand;

    public EventManager() {
        this.sd = new SpriteData();
        this.rand = new Random();
        //dummy object
        this.item = new Item(-100,-100,sd.getSprite("rock"),1);
    }

    public void generateSpawnPowerUpEvent(ArrayList<ArrayList<Integer>> spawnPoints, ArrayList<Item> itemList){
        //number for creating a random object
        int randNumber = this.rand.nextInt(item.getMAX_ITEMS());

        for(int i = 0; i < spawnPoints.size(); i++) {
            int bigRand = this.rand.nextInt(5000);
                if (bigRand == 1 && itemList.size() < spawnPoints.size()) {
                    itemList.add(new Item(spawnPoints.get(i).get(0), spawnPoints.get(i).get(1), sd.getSprite("ArmorUp"), randNumber));
                }
        }
    }
}
