package com.kyrutech.wilsons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kyrutech.wilsons.WilsonsAlgorithm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 600;
                config.height = 600;
		new LwjglApplication(new WilsonsAlgorithm(), config);
	}
}
