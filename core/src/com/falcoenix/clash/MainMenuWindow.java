package com.falcoenix.clash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuWindow extends Window
{	
	private TextButton newGame;
	private TextButton loadGame;
	private TextButton networkGame;
	private TextButton settings;
	private TextButton credits;
	private TextButton exit;
	
	public MainMenuWindow(final ClashGame game, Skin skin)
	{
		super("Menu", skin);
		
		this.setWidth(250);
		this.setHeight(300);
		
		newGame = new TextButton("Nowa gra", skin, "default");
		newGame.addListener(new ClickListener()
		{
	        public void clicked(InputEvent e, float x, float y)
	        {
	        	game.setScreenToMap();
	        }
	    });
		
		loadGame = new TextButton("Wczytaj gre", skin, "default");
		networkGame = new TextButton("Gra sieciowa", skin, "default");
		settings = new TextButton("Ustawienia", skin, "default");
		credits = new TextButton("Autorzy", skin, "default");
		exit = new TextButton("Wyjscie", skin, "default");
		exit.addListener(new ClickListener()
		{
	        public void clicked(InputEvent e, float x, float y)
	        {
	        	Gdx.app.exit();
	        }
	    });
		
		this.add(newGame).size(200, 40);
		this.row();
		this.add(loadGame).size(200, 40);
		this.row();
		this.add(networkGame).size(200, 40);
		this.row();
		this.add(settings).size(200, 40);
		this.row();
		this.add(credits).size(200, 40);
		this.row();
		this.add(exit).size(200, 40);
	}
}
