package com.falcoenix.map;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.falcoenix.clash.ClashGame;
import com.falcoenix.clash.UnitInfoWindow;
import com.falcoenix.mapunits.MapUnit;
import com.falcoenix.mapunits.Peasant;
import com.falcoenix.pathfinding.PathFinder;
import com.falcoenix.pathfinding.SimpleNode;

public class MapManager
{	
	private static ClashGame game;
	private static Skin skin;
	
	int size;
	
	TiledMap tiledMap = null;
	TiledMapRenderer tiledMapRenderer = null;
	
	MapUnitArray unitArray = null;
	MapInputProcessor mapInputProcessor = null;
	
	PathFinder pathFinder = null;
	
	OrthographicCamera camera = null;
	
	MapUnit active = null;
	UnitInfoWindow unitInfo = null;
	Stage guiStage = null;
	
	public MapManager(ClashGame game, Skin skin, TiledMap tiledMap, OrthographicCamera camera)
	{
		this.size = tiledMap.getProperties().get("width", Integer.class);
		this.tiledMap = tiledMap;
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		this.camera = camera;

		unitArray = new MapUnitArray(size);
		mapInputProcessor = new MapInputProcessor(this, camera, size);
		
		MapSteps mapSteps = new MapSteps();
		mapSteps = null;
		
		unitInfo = new UnitInfoWindow(game, skin);
		guiStage = new Stage();
	}
	
	public void leftClick(int x, int y)
	{
		//Je¿eli nie wybraliœmy jednostki
		if(active == null)
		{
			MapUnit unit = unitArray.getUnit(x, y);
			if(unit != null)
			{
				active = unit;
				unit.select(true);
			}
		}
		else //Je¿eli mamy wybran¹ jednostkê
		{
			MapUnit unit = unitArray.getUnit(x, y);
			if(unit != null)
			{
				//Walka / po³¹czenie
			}
			else
			{
				unitSetPath((int)active.getX(), (int)active.getY(), x, y);
			}
		}
	}

	public void rightClick(int screenX, int screenY)
	{
		double x = (camera.position.x - camera.viewportWidth/2 + screenX)/64;
		double y = (size*64 - camera.position.y - camera.viewportHeight/2 + screenY)/64;
		
		MapUnit unit = unitArray.getUnit((int)x, (int)y);
		if(unit != null)
		{
			//Info o jednostce
			unitInfo.update(unit);
			unitInfo.setPosition(screenX, camera.viewportHeight - screenY);
			unitInfo.setVisible(true);
		}
		else
		{
			if(active != null)
			{
				active.select(false);
				active = null;
			}
		}
	}
	
	public void unclicked()
	{
		unitInfo.setVisible(false);
	}
	
	public MapInputProcessor getInputProcessor()
	{
		return mapInputProcessor;
	}

	public void addUnit(int x, int y, MapUnit unit)
	{
		unit.setMapSize(size);
		unit.setPosition(x, y);
		unitArray.addUnit(x, y, unit);
	}
	
	public void unitSetPath(int x1, int y1, int x2, int y2)
	{
		MapUnit unit = unitArray.getUnit(x1, y1);
		if(unit != null)
		{
			unit.setPath(this.getPath(x1, y1, x2, y2));
		}
	}
	
	public void render(SpriteBatch batch, float delta)
	{
		camera.update();
        tiledMapRenderer.render();
        tiledMapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);

        //Rysowanie
        batch.begin();
        for(int y=0; y<size; y++)
        {
        	for(int x=0; x<size; x++)
        	{
        		MapUnit unit = unitArray.getUnit(x, y);
        		if(unit != null)
        		unit.draw(batch, 0);
        	}
        }
        batch.end();
        
        //GUI
        unitInfo.draw(batch);
        
        //Aktualizacja
        for(int y=0; y<size; y++)
        {
        	for(int x=0; x<size; x++)
        	{
        		MapUnit unit = unitArray.getUnit(x, y);
        		if(unit != null)
        		unit.act(delta);
        	}
        }
	}
	
	private List<SimpleNode> getPath(int x1, int y1, int x2, int y2)
	{
		pathFinder = null;
		if(tiledMap != null && unitArray != null)
		{
			pathFinder = new PathFinder(tiledMap, "desert-tileset", unitArray);
			List<SimpleNode> path = pathFinder.getPath(x1, y1, x2, y2);
			pathFinder = null;
			return path;
		}
		return null;
	}

	public InputProcessor getGuiStage()
	{
		return guiStage;
	}
}
