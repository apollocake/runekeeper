
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;



public class Player {
    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    public final int IDLE = 0; 
    public final int UP = 2;
    public final int DOWN = -2;
    public final int LEFT = -2;
    public final int RIGHT = 2;

    public PlayerAnimation playerAnimation;
    AttackingFunction attackingFunction; 
    public Animation animation;
    
    ColorAction coloraction = new ColorAction();
    
    
    public String direction = "DOWN";
    String attack = "";
public Rectangle bounds = new Rectangle();
    public Vector2 pos = new Vector2();
    
    public Player ( float x, float y) {
        pos.x = x;
        pos.y = y;
        attackingFunction = new AttackingFunction();
    
        bounds.width = 20;
        bounds.height = 20;
        
       bounds.x = pos.x;
       bounds.y = pos.y;
       
        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        animation = playerAnimation.downIdling;
    }
    
    public void update () {	
        processKeys();
        playerAnimation.setLocation(pos.x, pos.y);
        attackingFunction.setLocation(pos.x, pos.y);
         
    }
    
    //show the correct animation when player is hit
    public void isHit(){
        if(direction.equals("LEFT"))
        animation = playerAnimation.dleftIdling;
        
        if(direction.equals("RIGHT"))
            animation = playerAnimation.drightIdling;
        
        if(direction.equals("UP"))
          animation = playerAnimation.dupIdling;  
        
        if(direction.equals("DOWN"))
            animation = playerAnimation.ddownIdling;
    }
    
    private void processKeys () {
        
        if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.W)){
            direction = "LEFT";
          bounds.x =  pos.x -= Math.sin(Math.toRadians(45))*2;
          bounds.y=  pos.y += Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingLeftAnima;
            

        }
                
        else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.W)){
            direction = "RIGHT";
          bounds.x =  pos.x += Math.sin(Math.toRadians(45))*2;
          bounds.y=  pos.y += Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingRightAnima;

        }
        
        else if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.S)){
            direction = "LEFT";
           bounds.x= pos.x -= Math.sin(Math.toRadians(45))*2;
          bounds.y =  pos.y -= Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingLeftAnima;

        }
                
        else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.S)){
            direction = "RIGHT";
         bounds.x=   pos.x += Math.sin(Math.toRadians(45))*2;
         bounds.y =   pos.y -= Math.cos(Math.toRadians(45))*2;
            animation = playerAnimation.walkingRightAnima;
        }
    
        else if (Gdx.input.isKeyPressed(Keys.W)) {
            direction  = "UP";
         bounds.y =   pos.y += UP;
            animation = playerAnimation.walkingUpAnima;

        }	
        else if (Gdx.input.isKeyPressed(Keys.A)) {
            direction = "LEFT";
        bounds.x =    pos.x += LEFT;
            animation = playerAnimation.walkingLeftAnima;

        } 
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            direction  = "RIGHT";
         bounds.x =   pos.x += RIGHT;
            animation = playerAnimation.walkingRightAnima;


        } 
        else if(Gdx.input.isKeyPressed(Keys.S)) {
            direction  = "DOWN";
          bounds.y =  pos.y += DOWN;
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
        
//         else{
//             if(direction.equals("LEFT")){
//                animation = attackingFunction.leftIdling;
//            }
//             
//            if(direction.equals("RIGHT")){
//                animation = attackingFunction.rightIdling;
//            }
//             
//            if(direction.equals("UP")){
//                animation = attackingFunction.upIdling;
//            }
//             
//            if(direction.equals("DOWN")){
//                animation = attackingFunction.downIdling;
//            }
//         }
        

        
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

    
}
