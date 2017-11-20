package com.falcoenix.map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MapInputProcessor implements InputProcessor
{
	MapManager manager;
	OrthographicCamera camera;
	int size;
	
	public MapInputProcessor(MapManager manager, OrthographicCamera camera, int size)
	{
		this.manager = manager;
		this.camera = camera;
		this.size = size;
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(button == 0)
		{
			double x = (camera.position.x - camera.viewportWidth/2 + screenX)/64;
			double y = (size*64 - camera.position.y - camera.viewportHeight/2 + screenY)/64;
			manager.leftClick((int) x, (int) y);
		}
		else if(button == 1)
		{
			manager.rightClick(screenX, screenY);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		manager.unclicked();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
