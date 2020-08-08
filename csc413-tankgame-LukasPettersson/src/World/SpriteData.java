package World;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import static javax.imageio.ImageIO.read;
// tileset download page
//https://0x72.itch.io/dungeontileset-ii?download
public class SpriteData {

    private HashMap<String, BufferedImage> spriteData = new HashMap<>();

    public SpriteData(){
        try {
            spriteData.put("unbreakableWall",read(SpriteData.class.getClassLoader().getResource("unbreakableWall.png")));
            spriteData.put("breakableWall",read(SpriteData.class.getClassLoader().getResource("breakableWall.png")));
            spriteData.put("bullet",read(SpriteData.class.getClassLoader().getResource("bullet.png")));
            spriteData.put("player1", read(SpriteData.class.getClassLoader().getResource("tanks1.jpg")));
            spriteData.put("player2", read(SpriteData.class.getClassLoader().getResource("tanks2.jpg")));
            spriteData.put("rock", read(SpriteData.class.getClassLoader().getResource("rock.png")));
            spriteData.put("rockGroup", read(SpriteData.class.getClassLoader().getResource("rockGroup.png")));
            spriteData.put("1hearts", read(SpriteData.class.getClassLoader().getResource("1hearts.png")));
            spriteData.put("2hearts", read(SpriteData.class.getClassLoader().getResource("2hearts.png")));
            spriteData.put("3hearts", read(SpriteData.class.getClassLoader().getResource("3hearts.png")));
            spriteData.put("1shields", read(SpriteData.class.getClassLoader().getResource("1shields.png")));
            spriteData.put("2shields", read(SpriteData.class.getClassLoader().getResource("2shields.png")));
            spriteData.put("3shields", read(SpriteData.class.getClassLoader().getResource("3shields.png")));
            spriteData.put("4shields", read(SpriteData.class.getClassLoader().getResource("4shields.png")));
            spriteData.put("5shields", read(SpriteData.class.getClassLoader().getResource("5shields.png")));
            spriteData.put("ArmorUp", read(SpriteData.class.getClassLoader().getResource("armorUp.png")));
            spriteData.put("emptyInventory", read(SpriteData.class.getClassLoader().getResource("emptyInventory.png")));
            spriteData.put("1ItemInventory", read(SpriteData.class.getClassLoader().getResource("1ItemInventory.png")));
            spriteData.put("2ItemInventory", read(SpriteData.class.getClassLoader().getResource("2ItemInventory.png")));
            spriteData.put("3ItemInventory", read(SpriteData.class.getClassLoader().getResource("3ItemInventory.png")));
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public BufferedImage getSprite(String name){
        return spriteData.get(name);
    }
}
