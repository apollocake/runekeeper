package com.angrynerds.runekeeper.sound;

public interface SoundFile {

    public void stop();

    public void start();
    
    public void pause();

    public void loop();

    public void dispose();
}
