package com.angrynerds.runekeeper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.angrynerds.runekeeper.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 1200;
                config.height = 800;
                //reduces flicker on map
                config.vSyncEnabled = true;
		new LwjglApplication(new Main(), config);
	}
}
