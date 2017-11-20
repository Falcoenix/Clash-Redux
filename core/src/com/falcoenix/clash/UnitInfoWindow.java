package com.falcoenix.clash;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.falcoenix.mapunits.MapUnit;

public class UnitInfoWindow
{	
	int x;
	int y;
	
	private boolean visible;
	
	private Dialog dialog;
	private LabelStyle style;
	
	private Label title;
	private Label health;
	private Label attack;
	private Label defense;
	private Label actionPoints;
	private Label morale;
	private Label fatigue;
	
	private Stage stage;
	
	
	public UnitInfoWindow(final ClashGame game, Skin skin)
	{
		visible = false;
		
		dialog = new Dialog("", skin);
		dialog.setWidth(201);
		dialog.setHeight(116);
		dialog.setBackground("unit");
		
		style = new LabelStyle();
		style.font = skin.getFont("augusta16");
		
		title = new Label("Tytul", skin);
		title.setSize(127, 11);
		title.setAlignment(Align.center);
		title.setStyle(style);
		
		actionPoints = new Label("", skin);
		actionPoints.setSize(22, 22);
		actionPoints.setAlignment(Align.center);
		actionPoints.setStyle(style);
		
		health = new Label("", skin);
		health.setSize(22, 22);
		health.setAlignment(Align.center);
		health.setStyle(style);
		
		attack = new Label("", skin);
		attack.setSize(22, 22);
		attack.setAlignment(Align.center);
		attack.setStyle(style);
		
		defense = new Label("", skin);
		defense.setSize(22, 22);
		defense.setAlignment(Align.center);
		defense.setStyle(style);
		
		morale = new Label("", skin);
		morale.setSize(22, 22);
		morale.setAlignment(Align.center);
		morale.setStyle(style);
		
		fatigue = new Label("", skin);
		fatigue.setSize(22, 22);
		fatigue.setAlignment(Align.center);
		fatigue.setStyle(style);
		
		
		stage = new Stage();
		stage.addActor(dialog);
		stage.addActor(title);
		stage.addActor(health);
		stage.addActor(actionPoints);
		stage.addActor(attack);
		stage.addActor(defense);
		stage.addActor(morale);
		stage.addActor(fatigue);
	}
	
	public void update(MapUnit unit)
	{
		if(unit != null)
		{
			title.setText("Posp. ruszenie");
			actionPoints.setText(Integer.toString(unit.getAP()));
			attack.setText(Integer.toString(unit.getAP()));
			defense.setText(Integer.toString(unit.getAP()));
			morale.setText(Integer.toString(unit.getAP()));
			fatigue.setText(Integer.toString(unit.getAP()));
			health.setText("100");
		}
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public void setPosition(float x, float y)
	{
		this.x = (int) (x + 32);
		this.y = (int) (y - 100);
		
		dialog.setPosition(this.x, this.y);
		title.setPosition(this.x + 64, this.y + 114 - 14);
		health.setPosition(this.x + 43, this.y + 7);
		actionPoints.setPosition(this.x + 85, this.y + 49);
		attack.setPosition(this.x + 85, this.y + 4);
		defense.setPosition(this.x + 85 + 43, this.y + 4);
		morale.setPosition(this.x + 85 + 43, this.y + 49);
		fatigue.setPosition(this.x + 85 + 87, this.y + 49);
	}

	public void draw(SpriteBatch batch)
	{
		if(visible)
		{
			stage.draw();
		}
	}
}
