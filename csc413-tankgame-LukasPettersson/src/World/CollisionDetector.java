package World;


import Player.player;
import gameObject.Item;
import gameObject.Projectile;
import gameObject.Wall;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CollisionDetector {

    public void resolveCollisions(player user1, player user2, ArrayList<Wall> walls,ArrayList<Item> itemList) {
        tankVSEnvironment(user1,walls);
        tankVSEnvironment(user2,walls);
        tankVSTank(user1,user2);
        projectileVSTank(user1,user2);
        projectileVSTank(user2,user1);
        projectileVSEnvironment(user1,walls);
        projectileVSEnvironment(user2,walls);
        tankVSpowerUp(user1,itemList);
        tankVSpowerUp(user2,itemList);
    }

    private void tankVSpowerUp(player user, ArrayList<Item> itemList){
        ArrayList<Integer> tempList = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++){
            if(user.getUsrCharacter().getHitBoxBounds().intersects(itemList.get(i).getHitBoxBounds())){
                if(user.getInventory().getSize() < user.getInventory().getMAX_SIZE()) {
                    Item item = itemList.get(i);
                    user.getInventory().addItem(item);
                    itemList.remove(i);
                }
            }
        }
        for(int i = 0; i < tempList.size(); i++){
            itemList.remove(tempList.get(i));
        }
    }

    private void tankVSEnvironment(player user, ArrayList<Wall> walls){
        for(int i = 0; i < (walls.size()); i++) {
            if (user.getUsrCharacter().getHitBoxBounds().intersects(walls.get(i).getHitBoxBounds())){

                int vx = (int) Math.round(user.getUsrCharacter().getR()* Math.cos(Math.toRadians(user.getUsrCharacter().getAngle())));
                int vy = (int) Math.round(user.getUsrCharacter().getR() * Math.sin(Math.toRadians(user.getUsrCharacter().getAngle())));
                int x = user.getUsrCharacter().getX();
                int y = user.getUsrCharacter().getY();
                x -= vx;
                y -= vy;
                user.getUsrCharacter().setAngle(user.getUsrCharacter().getAngle() -180);

                user.getUsrCharacter().getHitBox().setLocation(x,y);

                if(walls.get(i).isBreakable()){
                    walls.get(i).decrementLifeCount();
                }
                if(walls.get(i).getLifeCount() == 0){
                    walls.remove(i);
                }
            }
        }
    }

    private void tankVSTank(player user1, player user2){
        if (user1.getUsrCharacter().getHitBoxBounds().intersects(user2.getUsrCharacter().getHitBoxBounds())){
            respawn(user1);
            respawn(user2);
        }
    }

    private void projectileVSTank(player user1,player user2){
        for(int i = 0; i < (user1.getUsrCharacter().getProjectiles().size()); i++) {
            if (user2.getUsrCharacter().getHitBoxBounds().intersects(user1.getUsrCharacter().getProjectiles().get(i).getHitBoxBounds())) {
                respawn(user2);
                user1.getUsrCharacter().getProjectiles().remove(i);
            }
        }
    }
    //this lowkey sucks performance wise, but it does the right thing kind of
    //so janky
    private void projectileVSEnvironment(player user, ArrayList<Wall> walls){

        ArrayList<Integer> tempWalls = new ArrayList<>();
        ArrayList<Integer>  tempProjectiles = new ArrayList<>();
        for(int i = 0; i < (user.getUsrCharacter().getProjectiles().size()); i++){
            for(int j = 0; j < (walls.size()); j++){
                if(walls.get(j).getHitBoxBounds().intersects(user.getUsrCharacter().getProjectiles().get(i).getHitBoxBounds())){
                    if(walls.get(j).isBreakable()){
                        walls.get(j).decrementLifeCount();
                        if(walls.get(j).getLifeCount() == 0){
                            tempWalls.add(j);
                        }
                    }
                    tempProjectiles.add(i);
                    break;
                }
            }
        }
        for(int i = 0 ; i < tempWalls.size();i++){
            int temp = tempWalls.get(i);
            walls.remove(temp);
        }

        for(int i = 0 ; i < tempProjectiles.size();i++){
            //might throw index out of bounds exception because two projectiles are getting removed at the same time, it is fine
            try {
                    int temp = tempProjectiles.get(i);
                    user.getUsrCharacter().getProjectiles().remove(temp);
                }catch(IndexOutOfBoundsException ex){
                        System.out.print(ex.getMessage());
                }
        }
    }

    private void respawn(player user){
        user.getUsrCharacter().setX( user.getUsrCharacter().getSpawnPoint(0));
        user.getUsrCharacter().setY(user.getUsrCharacter().getSpawnPoint(1));
        user.getUsrCharacter().setAngle(user.getUsrCharacter().getDefaultAngle());
        user.getUsrCharacter().reduceHitPoints();
        if(user.getUsrCharacter().getHitPoints() == 0){
            user.reduceLifePoints();
            user.getUsrCharacter().setHitPoints(user.getUsrCharacter().getDefaultHitPoints());
        }
        user.getUsrCharacter().getHitBox().setLocation(user.getUsrCharacter().getSpawnPoint(0),user.getUsrCharacter().getSpawnPoint(1));
    }
}
