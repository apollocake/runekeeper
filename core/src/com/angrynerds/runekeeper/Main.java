
package com.angrynerds.runekeeper;

import com.angrynerds.runekeeper.screens.StartScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {
	@Override
	public void create () {
		setScreen(new StartScreen(this));
	}
}
