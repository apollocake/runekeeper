package com.angrynerds.runekeeper.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ButtonsJobs extends InputListener {

    public boolean hover;
    public boolean messageSignal;

    public ButtonsJobs() {

    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        hover = false;
        super.enter(event, x, y, pointer, fromActor);

        if (!hover) {
            Sound hoverSound = Gdx.audio.newSound(Gdx.files.internal("fuzzyBeep.mp3"));
            hoverSound.play(1F);
            hover = true;
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        hover = false;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        messageSignal = false;
        super.touchDown(event, x, y, pointer, button);

        if (button == Buttons.LEFT) {
            Sound clickedSound = Gdx.audio.newSound(Gdx.files.internal("clicking.mp3"));
            clickedSound.play(1F);
            messageSignal = true;
        }
        return true;
    }
}
