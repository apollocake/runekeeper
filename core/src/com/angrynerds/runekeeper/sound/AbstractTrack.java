package com.angrynerds.runekeeper.sound;
/**
 *Implementation agnostic abstract class that blindly uses a SoundFile interface. For example, to set
 * up a background music track for playing, fireArea =  new BgmTrack(new FireAreaMusic()). Then call
 * fireArea.play() or fireArea.pause() or fireArea.dispose()...etc
 */
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
