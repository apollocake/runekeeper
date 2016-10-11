
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Player{
    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    public final int IDLE = 0; 
    public final int UP = 2;
    public final int DOWN = -2;
    public final int LEFT = -2;
    public final int RIGHT = 2;
    
    boolean runeFire = false;
    boolean runeWater = false;
    boolean runeWind = false;
    
    public PlayerAnimation playerAnimation;
    AttackingFunction attackingFunction;
    Rune rnFire = new Rune();
    Rune rnWater = new Rune();
    Rune rnWind = new Rune();
    public Animation animation;
    
    public String direction = "DOWN";
    String attack = "";

    public Vector2 pos = new Vector2();
    
    public Player ( float x, float y) {
        pos.x = x;
        pos.y = y;
        rnFire.setRune(new RuneFire());
        rnWater.setRune(new RuneWater());
        rnWind.setRune(new RuneWind());
        attackingFunction = new AttackingFunction();
        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        animation = playerAnimation.downIdling;
    }
    
    public void update (SpriteBatch batch) {	
        processKeys();
        playerAnimation.setLocation(pos.x, pos.y);
        attackingFunction.setLocation(pos.x, pos.y);
        rnFire.update(pos.x, pos.y);
        rnWater.update(pos.x, pos.y);
        rnWind.update(pos.x, pos.y);
        runePocessing(batch);

    }
    
    private void processKeys () {
        if(Gdx.input.isKeyPressed(Keys.NUM_0)){
            runeFire = false;
            runeWater = false;
            runeWind = false;
        }
        
        if(Gdx.input.isKeyPressed(Keys.NUM_1)){
            //rnOne.use(batch);
            runeFire = true;
            runeWater = false;
            runeWind = false;
        }
        
        if(Gdx.input.isKeyPressed(Keys.NUM_2)){
            runeFire = false;
            runeWater = true;
            runeWind = false;
        }
        
        if(Gdx.input.isKeyPressed(Keys.NUM_3)){
            runeFire = false;
            runeWater = false;
            runeWind = true;
        }
        
        //if()
        
        if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.W)){
            direction = "LEFT";
            pos.x -= Math.sin(Math.toRadians(45))*2;
            pos.y += Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingLeftAnima;

        }
                
        else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.W)){
            direction = "RIGHT";
            pos.x += Math.sin(Math.toRadians(45))*2;
            pos.y += Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingRightAnima;

        }
        
        else if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.S)){
            direction = "LEFT";
            pos.x -= Math.sin(Math.toRadians(45))*2;
            pos.y -= Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingLeftAnima;

        }
                
        else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.S)){
            direction = "RIGHT";
            pos.x += Math.sin(Math.toRadians(45))*2;
            pos.y -= Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingRightAnima;
        }
    
        else if (Gdx.input.isKeyPressed(Keys.W)) {
            direction  = "UP";
            pos.y += UP;
            animation = playerAnimation.walkingUpAnima;

        }	
        else if (Gdx.input.isKeyPressed(Keys.A)) {
            direction = "LEFT";
            pos.x += LEFT;
            animation = playerAnimation.walkingLeftAnima;

        } 
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            direction  = "RIGHT";
            pos.x += RIGHT;
            animation = playerAnimation.walkingRightAnima;

        } 
        else if(Gdx.input.isKeyPressed(Keys.S)) {
            direction  = "DOWN";
            pos.y += DOWN;
            animation = playerAnimation.walkingDownAnima;
           
        }
        
       else if(Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("DOWN")){
            animation = attackingFunction.attackingDownAnima;
            attack  = "DOWN_ATTACKING";
            
        }
        
        else if(Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("UP")){
            animation = attackingFunction.attackingUpAnima;
            attack  = "UP_ATTACKING";
        }
        
        else if(Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("LEFT")){
            animation = attackingFunction.attackingLeftAnima;
            attack  = "LEFT_ATTACKING";
        }
        
         else if(Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("RIGHT")){
            animation = attackingFunction.attackingRightAnima;
           attack  = "RIGHT_ATTACKING";
        }
               
        else{
            //X AND Y SHOULD NOT CHANGE
            //This is when the chatcter is in IDLE
            if(direction.equals("LEFT")){
                animation = playerAnimation.leftIdling;
            }
             
            else if(direction.equals("RIGHT")){
                animation = playerAnimation.rightIdling;
            }
             
            else if(direction.equals("UP")){
                animation = playerAnimation.upIdling;
            }
             
            else if(direction.equals("DOWN")){
                animation = playerAnimation.downIdling;
            }
            
            //
            else if(attack.equals("DOWN_ATTACKING")){
                animation = attackingFunction.downIdling;
            }
            
            else if(attack.equals("UP_ATTACKING")){
                animation = attackingFunction.upIdling;
            }
            
            else if(attack.equals("LEFT_ATTACKING")){
                animation = attackingFunction.leftIdling;
            }
            
            else if(attack.equals("RIGHT_ATTACKING")){
                animation = attackingFunction.rightIdling;
            }
               
        } 
        
    }
    
    private void runePocessing(SpriteBatch batch){
        if(runeFire == true){
            rnFire.use(batch);
        }
        
        else if(runeWater == true){
            rnWater.use(batch);
        }
        
        else if(runeWind == true){
            rnWind.use(batch);
        }
    }

}
