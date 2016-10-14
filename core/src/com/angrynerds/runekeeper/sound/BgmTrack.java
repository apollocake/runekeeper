package com.angrynerds.runekeeper.sound;

import com.angrynerds.runekeeper.sound.AbstractTrack;
import com.angrynerds.runekeeper.sound.SoundFile;

public class BgmTrack extends AbstractTrack {

    public BgmTrack(SoundFile soundFile) {
        super(soundFile);
        super.loop();
    }
}
