package com.falcoenix.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.falcoenix.pathfinding.SimpleNode;

public class MapSteps
{
	private static Texture texture = null;
	//private static Sprite sprite = null;
	
	private static SimpleNode prev;
	private static SimpleNode curr;
	private static SimpleNode next;
	private static int px;
	private static int py;
	private static int nx;
	private static int ny;
	
	private static HashMap<String, TextureRegion> stepMap;
	
	public MapSteps()
	{	
		texture = new Texture("units/steps.png");
		stepMap = new HashMap<String, TextureRegion>();
		
		stepMap.put("1111", new TextureRegion(texture, 0, 576, 64, 64)); //Puste
		stepMap.put("0000", new TextureRegion(texture, 0, 512, 64, 64)); //X
		
		stepMap.put("0220", new TextureRegion(texture, 0, 256, 64, 64)); //Ukos BL-TR
		stepMap.put("2002", new TextureRegion(texture, 0, 384, 64, 64)); //Ukos TR-BL
		stepMap.put("0202", new TextureRegion(texture, 0, 320, 64, 64)); //Ukos TL-BR
		stepMap.put("2020", new TextureRegion(texture, 0, 448, 64, 64)); //Ukos BR-TL
		
		stepMap.put("0211", new TextureRegion(texture, 0, 0, 64, 64)); //Prawo
		stepMap.put("0210", new TextureRegion(texture, 64, 0, 64, 64)); //1 wiersz
		stepMap.put("0212", new TextureRegion(texture, 128, 0, 64, 64));
		stepMap.put("0201", new TextureRegion(texture, 192, 0, 64, 64));
		stepMap.put("0221", new TextureRegion(texture, 256, 0, 64, 64));
		
		stepMap.put("1102", new TextureRegion(texture, 0, 64, 64, 64)); //Dó³
		stepMap.put("1202", new TextureRegion(texture, 64, 64, 64, 64)); //2 wiersz
		stepMap.put("1002", new TextureRegion(texture, 128, 64, 64, 64));
		stepMap.put("2102", new TextureRegion(texture, 192, 64, 64, 64));
		stepMap.put("0102", new TextureRegion(texture, 256, 64, 64, 64));
		
		stepMap.put("2011", new TextureRegion(texture, 0, 128, 64, 64)); //Lewa
		stepMap.put("2010", new TextureRegion(texture, 64, 128, 64, 64)); //3 wiersz
		stepMap.put("2012", new TextureRegion(texture, 128, 128, 64, 64));
		stepMap.put("2001", new TextureRegion(texture, 192, 128, 64, 64));
		stepMap.put("2021", new TextureRegion(texture, 256, 128, 64, 64));
		
		stepMap.put("1120", new TextureRegion(texture, 0, 192, 64, 64)); //Góra
		stepMap.put("0120", new TextureRegion(texture, 192, 192, 64, 64)); //4 wiersz
		stepMap.put("2120", new TextureRegion(texture, 256, 192, 64, 64));
		stepMap.put("1220", new TextureRegion(texture, 64, 192, 64, 64));
		stepMap.put("1020", new TextureRegion(texture, 128, 192, 64, 64));
		
	}
	
	public static List<TextureRegion> getSteps(List<SimpleNode> path)
	{
		//PX | NX | PY | NY
		if(path.size() == 0)
		return null;
		
		List<TextureRegion> regions = new ArrayList<TextureRegion>();
		
		if(path.size() == 1)
		{
			regions.add(new TextureRegion(texture, 0, 384, 64, 64));
		}
		else if(path.size() == 2)
		{
			regions.add(new TextureRegion(texture, 0, 384, 64, 64));
			regions.add(new TextureRegion(texture, 0, 384, 64, 64));
		}
		else
		{
			regions.add(stepMap.get("1111"));
			for(int i=1; i<path.size()-1; i++)
			{
				prev = path.get(i-1);
				curr = path.get(i);
				next = path.get(i+1);
				
				px = prev.x - curr.x + 1;
				nx = next.x - curr.x + 1;
				py = prev.y - curr.y + 1;
				ny = next.y - curr.y + 1;
				
				String key = Integer.toString(px) + Integer.toString(nx) + Integer.toString(py) + Integer.toString(ny);
				TextureRegion region = stepMap.get(key);
				
				if(region != null)
				regions.add(region);
				else
				regions.add(stepMap.get("1111"));
			}
			regions.add(stepMap.get("0000"));
		}
		
		return regions;
	}
}
