/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Noah
 */
public class EntityAnimation {

    private static int walkingFrameCol;
    private static int walkingUpFrameRow;
    private static int walkingLeftFrameRow;
    private static int walkingDownFrameRow;
    private static int walkingRightFrameRow;

    Animation walkingDownAnima;
    Animation walkingUpAnima;
    Animation walkingLeftAnima;
    Animation walkingRightAnima;

    public Animation upIdling;
    public Animation downIdling;
    public Animation leftIdling;
    public Animation rightIdling;
    

    
    public Animation enemyAttack;
    


    Texture   walkingSheet;
    Texture   walkingSheet2;

    TextureRegion[] walkingUpFrames;
    TextureRegion[] walkingDownFrames;
    TextureRegion[] walkingLeftFrames;
    TextureRegion[] walkingRightFrames;
    TextureRegion[][] tmp2;
    public Vector2 pos = new Vector2();
    Animation currentAnimation;

    private String pictureName;
    String pictureName2;
    private int cols, rows;

    public EntityAnimation(int walkingFrameCol, int walkingUpFrameRow, int walkingLeftFrameRow, int walkingDownFrameRow, int walkingRightFrameRow, int cols, int rows, String newPictureName) {
        this.cols = cols;
        this.rows = rows;
            
        this.walkingFrameCol = walkingFrameCol;
        this.walkingUpFrameRow = walkingUpFrameRow;
        this.walkingLeftFrameRow = walkingLeftFrameRow;
        this.walkingDownFrameRow = walkingDownFrameRow;
        this.walkingRightFrameRow = walkingRightFrameRow;
        
        this.pictureName = newPictureName;
        createAnimations();
        currentAnimation = downIdling;
    }

    private void createAnimations() {

        walkingSheet = new Texture(Gdx.files.internal(this.pictureName));
       
        TextureRegion[][] tmp = TextureRegion.split(walkingSheet, walkingSheet.getWidth()/this.cols,
                walkingSheet.getHeight()/this.rows); //There are 4 rows.
        
        
       if(this.pictureName.equals("demon.png") ){
           pictureName2 = "demon2.png";
            walkingSheet2 = new Texture(Gdx.files.internal(this.pictureName2));
              tmp2 = TextureRegion.split(walkingSheet2, walkingSheet2.getWidth()/this.cols,
                walkingSheet2.getHeight()/this.rows);
             enemyAttack  = new Animation(0.025f, tmp2[this.walkingDownFrameRow][0]);
       }
        else if(this.pictureName.equals("orc.png"))
                  enemyAttack = new Animation(0.025f,tmp[3][6]);
        
       else if(this.pictureName.equals("ghost.png") ){
           pictureName2 = "ghost.png";
            walkingSheet2 = new Texture(Gdx.files.internal(this.pictureName2));
              tmp2 = TextureRegion.split(walkingSheet2, walkingSheet2.getWidth()/this.cols,
                walkingSheet2.getHeight()/this.rows);
             enemyAttack  = new Animation(0.025f, tmp2[this.walkingUpFrameRow][0]);
       }
       
       else if(this.pictureName.equals("snake.png") ){
           pictureName2 = "snake2.png";
            walkingSheet2 = new Texture(Gdx.files.internal(this.pictureName2));
              tmp2 = TextureRegion.split(walkingSheet2, walkingSheet2.getWidth()/this.cols,
                walkingSheet2.getHeight()/this.rows);
             enemyAttack  = new Animation(0.025f, tmp2[this.walkingDownFrameRow][0]);
       }
       
       else if(this.pictureName.equals("wizard.png") ){
           pictureName2 = "wizard2.png";
            walkingSheet2 = new Texture(Gdx.files.internal(this.pictureName2));
              tmp2 = TextureRegion.split(walkingSheet2, walkingSheet2.getWidth()/this.cols,
                walkingSheet2.getHeight()/this.rows);
             enemyAttack  = new Animation(0.025f, tmp2[this.walkingDownFrameRow][0]);
       }
       
        
        else enemyAttack = new Animation(0.025f, tmp[this.walkingUpFrameRow][0]);
       
      
       
       System.out.println("jesfahjksdhakjdnh;kdjhfsdkhjgshdkjanhgskjdnhgkjadsflnglksjd");
       System.out.println(pictureName2);

        
        


        walkingUpFrames = new TextureRegion[this.walkingFrameCol-1];
        walkingDownFrames = new TextureRegion[this.walkingFrameCol-1];
        walkingLeftFrames = new TextureRegion[this.walkingFrameCol-1];
        walkingRightFrames = new TextureRegion[this.walkingFrameCol-1];


        //For when the character is in Idle
        upIdling  = new Animation(0.025f, tmp[this.walkingUpFrameRow][0]);
        downIdling  = new Animation(0.025f, tmp[this.walkingDownFrameRow][0]);
        leftIdling  = new Animation(0.025f, tmp[this.walkingLeftFrameRow][0]);
        rightIdling  = new Animation(0.025f, tmp[this.walkingRightFrameRow][0]);
        
    }

    void setLocation(float x, float y) {
        pos.x = x;
        pos.y = y;
    }
}
