package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;

public class WaterAreaMusic extends AbstractSoundFile {

    public WaterAreaMusic() {
        super(Gdx.audio.newMusic(Gdx.files.internal("waterarea.mp3")));
    }

    @Override
    public void start() {
        music.play();
    }
    @Override
    public void pause() {
        music.pause();
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
