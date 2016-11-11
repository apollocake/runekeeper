package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.Rune.FireGloveUpAnima;
import com.angrynerds.runekeeper.Rune.FireGloveLeftAnima;
import com.angrynerds.runekeeper.Rune.FireGloveRightAnima;
import com.angrynerds.runekeeper.Rune.FireGloveDownAnima;
import com.angrynerds.runekeeper.Rune.FireSwordAnima;
import com.angrynerds.runekeeper.Rune.GrassSwordLeftAnima;
import com.angrynerds.runekeeper.Rune.GrassSwordRightAnima;
import com.angrynerds.runekeeper.Rune.OreSwordAnima;
import com.angrynerds.runekeeper.Rune.RuneWater;
import com.angrynerds.runekeeper.Rune.RuneGrass;
import com.angrynerds.runekeeper.Rune.RuneOre;
import com.angrynerds.runekeeper.Rune.RuneFire;
import com.angrynerds.runekeeper.Rune.Rune;
import com.angrynerds.runekeeper.Rune.WaterSwordAnima;
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
    int iPos = 0;
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

    //RuneAnimation
    FireGloveRightAnima fireGloveRightAnima;
    FireGloveLeftAnima fireGloveLeftAnima;
    FireGloveUpAnima fireGloveUpAnima;
    FireGloveDownAnima fireGloveDownAnima;
    WaterGloveAnima waterGloveAnima;
    GrassGloveAnima grassGloveAnima;
    OreGloveAnima oreGloveAnima;
    
    FireSwordAnima fireSwordAnima;
    WaterSwordAnima waterSwordAnima;
    GrassSwordLeftAnima grassSwordLeftAnima;
    GrassSwordRightAnima grassSwordRightAnima;
    OreSwordAnima oreSwordAnima;
    
    int waterSwordAnimaPosRightX = 0;
    int waterSwordAnimaPosLeftX = 0;
    int waterSwordAnimaPosUpY = 0;
    int waterSwordAnimaPosDownY = 0;
    int oreSwordAnimaHight = 18;
    int oreSwordAnimaW = 16;
    
    Glove glove = new Glove();
    Sword sword = new Sword();
    Rune rnFire = new Rune();
    Rune rnWater = new Rune();
    Rune rnGrass = new Rune();
    Rune rnOre = new Rune();

    public Animation animation;
    public Animation gloveRuneAnimation;
    public String state;
    public float stateTime;
    public float stateTimeMagic;
    ColorAction coloraction = new ColorAction();

    public String direction = "DOWN";
    public String attack = "";
    public String attackWeapon = "SWORD";
    private int attackPower;
    private Buff currentBuff;
    public Rectangle bounds = new Rectangle();
    public Vector2 pos = new Vector2();

    private int lives;
    private final float MAX_HEALTH = 100;
    private float currentHealth = MAX_HEALTH;

    private HealthBar healthBar = new HealthBar(MAX_HEALTH);
    private boolean startDying = true;
    private boolean startDying2 = true;

    public Player(float x, float y) {

        this.currentBuff = new DefaultBuff();
        this.attackPower = 0;
        lives = 1;
        state = "ALIVE";
        pos.x = x;
        pos.y = y;
        healthBar = new HealthBar(MAX_HEALTH, x, y);
        rnFire.setRune(new RuneFire());
        rnWater.setRune(new RuneWater());
        rnGrass.setRune(new RuneGrass());
        rnOre.setRune(new RuneOre());
        attackingFunction = new AttackingFunction();

        fireGloveRightAnima = new FireGloveRightAnima();
        fireGloveLeftAnima = new FireGloveLeftAnima();
        fireGloveUpAnima = new FireGloveUpAnima();
        fireGloveDownAnima = new FireGloveDownAnima();
        waterGloveAnima = new WaterGloveAnima();
        grassGloveAnima = new GrassGloveAnima();
        oreGloveAnima = new OreGloveAnima();
        
        fireSwordAnima = new FireSwordAnima();
        waterSwordAnima = new WaterSwordAnima();
        grassSwordLeftAnima = new GrassSwordLeftAnima();
        grassSwordRightAnima = new GrassSwordRightAnima();
        oreSwordAnima = new OreSwordAnima();

        stateTime = 0;

        bounds.width = 20;
        bounds.height = 20;

        bounds.x = pos.x;
        bounds.y = pos.y;

        playerAnimation = new PlayerAnimation(pos.x, pos.y);
        animation = playerAnimation.downIdling;
        //gloveRuneAnimation = fireGloveRightAnima.rightIdling;
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
        if (damage >= getCurrentHealth()) {
            if (lives > 0) {
                addPlayerLives(-1);
                setCurrentHealth(MAX_HEALTH - (damage - getCurrentHealth()));
                animation = playerAnimation.lostLife;
            } else {
                lives = 0;
                healthBar.healthBar.setVisible(false);
                setCurrentHealth(0);
                state = "DYING";
            }
        } else {
            setCurrentHealth(getCurrentHealth() - damage);
        }

    }

    // method to add health to the healthBar
    public void addhealth(float addHealth) {
        if ((addHealth + getCurrentHealth()) >= MAX_HEALTH) {
            addPlayerLives(1);
            setCurrentHealth((addHealth + getCurrentHealth()) - MAX_HEALTH);
        } else {
            setCurrentHealth(getCurrentHealth() + addHealth);
        }
    }

    public void update(float deltaTime, SpriteBatch batch) {
        healthBar.setHealth(getCurrentHealth());
        GameStates.gsHealth = currentHealth;
        stateTime += deltaTime;
        if (state.equals("ALIVE")) {
            processKeys(batch);
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
        glove.draw(batch, (int) pos.x - Gdx.graphics.getWidth() / 4 - 40, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        sword.draw(batch, (int) pos.x - Gdx.graphics.getWidth() / 6 - 40, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
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

    private void processKeys(SpriteBatch batch) {
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

        if (Gdx.input.isKeyPressed(Keys.K)) {
            attackWeapon = "GLOVE";
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            attackWeapon = "SWORD";
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
        // end rune

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

        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("DOWN") && attackWeapon.equals("SWORD")) {
            animation = attackingFunction.attackingDownAnima;
            attack = "DOWN_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("UP") && attackWeapon.equals("SWORD")) {
            animation = attackingFunction.attackingUpAnima;
            attack = "UP_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("LEFT") && attackWeapon.equals("SWORD")) {
            animation = attackingFunction.attackingLeftAnima;
            attack = "LEFT_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("RIGHT") && attackWeapon.equals("SWORD")) {
            animation = attackingFunction.attackingRightAnima;
            attack = "RIGHT_ATTACKING";
            notifyObservers();
        } //
        else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("DOWN") && attackWeapon.equals("GLOVE")) {
            animation = animation = playerAnimation.walkingDownAnima;
            attack = "DOWN_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("UP") && attackWeapon.equals("GLOVE")) {
            animation = animation = playerAnimation.walkingUpAnima;
            attack = "UP_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("LEFT") && attackWeapon.equals("GLOVE")) {
            animation = playerAnimation.walkingLeftAnima;
            attack = "LEFT_ATTACKING";
            notifyObservers();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && direction.equals("RIGHT") && attackWeapon.equals("GLOVE")) {
            animation = playerAnimation.walkingRightAnima;
            attack = "RIGHT_ATTACKING";
            notifyObservers();
        } //
        else //X AND Y SHOULD NOT CHANGE
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
            } else if (attack.equals("DOWN_ATTACKING") && attackWeapon.equals("SWORD")) {
                animation = attackingFunction.downIdling;
            } else if (attack.equals("UP_ATTACKING") && attackWeapon.equals("SWORD")) {
                animation = attackingFunction.upIdling;
            } else if (attack.equals("LEFT_ATTACKING") && attackWeapon.equals("SWORD")) {
                animation = attackingFunction.leftIdling;
            } else if (attack.equals("RIGHT_ATTACKING") && attackWeapon.equals("SWORD")) {
                animation = attackingFunction.rightIdling;
            } else if (attack.equals("DOWN_ATTACKING") && attackWeapon.equals("GLOVE")) {
                animation = playerAnimation.downIdling;
            } else if (attack.equals("UP_ATTACKING") && attackWeapon.equals("GLOVE")) {
                animation = playerAnimation.upIdling;
            } else if (attack.equals("LEFT_ATTACKING") && attackWeapon.equals("GLOVE")) {
                animation = playerAnimation.leftIdling;
            } else if (attack.equals("RIGHT_ATTACKING") && attackWeapon.equals("GLOVE")) {
                animation = playerAnimation.rightIdling;
            }
        }

        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireGlove == true && attackWeapon.equals("GLOVE") && attack.equals("RIGHT_ATTACKING")) {
            fireGloveRightAnima.showFireAnimation(batch, (int) pos.x + 40, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireGlove == true && attackWeapon.equals("GLOVE") && attack.equals("LEFT_ATTACKING")) {
            fireGloveLeftAnima.showFireAnimation(batch, (int) pos.x - 105, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireGlove == true && attackWeapon.equals("GLOVE") && attack.equals("UP_ATTACKING")) {
            fireGloveUpAnima.showFireAnimation(batch, (int) pos.x - 33, (int) pos.y + 10);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireGlove == true && attackWeapon.equals("GLOVE") && attack.equals("DOWN_ATTACKING")) {
            fireGloveDownAnima.showFireAnimation(batch, (int) pos.x - 32, (int) pos.y - 100);
        }
        // water
        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterGlove == true && attackWeapon.equals("GLOVE") && attack.equals("RIGHT_ATTACKING")) {
            waterGloveAnima.showFireAnimation(batch, (int) pos.x + 35, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterGlove == true && attackWeapon.equals("GLOVE") && attack.equals("LEFT_ATTACKING")) {
            waterGloveAnima.showFireAnimation(batch, (int) pos.x - 100, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterGlove == true && attackWeapon.equals("GLOVE") && attack.equals("UP_ATTACKING")) {
            waterGloveAnima.showFireAnimation(batch, (int) pos.x - 30, (int) pos.y + 35);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterGlove == true && attackWeapon.equals("GLOVE") && attack.equals("DOWN_ATTACKING")) {
            waterGloveAnima.showFireAnimation(batch, (int) pos.x - 30, (int) pos.y - 70);
        }
        //Grass
        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassGlove == true && attackWeapon.equals("GLOVE") && attack.equals("RIGHT_ATTACKING")) {
            grassGloveAnima.showFireAnimation(batch, (int) pos.x + 10, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassGlove == true && attackWeapon.equals("GLOVE") && attack.equals("LEFT_ATTACKING")) {
            grassGloveAnima.showFireAnimation(batch, (int) pos.x - 50, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassGlove == true && attackWeapon.equals("GLOVE") && attack.equals("UP_ATTACKING")) {
            grassGloveAnima.showFireAnimation(batch, (int) pos.x - 30, (int) pos.y + 5);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassGlove == true && attackWeapon.equals("GLOVE") && attack.equals("DOWN_ATTACKING")) {
            grassGloveAnima.showFireAnimation(batch, (int) pos.x - 30, (int) pos.y - 70);
        }

        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreGlove == true && attackWeapon.equals("GLOVE") && attack.equals("RIGHT_ATTACKING")) {
            oreGloveAnima.showFireAnimation(batch, (int) pos.x + 30, (int) pos.y);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreGlove == true && attackWeapon.equals("GLOVE") && attack.equals("LEFT_ATTACKING")) {
            oreGloveAnima.showFireAnimation(batch, (int) pos.x - 90, (int) pos.y);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreGlove == true && attackWeapon.equals("GLOVE") && attack.equals("UP_ATTACKING")) {
            oreGloveAnima.showFireAnimation(batch, (int) pos.x - 40, (int) pos.y + 70);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreGlove == true && attackWeapon.equals("GLOVE") && attack.equals("DOWN_ATTACKING")) {
            oreGloveAnima.showFireAnimation(batch, (int) pos.x - 40, (int) pos.y - 60);
        }
        
        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireSword == true && attackWeapon.equals("SWORD") && attack.equals("RIGHT_ATTACKING")) {
            fireSwordAnima.showFireAnimation(batch, (int) pos.x - 170 , (int) pos.y - 100);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireSword == true && attackWeapon.equals("SWORD") && attack.equals("LEFT_ATTACKING")) {
            fireSwordAnima.showFireAnimation(batch, (int) pos.x - 170 , (int) pos.y - 100);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireSword == true && attackWeapon.equals("SWORD") && attack.equals("UP_ATTACKING")) {
            fireSwordAnima.showFireAnimation(batch, (int) pos.x - 170 , (int) pos.y - 100);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeFireSword == true && attackWeapon.equals("SWORD") && attack.equals("DOWN_ATTACKING")) {
            fireSwordAnima.showFireAnimation(batch, (int) pos.x - 170 , (int) pos.y - 100);
        }

        
        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterSword == true && attackWeapon.equals("SWORD") && attack.equals("RIGHT_ATTACKING")) {
            if(waterSwordAnima.getisAnimaFinished() == false ){
            waterSwordAnima.showFireAnimation(batch, (int) pos.x + 40 + waterSwordAnimaPosRightX++, (int) pos.y);
            }
            //System.out.println("i " + iPos);
            if(waterSwordAnima.getisAnimaFinished() == true){
                waterSwordAnimaPosRightX = 0;
            }
        }
        else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterSword == true && attackWeapon.equals("SWORD") && attack.equals("LEFT_ATTACKING")) {
            if(waterSwordAnima.getisAnimaFinished() == false){
            waterSwordAnima.showFireAnimation(batch, (int) pos.x - 80 - waterSwordAnimaPosLeftX++ , (int) pos.y);
            }
            if(waterSwordAnima.getisAnimaFinished() == true){
                waterSwordAnimaPosLeftX = 0;
            }
            
        } 
        else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterSword == true && attackWeapon.equals("SWORD") && attack.equals("UP_ATTACKING")) {
            if(waterSwordAnima.getisAnimaFinished() == false){
            waterSwordAnima.showFireAnimation(batch, (int) pos.x - 20 , (int) pos.y + 50 + waterSwordAnimaPosUpY++ );
            }
            if(waterSwordAnima.getisAnimaFinished() == true){
                waterSwordAnimaPosUpY = 0;
            }
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeWaterSword == true && attackWeapon.equals("SWORD") && attack.equals("DOWN_ATTACKING")) {
            if(waterSwordAnima.getisAnimaFinished() == false){
            waterSwordAnima.showFireAnimation(batch, (int) pos.x - 20, (int) pos.y - 60 - waterSwordAnimaPosDownY++);
            }
            if(waterSwordAnima.getisAnimaFinished() == true){
                waterSwordAnimaPosDownY = 0;
            }
        }
        
       
        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassSword == true && attackWeapon.equals("SWORD") && attack.equals("RIGHT_ATTACKING")) {
            grassSwordRightAnima.showFireAnimation(batch, (int) pos.x + 45, (int) pos.y - 15);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassSword == true && attackWeapon.equals("SWORD") && attack.equals("LEFT_ATTACKING")) {
            grassSwordLeftAnima.showFireAnimation(batch, (int) pos.x - 65, (int) pos.y - 30);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassSword == true && attackWeapon.equals("SWORD") && attack.equals("UP_ATTACKING")) {
            grassSwordLeftAnima.showFireAnimation(batch, (int) pos.x - 30, (int) pos.y + 5);
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeGrassSword == true && attackWeapon.equals("SWORD") && attack.equals("DOWN_ATTACKING")) {
            grassSwordLeftAnima.showFireAnimation(batch, (int) pos.x - 30, (int) pos.y - 70);
        }
        
        if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreSword == true && attackWeapon.equals("SWORD") && attack.equals("RIGHT_ATTACKING")) {
            if(oreSwordAnima.getisAnimaFinished() == false ){
                oreSwordAnima.showFireAnimation(batch, (int) pos.x + 55, (int) pos.y - 40, Gdx.graphics.getWidth() / oreSwordAnimaW--, Gdx.graphics.getHeight() / oreSwordAnimaHight--);
               
            //waterSwordAnima.showFireAnimation(batch, (int) pos.x + 40 + waterSwordAnimaPosRightX++, (int) pos.y);
            }
            if(oreSwordAnimaW == 6){
                    oreSwordAnimaW = 7;
                }
                 if(oreSwordAnimaHight == 4){
                    oreSwordAnimaHight = 5;
                }
            //System.out.println("i " + iPos);
            if(oreSwordAnima.getisAnimaFinished() == true){
                oreSwordAnimaW = 16;
                oreSwordAnimaHight = 18;
            }            
        }    
        else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreSword == true && attackWeapon.equals("SWORD") && attack.equals("LEFT_ATTACKING")) {if(oreSwordAnima.getisAnimaFinished() == false ){
                oreSwordAnima.showFireAnimation(batch, (int) pos.x - 135, (int) pos.y - 40, Gdx.graphics.getWidth() / oreSwordAnimaW--, Gdx.graphics.getHeight() / oreSwordAnimaHight--);
               
            //waterSwordAnima.showFireAnimation(batch, (int) pos.x + 40 + waterSwordAnimaPosRightX++, (int) pos.y);
            }
            if(oreSwordAnimaW == 6){
                    oreSwordAnimaW = 7;
                }
                 if(oreSwordAnimaHight == 4){
                    oreSwordAnimaHight = 5;
                }
            //System.out.println("i " + iPos);
            if(oreSwordAnima.getisAnimaFinished() == true){
                oreSwordAnimaW = 16;
                oreSwordAnimaHight = 18;
            }
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreSword == true && attackWeapon.equals("SWORD") && attack.equals("UP_ATTACKING")) {if(oreSwordAnima.getisAnimaFinished() == false ){
                oreSwordAnima.showFireAnimation(batch, (int) pos.x -55, (int) pos.y + 45, Gdx.graphics.getWidth() / oreSwordAnimaW--, Gdx.graphics.getHeight() / oreSwordAnimaHight--);
               
            //waterSwordAnima.showFireAnimation(batch, (int) pos.x + 40 + waterSwordAnimaPosRightX++, (int) pos.y);
            }
            if(oreSwordAnimaW == 6){
                    oreSwordAnimaW = 7;
                }
                 if(oreSwordAnimaHight == 4){
                    oreSwordAnimaHight = 5;
                }
            //System.out.println("i " + iPos);
            if(oreSwordAnima.getisAnimaFinished() == true){
                oreSwordAnimaW = 16;
                oreSwordAnimaHight = 18;
            }
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && runeOreSword == true && attackWeapon.equals("SWORD") && attack.equals("DOWN_ATTACKING")) {if(oreSwordAnima.getisAnimaFinished() == false ){
                oreSwordAnima.showFireAnimation(batch, (int) pos.x -55, (int) pos.y - 120, Gdx.graphics.getWidth() / oreSwordAnimaW--, Gdx.graphics.getHeight() / oreSwordAnimaHight--);
               
            //waterSwordAnima.showFireAnimation(batch, (int) pos.x + 40 + waterSwordAnimaPosRightX++, (int) pos.y);
            }
            if(oreSwordAnimaW == 6){
                    oreSwordAnimaW = 7;
                }
                 if(oreSwordAnimaHight == 4){
                    oreSwordAnimaHight = 5;
                }
            //System.out.println("i " + iPos);
            if(oreSwordAnima.getisAnimaFinished() == true){
                oreSwordAnimaW = 16;
                oreSwordAnimaHight = 18;
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
            rnFire.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 4, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        } else if (runeWaterGlove == true) {
            rnWater.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 4, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        } else if (runeGrassGlove == true) {
            rnGrass.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 4, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        } else if (runeOreGlove == true) {
            rnOre.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 4, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        }

        if (runeFireSword == true) {
            rnFire.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 6, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        } else if (runeWaterSword == true) {
            rnWater.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 6, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        } else if (runeGrassSword == true) {
            rnGrass.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 6, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        } else if (runeOreSword == true) {
            rnOre.use(batch, (int) pos.x - Gdx.graphics.getWidth() / 6, (int) pos.y - Gdx.graphics.getHeight() / 4 - 10);
        }
    }

    public Vector2 getPosition() {
        return this.pos;
    }

    /**
     * @return the attackPower
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * @param attackPower the attackPower to set
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * @return the currentBuff
     */
    public Buff getCurrentBuff() {
        return currentBuff;
    }

    /**
     * @param currentBuff the currentBuff to set
     */
    public void setCurrentBuff(Buff currentBuff) {
        this.currentBuff = currentBuff;
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

}
