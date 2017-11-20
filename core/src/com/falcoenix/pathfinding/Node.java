package com.falcoenix.pathfinding;

import java.util.ArrayList;
import java.util.List;

public class Node
{
	public final String id;
	public final int x;
	public final int y;
	
	public double G;
	public final double H;
	public double F = 0;
	
	public List<Edge> adjacencies;
	public Node parent;

	public Node(int x, int y, double H)
	{
		this.x = x;
		this.y = y;
		this.id = Integer.toString(x) + ":" + Integer.toString(y);
		
		this.H = H;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getF()
	{
		return (int) F;
	}

	public String toString()
	{
		return id;
	}
}