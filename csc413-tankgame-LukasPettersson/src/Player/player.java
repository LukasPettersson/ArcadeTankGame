package Player;
import World.SpriteData;
import gameObject.UserControlledCharacter;

public class player {

    private int lifes;
    private UserControlledCharacter usrCharacter;
    private playerInventory inventory;

    public player(SpriteData sd, String spriteName, int x, int y,int angle){
        this.lifes = 3;
        this.inventory = new playerInventory();
        this.usrCharacter = new UserControlledCharacter(x,y,sd.getSprite(spriteName), 0,0,angle,5,this.inventory);
    }
    public int getLifes(){
        return this.lifes;
    }
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
    public UserControlledCharacter getUsrCharacter(){
        return this.usrCharacter;
    }
    public void reduceLifePoints(){
        this.lifes--;
    }
    public playerInventory getInventory(){
        return this.inventory;
    }

}
