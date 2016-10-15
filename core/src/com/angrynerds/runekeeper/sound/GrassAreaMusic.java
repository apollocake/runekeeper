package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;

public class GrassAreaMusic extends AbstractSoundFile {

    public GrassAreaMusic() {
        super(Gdx.audio.newMusic(Gdx.files.internal("grassarea.mp3")));
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
