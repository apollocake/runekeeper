/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Noah
 */
public class Enemy implements Entity {

    private EnemyPatrol enemyPatrol;
    private EntityAnimation animation;
    private String enemyName;

    private Vector2 pos = new Vector2();
    private Vector2 dimensions = new Vector2();

    private int boxCounter = 0;
    private int boxCounter2 = 0;
    private int boxCounter3 = 0;
    private int boxCounter4 = 0;
    private boolean bleft = false;
    private boolean bdown = false;
    private boolean bright = false;
    private boolean bup = false;

    public boolean alert = false;
    

    public Rectangle bounds = new Rectangle();

    public Enemy(EntityAnimation newAnimation, String newName, float x, float y, DifficultyType difficulty, EnemyPatrol newEnemyPatrol) {

        this.pos.x = x;
        this.pos.y = y;
        this.animation = newAnimation;
        this.enemyName = newName;

        bounds.width = 40;
        bounds.height = 40;

        bounds.x = pos.x;
        bounds.y = pos.y;

        this.enemyPatrol = newEnemyPatrol;
        difficulty.TransformEntity(this);

    }

    @Override
    public String getName() {
        return this.enemyName;
    }

    @Override
    public void update() {
        patrol(pos);
        bounds.x = pos.x;
        bounds.y = pos.y;
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

    @Override
    public Rectangle getRec() {
        return this.bounds;
    }

    private void patrol() {
        if (boxCounter < 100) {
            pos.x--;
            boxCounter++;
        }
        if (boxCounter >= 100) {
            bleft = true;
        }
        if (bleft == true && boxCounter2 < 100) {
            pos.y--;
            boxCounter2++;
        }
        if (boxCounter2 >= 100) {
            bdown = true;
        }
        if (bdown == true && boxCounter3 < 100) {
            pos.x++;
            boxCounter3++;
        }
        if (boxCounter3 >= 100) {
            bright = true;
        }
        if (bright == true && boxCounter4 < 100) {
            pos.y++;

            boxCounter4++;
        }
        if (boxCounter4 >= 100) {
            bup = true;
        }
        if (bleft == true && bright == true && bup == true && bdown == true) {
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

    public void patrol(Vector2 pos) {
        this.pos = enemyPatrol.patrol(pos);
    }

    
    @Override
    public void setPatrol(EnemyPatrol patrolType){
        this.enemyPatrol = patrolType;
    }
    
    @Override
    public void setAlert(boolean A){
        this.alert = A;
    }

    @Override
    public boolean getAlert() {
        return this.alert;
    }

    @Override
    public Vector2 getDimensions() {
        return this.dimensions;
    }

    @Override
    public void setDimensions(Vector2 newDimensions) {
        this.dimensions = newDimensions;

    }
}
