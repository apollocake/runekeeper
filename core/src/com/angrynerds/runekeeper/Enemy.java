/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Noah
 */
public class Enemy implements Entity {

    private EnemyPatrol enemyPatrol;
    private EnemyType enemyType;
    private EntityAnimation animation;
    private String enemyName;
    private Vector2 pos;
    private Vector2 dimensions = new Vector2();

    private int boxCounter = 0;
    private int boxCounter2 = 0;
    private int boxCounter3 = 0;
    private int boxCounter4 = 0;
    private boolean bleft = false;
    private boolean bdown = false;
    private boolean bright = false;
    private boolean bup = false;

    private final float MAX_HEALTH = 100;
    private float currentHealth;

    private HealthBar healthBar;
    public boolean alert = false;
    private int lives;
    public String state;
    private Rectangle bounds;
    public boolean isAlive;

    public Enemy(EntityAnimation newAnimation, String newName, float x, float y, DifficultyType difficulty, EnemyPatrol newEnemyPatrol, EnemyType enemyType) {

        pos = new Vector2(x, y);
        lives = 0;
        currentHealth = MAX_HEALTH;
        healthBar = new HealthBar(MAX_HEALTH, x, y);
        this.animation = newAnimation;
        this.enemyName = newName;
        this.enemyType = enemyType;
        bounds = new Rectangle(this.pos.x, this.pos.y, this.getAnimation().getSpriteWidth(), this.getAnimation().getSpriteHeight());
        isAlive = true;

        this.enemyPatrol = newEnemyPatrol;
        difficulty.TransformEntity(this);

    }

    public void damage(int damage) {
        if (damage >= getCurrentHealth()) {
            if (getLives() > 0) {
                lives--;
                setCurrentHealth(MAX_HEALTH - (damage - getCurrentHealth()));
            } else {
                setLives(0);
                getHealthBar().healthBar.setVisible(false);
                setCurrentHealth(0);
                state = "DYING";
                isAlive = false;
            }
        } else {
            setCurrentHealth(getCurrentHealth() - damage);
        }
    }

    @Override
    public String getName() {
        return this.enemyName;
    }

    @Override
    public void update() {
        healthBar.setHealth(currentHealth);
        patrol(pos);
        bounds.x = pos.x;
        bounds.y = pos.y;
        animation.setLocation(pos.x, pos.y);
        this.healthBar.healthBar.setPosition(this.pos.x, this.pos.y);
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
    
    public void playAttackSound(){
        
    }

    @Override
    public void setPatrol(EnemyPatrol patrolType) {
        this.enemyPatrol = patrolType;
    }

    @Override
    public void setAlert(boolean A) {
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

    /**
     * @return the enemyType
     */
    public EnemyType getEnemyType() {
        return enemyType;
    }

    /**
     * @return the currentHealth
     */
    public float getCurrentHealth() {
        return currentHealth;
    }

    /**
     * @param currentHealth the currentHealth to set
     */
    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * @param lives the lives to set
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * @return the healthBar
     */
    public HealthBar getHealthBar() {
        return healthBar;
    }

    /**
     * @param healthBar the healthBar to set
     */
    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    @Override
    public boolean isAlive() {
      return isAlive;
    }
}
