package com.angrynerds.runekeeper.sound;

import com.angrynerds.runekeeper.MusicCollision;

public class MusicManager {

    private final MusicCollision musicCollision;

    public MusicManager(MusicCollision musicCollision) {
        this.musicCollision = musicCollision;
    }
    public void pause(){
        musicCollision.pause();
    }
    public void play(){
        musicCollision.play();
    }
}
