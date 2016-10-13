package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.screens.NewGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

public class Player {

    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    private final int IDLE = 0;
    private final int UP = 2;
    private final int DOWN = -2;
    private final int LEFT = -2;
    private final int RIGHT = 2;

    public PlayerAnimation playerAnimation;
    AttackingFunction attackingFunction;
    public Animation animation;

    public String direction = "DOWN";
    public String state;
    private float stateTime;
    public String attack = "";
    
    
    private HealthBar healthBar;
    private int lives;
    
    
    public Vector2 pos = new Vector2();

    public Rectangle bounds = new Rectangle();

    public Player(float x, float y) {
        lives = 1;
        state = "ALIVE";
        pos.x = x;
        pos.y = y;
        attackingFunction = new AttackingFunction();
        stateTime = 0;
        
        bounds.x = pos.x;
        bounds.y = pos.y;
        bounds.width = 20;
        bounds.height = 20;
        
        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        animation = playerAnimation.downIdling;

    }
    
 
       
    public void update(float deltaTime) {
        if(state.equals("ALIVE")){
            processKeys();
            playerAnimation.setLocation(pos.x, pos.y);
            attackingFunction.setLocation(pos.x, pos.y);
        }        
        else if (state.equals("DYING")) {
            animation = playerAnimation.dyingAnimation;
            stateTime += deltaTime;
            if(stateTime > .4f){
                state = "DEAD";
                attack = "";
            }
        }
        else{//Here the player is DEAD
            animation  = playerAnimation.dead;
        }        
        
        bounds.x = this.pos.x;
        bounds.y = this.pos.y;
    }

    
    
    private void processKeys() {
            if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.W)) {
                direction = "LEFT";
                pos.x -= Math.sin(Math.toRadians(45)) * 2;
                pos.y += Math.cos(Math.toRadians(45)) * 2;
                animation = playerAnimation.walkingLeftAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.W)) {
                direction = "RIGHT";
                pos.x += Math.sin(Math.toRadians(45)) * 2;
                pos.y += Math.cos(Math.toRadians(45)) * 2;
                animation = playerAnimation.walkingRightAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.S)) {
                direction = "LEFT";
                pos.x -= Math.sin(Math.toRadians(45)) * 2;
                pos.y -= Math.cos(Math.toRadians(45)) * 2;
                animation = playerAnimation.walkingLeftAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.S)) {
                direction = "RIGHT";
                pos.x += Math.sin(Math.toRadians(45)) * 2;
                pos.y -= Math.cos(Math.toRadians(45)) * 2;
                animation = playerAnimation.walkingRightAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.W)) {
                direction = "UP";
                pos.y += UP;
                animation = playerAnimation.walkingUpAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.A)) {
                direction = "LEFT";
                pos.x += LEFT;
                animation = playerAnimation.walkingLeftAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.D)) {
                direction = "RIGHT";
                pos.x += RIGHT;
                animation = playerAnimation.walkingRightAnima;
            } 
            else if (Gdx.input.isKeyPressed(Keys.S)) {
                direction = "DOWN";
                pos.y += DOWN;
                animation = playerAnimation.walkingDownAnima;
            }
            else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("DOWN")) {
                animation = attackingFunction.attackingDownAnima;
                attack = "DOWN_ATTACKING";
            }
            else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("UP")) {
                animation = attackingFunction.attackingUpAnima;
                attack = "UP_ATTACKING";
            }
            else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("LEFT")) {
                animation = attackingFunction.attackingLeftAnima;
                attack = "LEFT_ATTACKING";
            }
            else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("RIGHT")) {
                animation = attackingFunction.attackingRightAnima;
                attack = "RIGHT_ATTACKING";    
            } 
            else 
            //X AND Y SHOULD NOT CHANGE
            //This is when the chatcter is in IDLE
            if (direction.equals("LEFT")) {
                animation = playerAnimation.leftIdling;
                attack = "";
            } 
            else if (direction.equals("RIGHT")) {
                animation = playerAnimation.rightIdling;
                attack = "";
            } 
            else if (direction.equals("UP")) {
                animation = playerAnimation.upIdling;
                attack = "";
            } 
            else if (direction.equals("DOWN")) {
                animation = playerAnimation.downIdling;
                attack = "";
            }
    }
    
    
    public void setHealthBar(HealthBar healthBar){
        this.healthBar = healthBar;
    }
    
    public int getPlayerLives(){
        return lives;
    }
  
    public void addPlayerLives(int life){
        this.lives += life;
    }
    
    public void damage(float damage){
        float health = healthBar.getHealth();
        float temp = 0;
        if( damage >= health)
        {
            if(lives > 0){
                addPlayerLives(-1);
                healthBar.setHealth( (healthBar.MAXHEALTH - (damage - health)));
            }
            else{
                lives = 0;
                healthBar.healthBar.setVisible(false);
                state = "DYING";
            }  
        }
        else {
          temp = health - damage;
          healthBar.setHealth(temp);
        }
         
    }
    
    // method to add health to the healthBar
    public void addhealth(float addHealth){
         float temp = 0;
         if((addHealth + healthBar.getHealth()) >= healthBar.MAXHEALTH){
             addPlayerLives(1);
             healthBar.setHealth(((addHealth + healthBar.getHealth())-healthBar.MAXHEALTH));
         }
         else{
             healthBar.setHealth((healthBar.getHealth() + addHealth));
         }
    } 

}
