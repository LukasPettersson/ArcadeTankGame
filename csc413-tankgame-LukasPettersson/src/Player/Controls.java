package Player;

import gameObject.UserControlledCharacter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

    private UserControlledCharacter character;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    private final int useItem;

    public Controls(int up, int down, int right, int left, int shoot,int useItem, UserControlledCharacter character) {

        this.character = character;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.useItem = useItem;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyPressed = keyEvent.getKeyCode();
        if (keyPressed == up) {
            this.character.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.character.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.character.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.character.toggleRightPressed();
        }
        if(keyPressed == shoot){
            this.character.toggleShootPressed();
        }
        if(keyPressed == useItem){
            this.character.toggleUseItemPressed();
        }


    }


    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyReleased = keyEvent.getKeyCode();
        if (keyReleased  == up) {
            this.character.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.character.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.character.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.character.unToggleRightPressed();
        }
        if(keyReleased == shoot){
            this.character.unToggleShootPressed();
        }
        if(keyReleased == useItem){
            this.character.unToggleUseItemPressed();
        }
    }
}
