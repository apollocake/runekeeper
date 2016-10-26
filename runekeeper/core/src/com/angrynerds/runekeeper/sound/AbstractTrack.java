package com.angrynerds.runekeeper.sound;

public abstract class AbstractTrack {

    protected SoundFile soundFile;

    public AbstractTrack(SoundFile soundFile) {
        this.soundFile = soundFile;
    }

    public void play() {
        soundFile.start();
    }
    public void pause() {
        soundFile.pause();
    }

    public void dispose() {
        soundFile.dispose();
    }

    public void loop() {
        soundFile.loop();
    }

}
