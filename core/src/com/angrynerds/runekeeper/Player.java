
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;



public class Player{
    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    public final int IDLE = 0; 
    public final int UP = -1;
    public final int DOWN = 1;
    public final int LEFT = -1;
    public final int RIGHT = 1;

    public PlayerAnimation playerAnimation;
    public Animation animation;
    
    public int direction = DOWN;
    public boolean moving = false;
    public Vector2 pos = new Vector2();
    
    public Player ( float x, float y) {
        pos.x = x;
        pos.y = y;
        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        playerAnimation.setDirection(direction);
        playerAnimation.setMoving(moving);
        animation = playerAnimation.currentAnimation;
    }
    
    public void update () {
		
        processKeys();
        playerAnimation.setLocation(pos.x, pos.y);
        playerAnimation.setDirection(direction);
        playerAnimation.setMoving(moving);      
        playerAnimation.updateAnimation();
        
        animation = playerAnimation.currentAnimation;

    }
    
    private void processKeys () {
    
        if ((Gdx.input.isKeyPressed(Keys.W))) {
            direction  = UP;
            pos.y -= UP;
            moving = true;
        }	
        if (Gdx.input.isKeyPressed(Keys.A)) {
            direction = LEFT;
            pos.x += LEFT;
            moving = true;

        } 
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            direction  = RIGHT;
            pos.x += RIGHT;
        } 
        else if(Gdx.input.isKeyPressed(Keys.S)) {
            direction  = DOWN;
            pos.y += DOWN;
            moving = true;
        }
        
        else{
            direction = IDLE;
            moving  = false;
            //X AND Y SHOULD STAY THE SAME HERE
        }
    }

    
}
