
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


class PlayerAnimation{
    
    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    public final int IDLE = 0; 
    public final int UP = -1;
    public final int DOWN = 1;
    public final int LEFT = -1;
    public final int RIGHT = 1;
    
    //Based off the playerWlaking.png he assets folder
    private static final int WALKING_FRAME_COLS= 9;
    private static final int WALKING_UP_FRAME_ROW = 0; 
    private static final int WALKING_LEFT_FRAME_ROW = 1;        
    private static final int WALKING_DOWN_FRAME_ROW = 2;        
    private static final int WALKING_RIGHT_FRAME_ROW = 3;        


    Animation walkingDownAnima; 
    Animation walkingUpAnima;          
    Animation walkingLeftAnima;          
    Animation walkingRightAnima; 
        
    Animation upIdling;
    Animation downIdling;
    Animation leftIdling;
    Animation rightIdling;

    Texture   walkingSheet;             
    TextureRegion[] walkingUpFrames;             
    TextureRegion[] walkingDownFrames;             
    TextureRegion[] walkingLeftFrames;             
    TextureRegion[] walkingRightFrames; 
    
    public Vector2 pos = new Vector2();
    int direction = 0;
    boolean moving = false;
    Animation currentAnimation;
    

    
    public PlayerAnimation(float x, float y) {
        createAnimations();
        currentAnimation = downIdling;
    }

    void updateAnimation() {
        if(direction == LEFT){
            if(moving){
                currentAnimation = walkingLeftAnima;
            }
            else{
                currentAnimation  = leftIdling;
            }
        }
                
        if(direction == RIGHT){
            if(moving){
                currentAnimation = walkingRightAnima;
            }
            else{
                currentAnimation  = rightIdling;
            }
        }
                
        if(direction == UP){
            if(moving){
                currentAnimation = walkingUpAnima;
            }
            else{
                currentAnimation  = upIdling;
            }
        }
        
        if(direction == DOWN){
            if(moving){
                currentAnimation = walkingDownAnima;
            }
            else{
                currentAnimation  = downIdling;
            }
        }
        if(direction == IDLE){
            
        }
        
    }
    

    private void createAnimations() {
                
        walkingSheet = new Texture(Gdx.files.internal("playerWalking.png"));
        
        TextureRegion[][] tmp = TextureRegion.split(walkingSheet, walkingSheet.getWidth()/WALKING_FRAME_COLS, 
                walkingSheet.getHeight()/4); //There are 4 rows.

        
        walkingUpFrames = new TextureRegion[WALKING_FRAME_COLS];
        walkingDownFrames = new TextureRegion[WALKING_FRAME_COLS];
        walkingLeftFrames = new TextureRegion[WALKING_FRAME_COLS];
        walkingRightFrames = new TextureRegion[WALKING_FRAME_COLS];
        
                
        //For when the character is in Idle                           
        upIdling  = new Animation(0.025f, tmp[WALKING_UP_FRAME_ROW][0]);
        downIdling  = new Animation(0.025f, tmp[WALKING_DOWN_FRAME_ROW][0]);
        leftIdling  = new Animation(0.025f, tmp[WALKING_LEFT_FRAME_ROW][0]);
        rightIdling  = new Animation(0.025f, tmp[WALKING_RIGHT_FRAME_ROW][0]);
        
        //For the walking animations
        //j=1 so the idle isn't included
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingUpFrames[i++] = tmp[WALKING_UP_FRAME_ROW][j];  
        }        
        walkingUpAnima = new Animation(0.025f, walkingUpFrames);
                
        
        
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingLeftFrames[i++] = tmp[WALKING_LEFT_FRAME_ROW][j];  
        }        
        walkingLeftAnima = new Animation(0.025f, walkingLeftFrames);
        
        
        
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingDownFrames[i++] = tmp[WALKING_DOWN_FRAME_ROW][j];  
        }        
        walkingDownAnima = new Animation(0.025f, walkingDownFrames);

        
        
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingRightFrames[i++] = tmp[WALKING_RIGHT_FRAME_ROW][j];  
        }
        walkingRightAnima = new Animation(0.025f, walkingRightFrames);

        
    }

    
    public void setDirection(int direction){
        this.direction = direction;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
            
    }

    void setLocation(float x, float y) {
        pos.x = x;
        pos.y = y;
    }
    
 
    
}
