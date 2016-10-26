package com.angrynerds.runekeeper.sound;

public class BgmTrack extends AbstractTrack {

    public BgmTrack(SoundFile soundFile) {
        super(soundFile);
        super.loop();
    }
}
