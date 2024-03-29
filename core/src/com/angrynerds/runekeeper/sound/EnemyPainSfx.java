/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper.sound;

import com.angrynerds.runekeeper.sound.BgmTrack;
import com.angrynerds.runekeeper.sound.FireAreaMusic;
import com.angrynerds.runekeeper.sound.GrassAreaMusic;
import com.angrynerds.runekeeper.sound.OreAreaMusic;
import com.angrynerds.runekeeper.sound.SoundFile;
import com.angrynerds.runekeeper.sound.StartAreaMusic;
import com.angrynerds.runekeeper.sound.WaterAreaMusic;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.Observable;
import java.util.Observer;

public class EnemyPainSfx {

    private final float DELTA_MAX = 0.3f; //for preventing triggering sound faster than it can play
    private EnemyName currentEnemyType;
    private EnemyName lastEnemyType;
    private float deltaSum = 0;

    public enum EnemyName {
        ORC, GOBLIN, TROLL, GHOST, WIZARD, SNAKE, DEMON, GOBLIN_KING, EVIL_WIZARD, GHOST_KING, SNAKE_KING, METEOR_BEAST
    }

    private SfxTrack sfx;

    public EnemyPainSfx() {
        sfx = new SfxTrack(new DemonPainSfx()); //only exists to reduce adding if statement for null checking
    }

    public void play(String enemyName, float delta) {
        lastEnemyType = currentEnemyType;
        if (enemyName.equals("Demon")) {
            currentEnemyType = EnemyName.DEMON;
        } else if (enemyName.equals("Ghost")) {
            currentEnemyType = EnemyName.GHOST;
        } else if (enemyName.equals("Goblin")) {
            currentEnemyType = EnemyName.GOBLIN;
        } else if (enemyName.equals("Orc")) {
            currentEnemyType = EnemyName.ORC;
        } else if (enemyName.equals("Snake")) {
            currentEnemyType = EnemyName.SNAKE;
        } else if (enemyName.equals("Wizard")) {
            currentEnemyType = EnemyName.WIZARD;
        } else if (enemyName.equals("Ghost King")) {
            currentEnemyType = EnemyName.GHOST_KING;
        } else if (enemyName.equals("Goblin King")) {
            currentEnemyType = EnemyName.GOBLIN_KING;
        } else if (enemyName.equals("Snake King")) {
            currentEnemyType = EnemyName.SNAKE_KING;
        } else if (enemyName.equals("Evil Wizard")) {
            currentEnemyType = EnemyName.EVIL_WIZARD;
        } else if (enemyName.equals("Meteor Beast")) {
            currentEnemyType = EnemyName.METEOR_BEAST;
        }
        //trigger new enemy sound no matter what if changing enemies
        if(lastEnemyType != currentEnemyType){
            deltaSum = DELTA_MAX; //ensures if statement is triggered next
        }
        
        //prevent sound from playing too quickly
        if (deltaSum > DELTA_MAX) {
            deltaSum = 0;
            switch (currentEnemyType) {
                case DEMON:
                    sfx.dispose();
                    sfx = new SfxTrack(new DemonPainSfx());
                    break;
                case GHOST:
                    sfx.dispose();
                    sfx = new SfxTrack(new GhostPainSfx());
                    break;
                case GOBLIN:
                    sfx.dispose();
                    sfx = new SfxTrack(new GoblinPainSfx());
                    break;
                case ORC:
                    sfx.dispose();
                    sfx = new SfxTrack(new OrcPainSfx());
                    break;
                case SNAKE:
                    sfx.dispose();
                    sfx = new SfxTrack(new SnakePainSfx());
                    break;
                case WIZARD:
                    sfx.dispose();
                    sfx = new SfxTrack(new WizardPainSfx());
                    break;
                case GHOST_KING:
                    sfx.dispose();
                    sfx = new SfxTrack(new GhostKingPainSfx());
                    break;
                case GOBLIN_KING:
                    sfx.dispose();
                    sfx = new SfxTrack(new GoblinKingPainSfx());
                    break;
                case SNAKE_KING:
                    sfx.dispose();
                    sfx = new SfxTrack(new SnakeKingPainSfx());
                    break;
                case EVIL_WIZARD:
                    sfx.dispose();
                    sfx = new SfxTrack(new EvilWizardPainSfx());
                    break;
                case METEOR_BEAST:
                    sfx.dispose();
                    sfx = new SfxTrack(new MeteorBeastPainSfx());
                    break;
                default:
                    System.out.println("you should never get here! No sfx played");
                    break;
            }
            sfx.play();
        } else {
            deltaSum += delta;
        }
    }

    public void dispose() {
        sfx.dispose();
    }

}
