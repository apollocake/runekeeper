/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Noah
 */
public class Enemy implements Entity{
    private EntityAnimation animation;
    private String enemyName;

    private Vector2 pos = new Vector2();
    
    private int boxCounter = 0;
    private int boxCounter2 = 0;
    private int boxCounter3 = 0;
    private int boxCounter4 = 0;
    private boolean bleft = false;
    private boolean bdown = false;
    private boolean bright = false;
    private boolean bup = false;

    public Enemy(EntityAnimation newAnimation, String newName, float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
        this.animation = newAnimation;
        this.enemyName = newName;
    }

    @Override
    public String getName() {
        return this.enemyName;
    }

    @Override
    public void update() {
        
        patrol();
        animation.setLocation(pos.x, pos.y);
    }

    @Override
    public EntityAnimation getAnimation() {
        return this.animation;
    }

    @Override
    public Vector2 getPosition() {
        return this.pos;
    }
    
    private void patrol(){
        if(boxCounter<100){
           pos.x --;
           
           boxCounter++;
        }
        if(boxCounter>=100){
            bleft = true;
        }
        if(bleft == true && boxCounter2<100){
           pos.y --;
           
           boxCounter2++;
        }
        if(boxCounter2>=100){
            bdown = true;
        }
        if(bdown == true && boxCounter3<100){
           pos.x ++;
           
           boxCounter3++;
        }
        if(boxCounter3>=100){
            bright = true;
        }
        if(bright == true && boxCounter4<100){
           pos.y ++;
           
           boxCounter4++;
        }
        if(boxCounter4>=100){
            bup = true;
        }
        if(bleft == true && bright == true && bup == true && bdown == true){
            boxCounter = 0;
            boxCounter2 = 0;
            boxCounter3 = 0;
            boxCounter4 = 0;
            
            bleft = false;
            bright = false;
            bup = false;
            bdown = false;
        }
    }

}
