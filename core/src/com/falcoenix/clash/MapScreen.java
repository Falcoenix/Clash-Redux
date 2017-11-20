package com.falcoenix.clash;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.falcoenix.map.MapInputProcessor;
import com.falcoenix.map.MapManager;
import com.falcoenix.mapunits.MapUnit;
import com.falcoenix.mapunits.Peasant;
import com.falcoenix.pathfinding.PathFinder;

public class MapScreen implements Screen
{
	final ClashGame game;
	final Skin skin;
	
    TiledMap tiledMap = null;
    OrthographicCamera camera = null;
    TiledMapRenderer tiledMapRenderer = null;
    
    int mapWidth = 100;
    int mapHeight = 100;
    
    int x;
    int y;
    
    private SpriteBatch batch = new SpriteBatch();
    
    InputMultiplexer inputMultiplexer;
    MapManager mapManager;

	public MapScreen(final ClashGame game, final Skin skin)
	{
		this.game = game;
		this.skin = skin;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(0,64*89);
        
        tiledMap = new TmxMapLoader().load("maps/untitled-land.tmx");
        
        mapManager = new MapManager(game, skin, tiledMap, camera);
        
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mapManager.getInputProcessor());
        inputMultiplexer.addProcessor(mapManager.getGuiStage());
        
        for(int i=0; i<30; i++)
        mapManager.addUnit(7, i, new Peasant(1));
        
        mapManager.addUnit(0, 0, new Peasant(1));
        
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta)
	{
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapManager.render(batch, Gdx.graphics.getDeltaTime()); 
	}

	@Override
	public void dispose()
	{
		tiledMap.dispose();
	    camera = null;
	    tiledMapRenderer = null;
	}
	
	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void pause()
	{
		inputMultiplexer.removeProcessor(mapManager.getInputProcessor());
	}
	
	@Override
	public void resume()
	{
		inputMultiplexer.addProcessor(mapManager.getInputProcessor());
	}
	
	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
	}

	public void moveLeft()
	{
		if(camera != null)
		{
			if(camera.position.x - camera.viewportWidth/2 > 0)
			camera.translate(-16,0);
			else
			camera.position.x = camera.viewportWidth/2;
		}
	}

	public void moveRight()
	{
		if(camera != null)
		{
			if(camera.position.x + camera.viewportWidth/2 < mapWidth * 64)
			camera.translate(16,0);
			else
			camera.position.x = mapWidth * 64 - camera.viewportWidth/2;
		}
	}

	public void moveDown()
	{
		if(camera != null)
		{
			if(camera.position.y - camera.viewportHeight/2 > 0)
			camera.translate(0,-16);
			else
			camera.position.y = camera.viewportHeight/2;
		}
	}

	public void moveUp()
	{
		if(camera != null)
		{
			if(camera.position.y + camera.viewportHeight/2 < mapHeight * 64)
			camera.translate(0,16);
			else
			camera.position.y = mapHeight * 64 - camera.viewportHeight/2;
		}
	}
}
