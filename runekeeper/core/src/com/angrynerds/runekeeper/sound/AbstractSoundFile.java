package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public abstract class AbstractSoundFile implements SoundFile {

    protected Music music = null;
    protected Sound sound = null;

    public AbstractSoundFile(Music music) {
        this.music = music;
    }

    public AbstractSoundFile(Sound sound) {
        this.sound = sound;
    }

}
