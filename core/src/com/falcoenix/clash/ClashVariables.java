package com.falcoenix.clash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ClashVariables
{
	final public static int tileSize = 64;
	final public static ClashGame game = new ClashGame();
	final public static Skin skin = new Skin(Gdx.files.internal("skins/clash-skin.json"));
	final public static Skin infoSkin = new Skin(Gdx.files.internal("skins/clash-skin.json"));
	
}
