package com.angrynerds.runekeeper.sound;

/**
 *Interface for abstracting the implementation actual sound files that are played
 */
public interface SoundFile {

    public void stop();

    public void start();
    
    public void pause();

    public void loop();

    public void dispose();
}
