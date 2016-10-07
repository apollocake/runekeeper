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
    private EnemyPatrol enemyPatrol;
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
    
   // enemyPatrol = enemyPatrol(new BoxPatrol());

    public Enemy(EntityAnimation newAnimation, String newName, float x, float y, EnemyPatrol newEnemyPatrol) {
        this.pos.x = x;
        this.pos.y = y;
        this.animation = newAnimation;
        this.enemyName = newName;
        this.enemyPatrol = newEnemyPatrol;
    }

    @Override
    public String getName() {
        return this.enemyName;
    }

    @Override
    public void update() {
       // enemyPatrol.patrol();
        patrol(pos);
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

    
    public void patrol(Vector2 pos){
        this.pos = enemyPatrol.patrol(pos);
    }
    public void setPatrol(EnemyPatrol patrolType){
        this.enemyPatrol = patrolType;
    }
}
