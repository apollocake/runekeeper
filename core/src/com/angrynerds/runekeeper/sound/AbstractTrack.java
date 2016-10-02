package com.angrynerds.runekeeper.sound;

import com.angrynerds.runekeeper.sound.SoundFile;

public abstract class AbstractTrack {

    protected SoundFile soundFile;

    public AbstractTrack(SoundFile soundFile) {
        this.soundFile = soundFile;
    }

    public void play() {
        soundFile.start();
    }

    public void dispose() {
        soundFile.dispose();
    }

    public void loop() {
        soundFile.loop();
    }

}
