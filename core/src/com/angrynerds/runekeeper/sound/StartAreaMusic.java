package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;

public class StartAreaMusic extends AbstractSoundFile {

    public StartAreaMusic() {
        super(Gdx.audio.newMusic(Gdx.files.internal("startarea.mp3")));
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
