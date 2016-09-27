
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;



public class Player{
    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    public final int IDLE = 0; 
    public final int UP = 2;
    public final int DOWN = -2;
    public final int LEFT = -2;
    public final int RIGHT = 2;

    public PlayerAnimation playerAnimation;
    public Animation animation;
    
    public int direction = DOWN;
    public Vector2 pos = new Vector2();
    
    public Player ( float x, float y) {
        pos.x = x;
        pos.y = y;
        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        animation = playerAnimation.downIdling;
    }
    
    public void update () {
		
        processKeys();
        playerAnimation.setLocation(pos.x, pos.y);
    }
    
    private void processKeys () {
    
        if ((Gdx.input.isKeyPressed(Keys.W))) {
            direction  = UP;
            pos.y += UP;
            animation = playerAnimation.walkingUpAnima;
        }	
        else if (Gdx.input.isKeyPressed(Keys.A)) {
            direction = LEFT;
            pos.x += LEFT;
            animation = playerAnimation.walkingLeftAnima;
        } 
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            direction  = RIGHT;
            pos.x += RIGHT;
            animation = playerAnimation.walkingRightAnima;

        } 
        else if(Gdx.input.isKeyPressed(Keys.S)) {
            direction  = DOWN;
            pos.y += DOWN;
            animation = playerAnimation.walkingDownAnima;

        }
        
        else{
            //X AND Y SHOULD NOT CHANGE
            //This is when the chatcter is in IDLE
            if(direction == LEFT){
                animation = playerAnimation.leftIdling;
            }
             
            if(direction == RIGHT){
                animation = playerAnimation.rightIdling;
            }
             
            if(direction == UP){
                animation = playerAnimation.upIdling;
            }
             
            if(direction == DOWN){
                animation = playerAnimation.downIdling;
            }
            
        }
    }

    
}
