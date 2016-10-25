package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.Observable;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Observable {

    //DEFINED FOR THE CORRESPONDING ANIMATIONS
    public final int IDLE = 0;
    public final int UP = 2;
    public final int DOWN = -2;
    public final int LEFT = -2;
    public final int RIGHT = 2;
    public final int RUNE_FOR_GLOVE_X = 30;
    public final int RUNE_FOR_GLOVE_Y = 5;
    public final int RUNE_FOR_SWORD_X = 130;
    public final int RUNE_FOR_SWORD_Y = 5;

    boolean runeFireGlove = false;
    boolean runeWaterGlove = false;
    boolean runeGrassGlove = false;
    boolean runeOreGlove = false;
    boolean runeFireSword = false;
    boolean runeWaterSword = false;
    boolean runeGrassSword = false;
    boolean runeOreSword = false;

    public PlayerAnimation playerAnimation;
    AttackingFunction attackingFunction;
    Glove glove = new Glove();
    Sword sword = new Sword();
    Rune rnFire = new Rune();
    Rune rnWater = new Rune();
    Rune rnGrass = new Rune();
    Rune rnOre = new Rune();
    public Animation animation;
    public String state;
    public float stateTime;
    ColorAction coloraction = new ColorAction();

    public String direction = "DOWN";
    public String attack = "";
    public Rectangle bounds = new Rectangle();
    public Vector2 pos = new Vector2();

    private int lives;
    private final float MAX_HEALTH = 100;
    private float currentHealth = MAX_HEALTH;

    private HealthBar healthBar = new HealthBar(MAX_HEALTH);
    private boolean startDying = true;
    private boolean startDying2 = true;

    public Player(float x, float y) {

        lives = 100;
        state = "ALIVE";
        pos.x = x;
        pos.y = y;
        rnFire.setRune(new RuneFire());
        rnWater.setRune(new RuneWater());
        rnGrass.setRune(new RuneGrass());
        rnOre.setRune(new RuneOre());
        attackingFunction = new AttackingFunction();

        stateTime = 0;

        bounds.width = 20;
        bounds.height = 20;

        bounds.x = pos.x;
        bounds.y = pos.y;

        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        animation = playerAnimation.downIdling;
        //glove.draw(batch);
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

    public int getPlayerLives() {
        return lives;
    }

    public void addPlayerLives(int life) {
        this.lives += life;
    }

    public void damage(float damage) {
        if (damage >= currentHealth) {
            if (lives > 0) {
                addPlayerLives(-1);
                currentHealth = (MAX_HEALTH - (damage - currentHealth));
                 animation = playerAnimation.lostLife;
            } else {
                lives = 0;
                healthBar.healthBar.setVisible(false);
                currentHealth = 0;
                state = "DYING";
            }
        } else {
            currentHealth -= damage;
        }

    }

    // method to add health to the healthBar
    public void addhealth(float addHealth) {
        if ((addHealth + currentHealth) >= MAX_HEALTH) {
            addPlayerLives(1);
            currentHealth = ((addHealth + currentHealth) - MAX_HEALTH);
        } else {
            currentHealth += addHealth;
        }
    }

    public void update(float deltaTime, SpriteBatch batch) {
        healthBar.setHealth(currentHealth);
        stateTime += deltaTime;
        if (state.equals("ALIVE")) {
            processKeys();
        } else if (state.equals("DYING")) {
            if (startDying) {
                startDying = false;
                animation = playerAnimation.dyingAnimation;
                stateTime = 0;
            } else if (animation.isAnimationFinished(stateTime) && startDying2) {
                animation = playerAnimation.dead;
                attack = "";
                startDying2 = false;
                stateTime = 0;
            } else if (animation.isAnimationFinished(stateTime)) {
                state = "DEAD";
            }
        }
            bounds.x = this.pos.x;
            bounds.y = this.pos.y;
            runePocessing(batch);
            glove.draw(batch);
            sword.draw(batch);
    }

    //show the correct animation when player is hit
    public void isHit() {
        if (direction.equals("LEFT")) {
            animation = playerAnimation.dleftIdling;
        }

        if (direction.equals("RIGHT")) {
            animation = playerAnimation.drightIdling;
        }

        if (direction.equals("UP")) {
            animation = playerAnimation.dupIdling;
        }

        if (direction.equals("DOWN")) {
            animation = playerAnimation.ddownIdling;
        }

    }

    private void processKeys() {
        if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
            runeFireGlove = false;
            runeWaterGlove = false;
            runeGrassGlove = false;
            runeOreGlove = false;
            runeFireSword = false;
            runeWaterSword = false;
            runeGrassSword = false;
            runeOreSword = false;
        }

        //Glove
        if (Gdx.input.isKeyPressed(Keys.NUM_1) && Gdx.input.isKeyPressed(Keys.K)) {
            //rnOne.use(batch);
            runeFireGlove = true;
            runeWaterGlove = false;
            runeGrassGlove = false;
            runeOreGlove = false;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_2) && Gdx.input.isKeyPressed(Keys.K)) {
            runeFireGlove = false;
            runeWaterGlove = true;
            runeGrassGlove = false;
            runeOreGlove = false;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_3) && Gdx.input.isKeyPressed(Keys.K)) {
            runeFireGlove = false;
            runeWaterGlove = false;
            runeGrassGlove = true;
            runeOreGlove = false;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_4) && Gdx.input.isKeyPressed(Keys.K)) {
            runeFireGlove = false;
            runeWaterGlove = false;
            runeGrassGlove = false;
            runeOreGlove = true;
        }

        //Sword
        if (Gdx.input.isKeyPressed(Keys.NUM_1) && Gdx.input.isKeyPressed(Keys.L)) {
            //rnOne.use(batch);
            runeFireSword = true;
            runeWaterSword = false;
            runeGrassSword = false;
            runeOreSword = false;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_2) && Gdx.input.isKeyPressed(Keys.L)) {
            runeFireSword = false;
            runeWaterSword = true;
            runeGrassSword = false;
            runeOreSword = false;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_3) && Gdx.input.isKeyPressed(Keys.L)) {
            runeFireSword = false;
            runeWaterSword = false;
            runeGrassSword = true;
            runeOreSword = false;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_4) && Gdx.input.isKeyPressed(Keys.L)) {
            runeFireSword = false;
            runeWaterSword = false;
            runeGrassSword = false;
            runeOreSword = true;
        }

        if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.W)) {
            direction = "LEFT";

            notifyObservers();
            bounds.x = pos.x -= Math.sin(Math.toRadians(45)) * 2;
            bounds.y = pos.y += Math.cos(Math.toRadians(45)) * 2;
            animation = playerAnimation.walkingLeftAnima;

        } else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.W)) {
            direction = "RIGHT";

            notifyObservers();

            bounds.x = pos.x += Math.sin(Math.toRadians(45)) * 2;
            bounds.y = pos.y += Math.cos(Math.toRadians(45)) * 2;

            animation = playerAnimation.walkingRightAnima;

        } else if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.S)) {
            direction = "LEFT";
            notifyObservers();
            bounds.x = pos.x -= Math.sin(Math.toRadians(45)) * 2;
            bounds.y = pos.y -= Math.cos(Math.toRadians(45)) * 2;
            animation = playerAnimation.walkingLeftAnima;

        } else if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.S)) {
            direction = "RIGHT";
            notifyObservers();
            bounds.x = pos.x += Math.sin(Math.toRadians(45)) * 2;
            bounds.y = pos.y -= Math.cos(Math.toRadians(45)) * 2;
            animation = playerAnimation.walkingRightAnima;
        } else if (Gdx.input.isKeyPressed(Keys.W)) {
            direction = "UP";
            notifyObservers();
            bounds.y = pos.y += UP;
            animation = playerAnimation.walkingUpAnima;

        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            direction = "LEFT";

            notifyObservers();
            bounds.x = pos.x += LEFT;

            animation = playerAnimation.walkingLeftAnima;

        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            direction = "RIGHT";

            notifyObservers();

            bounds.x = pos.x += RIGHT;

            animation = playerAnimation.walkingRightAnima;

        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            direction = "DOWN";
            notifyObservers();
            bounds.y = pos.y += DOWN;
            animation = playerAnimation.walkingDownAnima;

        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("DOWN")) {
            animation = attackingFunction.attackingDownAnima;
            attack = "DOWN_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("UP")) {
            animation = attackingFunction.attackingUpAnima;
            attack = "UP_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("LEFT")) {
            animation = attackingFunction.attackingLeftAnima;
            attack = "LEFT_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("RIGHT")) {
            animation = attackingFunction.attackingRightAnima;
            attack = "RIGHT_ATTACKING";
            notifyObservers();
        } else //X AND Y SHOULD NOT CHANGE
        //This is when the chatcter is in IDLE
        {
            if (direction.equals("LEFT")) {
                animation = playerAnimation.leftIdling;
                attack = "";
            } else if (direction.equals("RIGHT")) {
                animation = playerAnimation.rightIdling;
                attack = "";
            } else if (direction.equals("UP")) {
                animation = playerAnimation.upIdling;
                attack = "";
            } else if (direction.equals("DOWN")) {
                animation = playerAnimation.downIdling;
                attack = "";
            } else if (attack.equals("DOWN_ATTACKING")) {
                animation = attackingFunction.downIdling;
            } else if (attack.equals("UP_ATTACKING")) {
                animation = attackingFunction.upIdling;
            } else if (attack.equals("LEFT_ATTACKING")) {
                animation = attackingFunction.leftIdling;
            } else if (attack.equals("RIGHT_ATTACKING")) {
                animation = attackingFunction.rightIdling;
            }
        }
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setX(float x) {
        pos.x = x;
    }

    public void setY(float y) {
        pos.y = y;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();

    }

    private void runePocessing(SpriteBatch batch) {
        if (runeFireGlove == true) {
            rnFire.use(batch, RUNE_FOR_GLOVE_X, RUNE_FOR_GLOVE_Y);
        } else if (runeWaterGlove == true) {
            rnWater.use(batch, RUNE_FOR_GLOVE_X, RUNE_FOR_GLOVE_Y);
        } else if (runeGrassGlove == true) {
            rnGrass.use(batch, RUNE_FOR_GLOVE_X, RUNE_FOR_GLOVE_Y);
        } else if (runeOreGlove == true) {
            rnOre.use(batch, RUNE_FOR_GLOVE_X, RUNE_FOR_GLOVE_Y);
        }

        if (runeFireSword == true) {
            rnFire.use(batch, RUNE_FOR_SWORD_X, RUNE_FOR_SWORD_Y);
        } else if (runeWaterSword == true) {
            rnWater.use(batch, RUNE_FOR_SWORD_X, RUNE_FOR_SWORD_Y);
        } else if (runeGrassSword == true) {
            rnGrass.use(batch, RUNE_FOR_SWORD_X, RUNE_FOR_SWORD_Y);
        } else if (runeOreSword == true) {
            rnOre.use(batch, RUNE_FOR_SWORD_X, RUNE_FOR_SWORD_Y);
        }
    }

    public Vector2 getPosition() {
        return this.pos;
    }

}
