package com.falcoenix.clash;
import java.awt.Font;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ClashGame extends Game implements ApplicationListener
{
	private MainMenuScreen mainMenuScreen;
	private MapScreen mapScreen;
	
	public Font font;
	
	@Override
	public void create()
	{	
		mainMenuScreen = new MainMenuScreen(this, ClashVariables.skin);
		mapScreen = new MapScreen(this, ClashVariables.skin);
		
		this.setScreenToMenu();
	}
	
	public void setScreenToMenu()
	{
		this.setScreen(mainMenuScreen);
	}
	
	public void setScreenToMap()
	{
		this.setScreen(mapScreen);
		mainMenuScreen.pause();
	}

	@Override
	public void render()
	{
		super.render();
		this.handleInput();
	}
	
	private void handleInput()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		mapScreen.moveLeft();
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		mapScreen.moveRight();
		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		mapScreen.moveDown();
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		mapScreen.moveUp();

		/*
		cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);

		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
		*/
	}
	
	@Override
	public void dispose()
	{
		ClashVariables.skin.dispose();
		ClashVariables.infoSkin.dispose();
		//window = null;
	}
}
