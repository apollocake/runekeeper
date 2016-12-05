/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Christopher Pope
 */
public class TrollAttackSound extends AbstractSoundFile {
    public TrollAttackSound(){
        super(Gdx.audio.newSound(Gdx.files.internal("trollattack.mp3")));
    }
    
    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void start() {
        sound.play();
    }

    @Override
    public void pause() {
        sound.pause();
    }

    @Override
    public void loop() {
        //do nothing, sfx is one-shot
    }

    @Override
    public void dispose() {
        sound.stop();
        sound.dispose();
    }
    
}
