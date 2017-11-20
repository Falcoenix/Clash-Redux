package com.falcoenix.pathfinding;

public class SimpleNode
{
	public int x;
	public int y;
	public double w = 0;
	
	public SimpleNode(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return Integer.toString(x) + ":" + Integer.toString(y);
	}
}
