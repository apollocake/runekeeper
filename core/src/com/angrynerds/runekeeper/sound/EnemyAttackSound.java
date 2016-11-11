/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper.sound;

/**
 *
 * @author Christopher Pope
 */
public class EnemyAttackSound {
    private final float DELTA_MAX = 0.3f; //for preventing triggering sound faster than it can play
    private EnemyName currentEnemyType;
    private EnemyName lastEnemyType;
    private final float deltaSum = 0;

    public enum EnemyName {
        ORC, GOBLIN, TROLL, GHOST, WIZARD, SNAKE, DEMON, GOBLIN_KING, EVIL_WIZARD, GHOST_KING, SNAKE_KING, METEOR_BEAST
    }

    private SfxTrack sfx;

    public EnemyAttackSound() {
        sfx = new SfxTrack(new DemonAttackSound()); //only exists to reduce adding if statement for null checking
    }
    
    public void play(){
        sfx.play();
    }
}
