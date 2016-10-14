package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class OreAreaMusic extends AbstractSoundFile {

    public OreAreaMusic() {
        super(Gdx.audio.newMusic(Gdx.files.internal("orearea.mp3")));
    }

    @Override
    public void start() {
        music.play();
    }

    @Override
    public void stop() {
        music.stop();
    }

    @Override
    public void dispose() {
        music.stop();
        music.dispose();
    }

    @Override
    public void loop() {
        music.setLooping(true);
    }
}
