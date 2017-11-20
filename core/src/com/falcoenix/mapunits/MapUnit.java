package com.falcoenix.mapunits;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.falcoenix.clash.ClashVariables;
import com.falcoenix.map.MapSteps;
import com.falcoenix.pathfinding.SimpleNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public abstract class MapUnit extends Actor
{
	int player;
	boolean active;
	protected static String type;
	protected static String name;
	protected static int licence;
	protected static int cost;
	protected static int time;
	protected static int attack;
	protected static int defense;
	protected int health;
	protected static int shoot;
	protected static boolean ranged;
	protected int maxActionPoints;
	protected double actionPoints;
	protected int morale;
	protected int fatigue;
	
	protected int x;
	protected int y;
	protected double moveX;
	protected double moveY;
	
	
	protected List<SimpleNode> path;
	protected List<Double> pathRange = new ArrayList<Double>();

	//Animacja
	static Texture texture = null;
	static Texture activeTexture = null;
	
	TextureRegion[] animationFrames;
	Animation<TextureRegion> animation;
	
	Sprite sprite = null;
	Sprite activeSprite = null;
	Sprite step = null;
	
	float elapsedTime;
	
	static int mapSize;
	
	public MapUnit(String texturePath, int player)
	{
		if(texture == null)
		texture = new Texture(texturePath);
		
		if(activeTexture == null)
		activeTexture = new Texture("units/hud.png");
		
		if(sprite == null)
		sprite = new Sprite(texture);
        
		if(step == null)
		{
			step = new Sprite(texture);
			step.setBounds(0, 0, ClashVariables.tileSize, ClashVariables.tileSize);
		}
		
		if(activeSprite == null)
		{
			activeSprite = new Sprite(activeTexture);
			activeSprite.setBounds(0, 0, ClashVariables.tileSize, ClashVariables.tileSize);
		}
		
		health = 100;
		morale = 10;
		fatigue = 0;
        
        active = false;
        this.player = player;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getAP()
	{
		return (int)actionPoints;
	}
	
	public int getMorale()
	{
		return morale;
	}
	
	public int getFatigue()
	{
		return morale;
	}

	public void setMapSize(int mapSize)
	{
		this.mapSize = mapSize;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public float getX()
	{
		return x;
	}

	@Override
	public float getY()
	{
		return y;
	}
	
	public int getPlayer()
	{
		return player;
	}

	@Override
	public void act(float delta)
	{	
		super.act(delta);
	}

	public void setPath(List<SimpleNode> path)
	{
		this.path = path;
		
		if(path.size() > 0)
		{
			pathRange.clear();
			pathRange.add(0.0);
			for(int i=1; i<path.size(); i++)
			pathRange.add(pathRange.get(i-1) + path.get(i).w);
		}
	}
	
	public void select(boolean active)
	{
		this.active = active;
	}
	
	private void drawPath(Batch batch)
	{
		List<TextureRegion> regions = MapSteps.getSteps(path);
		
		int tileSize = ClashVariables.tileSize;
		
		if(regions != null)
		{
			step.setColor(1f, 1f, 1f, 1f);
			step.setRegion(regions.get(0));
			step.setPosition(path.get(0).x*tileSize, (mapSize-1-path.get(0).y)*tileSize);
			step.draw(batch);
			
			if(regions.size() > 1)
			for(int i=1; i<regions.size(); i++)
			{
				if(pathRange.get(i) <= actionPoints)
				step.setColor(0, 0, 0, 1f);
				else
				step.setColor(1f, 1f, 1f, 1f);
				
				step.setRegion(regions.get(i));
				step.setPosition(path.get(i).x*tileSize, (mapSize-1-path.get(i).y)*tileSize);
				step.draw(batch);
			}
		}
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		int tileSize = ClashVariables.tileSize;
		
		if(active)
		{
			activeSprite.setPosition((int)x*tileSize, (int)(mapSize-1-y)*tileSize);
			activeSprite.draw(batch);
			
			drawPath(batch);
		}
		
		batch.draw(animation.getKeyFrame(elapsedTime += Gdx.graphics.getDeltaTime()), (int)x*tileSize, (int)(mapSize-1-y)*tileSize);
	}

	
}