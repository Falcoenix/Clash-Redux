package com.falcoenix.map;

import com.falcoenix.mapunits.MapUnit;

public class MapUnitArray
{
	private MapUnit[][] array;

	public MapUnitArray(int size)
	{
		array = new MapUnit[size][size];
		for(int y=0; y<size; y++)
		for(int x=0; x<size; x++)
		array[x][y] = null;
	}
	
	public void addUnit(int x, int y, MapUnit unit)
	{
		if(x>=0 && y>=0 && x<array.length && y<array.length)
		{
			if(array[x][y] == null)
			array[x][y] = unit;
		}
	}
	
	public void moveUnit(int x1, int y1, int x2, int y2)
	{
		if(x1>=0 && y1>=0 && x1<array.length && y1<array.length)
		{
			if(x2>=0 && y2>=0 && x2<array.length && y2<array.length)
			{
				if(array[x2][y2] == null)
				{
					array[x2][y2] = array[x1][y1];
					array[x1][y1] = null;
				}
			}
			
		}
	}
	
	public void removeUnit(int x, int y)
	{
		if(x>=0 && y>=0 && x<array.length && y<array.length)
		{
			if(array[x][y] != null)
			array[x][y] = null;
		}
	}
	
	public MapUnit getUnit(int x, int y)
	{
		if(x>=0 && y>=0 && x<array.length && y<array.length)
		return array[x][y];
		
		return null;
	}
}
