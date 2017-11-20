package com.falcoenix.mapunits;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.falcoenix.pathfinding.SimpleNode;

public class Peasant extends MapUnit
{
	public Peasant(int player)
	{
		super("units/peasant.png", player);
		
		type = "peasant";
		name = "Pospolite ruszenie";
		licence = 0;
		cost = 2;
		time = 1;
		
		attack = 1;
		defense = 1;
		shoot = 0;
		ranged = false;
		maxActionPoints = 24;
		actionPoints = maxActionPoints;
		morale = 10;
		
		path = new ArrayList<SimpleNode>();
		
		/* ** */
		
		animationFrames = new TextureRegion[8];
		for(int i=0; i<8; i++)
		{
			animationFrames[i] = new TextureRegion(texture, 64*i, 0, 64, 64);
		}
		animation = new Animation<TextureRegion>(1f/4f,animationFrames);
		animation.setPlayMode(Animation.PlayMode.LOOP);
		
		active = false;
	}
}
