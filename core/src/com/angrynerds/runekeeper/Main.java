
package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.screens.StartScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Main extends Game  {
	@Override
	public void create () {

		setScreen(new StartScreen(this));
	}
}
