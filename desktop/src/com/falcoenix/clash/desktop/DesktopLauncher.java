package com.falcoenix.clash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.falcoenix.clash.ClashGame;
import com.falcoenix.pathfinding.PathFinder;

public class DesktopLauncher
{
	private static void fullscreen()
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.fullscreen = true;
	
		new LwjglApplication(new ClashGame(), config);
	}
	
	private static void window()
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		
		new LwjglApplication(new ClashGame(), config);
	}
	
	public static void main (String[] arg)
	{
		window();
	}
}
