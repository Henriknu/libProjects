package com.johnew.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.johnew.game.FlappyTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = FlappyTest.WIDTH;
		config.height = FlappyTest.HEIGHT;
		config.title = FlappyTest.TITLE;

		new LwjglApplication(new FlappyTest(), config);
	}
}
