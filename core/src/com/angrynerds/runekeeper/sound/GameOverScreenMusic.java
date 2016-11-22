package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;

public class GameOverScreenMusic extends AbstractSoundFile {

    public GameOverScreenMusic() {
        super(Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3")));
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
