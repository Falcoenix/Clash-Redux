package com.falcoenix.pathfinding;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.falcoenix.map.MapUnitArray;
import com.falcoenix.mapunits.MapUnit;

public class PathFinder
{
	private int size;
	
	private int[][] weights = null;
	private List<Node> nodes = new ArrayList<Node>();
	
	public PathFinder(TiledMap tiledMap, String tileSetName, MapUnitArray units)
	{
		size = tiledMap.getProperties().get("width", Integer.class);
		TiledMapTileSet tileset = tiledMap.getTileSets().getTileSet(tileSetName);
		if(tileset != null)
		{
			TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("ground");
			weights = new int[layer.getWidth()][layer.getHeight()];
			
			for(int y=0; y<layer.getHeight(); y++)
			{
				for(int x=0; x<layer.getWidth(); x++)
				{
					int tileCost = (Integer) layer.getCell(x, size-1-y).getTile().getProperties().get("cost");
					weights[x][y] = tileCost;
					
					if(units.getUnit(x, y) != null)
					weights[x][y] = Integer.MAX_VALUE;
				}
			}
			
			size = weights.length;
		}
	}
	
	public List<SimpleNode> getPath(int x1, int y1, int x2, int y2)
    {	
		nodes.clear();
		Node source = null;
		Node dest = null;
		
		//Dodawanie pól
		for(int y=0; y<size; y++)
		{
			for(int x=0; x<size; x++)
			{
				Node n = new Node(x, y, (y2-y + x2-x)*1);
				
				if(x == x1 && y == y1)
				source = n;
				else if(x == x2 && y == y2)
				dest = n;
				
				nodes.add(n);
			}
		}
		
		//Dodawanie krawêdzi
		for(int y=0; y<size; y++)
		{
			for(int x=0; x<size; x++)
			{
				nodes.get(y*size + x).adjacencies = getAdjacencies(x, y);
			}
		}
		
		AStar.run(source, dest);
		
		List<SimpleNode> path = AStar.getPath(dest);
		path.get(0).w = 0;
		for(int i=1; i<path.size(); i++)
		{
			SimpleNode n = path.get(i);
			if(path.get(i-1).x != n.x && path.get(i-1).y != n.y)
				n.w = weights[n.x][n.y] * Math.sqrt(2);
			else
				n.w = weights[n.x][n.y];		
		}
		
        return path;
    }
	
	public int[][] getWeights()
	{
		return weights;
	}
	
	private ArrayList<Edge> getAdjacencies(int x, int y)
	{
		List<Edge> edges = new ArrayList<Edge>();
		
		if(y-1 >= 0)
		edges.add(new Edge(nodes.get((y-1)*size + x), weights[x][y-1])); //1
		
		if(y-1 >= 0 && x+1 < size)
		edges.add(new Edge(nodes.get((y-1)*size + (x+1)), weights[x+1][y-1]*Math.sqrt(2))); //2
		
		if(x+1 < size)
		edges.add(new Edge(nodes.get(y*size + (x+1)), weights[x+1][y])); //3
		
		if(y+1 < size && x+1 < size)
		edges.add(new Edge(nodes.get((y+1)*size + (x+1)), weights[x+1][y+1]*Math.sqrt(2))); //4
		
		if(y+1 < size)
		edges.add(new Edge(nodes.get((y+1)*size + x), weights[x][y+1])); //5
		
		if(y+1 < size && x-1 >= 0)
		edges.add(new Edge(nodes.get((y+1)*size + (x-1)), weights[x-1][y+1]*Math.sqrt(2))); //6
		
		if(x-1 >= 0)
		edges.add(new Edge(nodes.get(y*size + (x-1)), weights[x-1][y])); //7
		
		if(y-1 >= 0 && x-1 >= 0)
		edges.add(new Edge(nodes.get((y-1)*size + (x-1)), weights[x-1][y-1]*Math.sqrt(2))); //8
		
		return (ArrayList<Edge>) edges;
	}
}
