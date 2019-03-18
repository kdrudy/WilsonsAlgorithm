package com.kyrutech.wilsons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kyrutech.wilsons.MainMazeApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 400;
                config.height = 400;
		new LwjglApplication(new MainMazeApp(), config);
	}
}
