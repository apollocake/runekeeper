package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;

public class FireAreaMusic extends AbstractSoundFile {

    public FireAreaMusic() {
        super(Gdx.audio.newMusic(Gdx.files.internal("firearea.mp3")));
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
