package com.falcoenix.pathfinding;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class AStar
{
	public static List<SimpleNode> getPath(Node target)
	{
		List<SimpleNode> path = new ArrayList<SimpleNode>();
        
		for(Node node = target; node!=null; node = node.parent)
		{
			SimpleNode n = new SimpleNode(node.getX(), node.getY());
			path.add(n);
		}

		Collections.reverse(path);
		return path;
	}

	public static void run(Node source, Node goal)
	{
		Set<Node> explored = new HashSet<Node>();

		PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>()
		{
			//Porównywanie
			public int compare(Node i, Node j)
			{
				if(i.F > j.F)
					return 1;
				else if(i.F < j.F)
					return -1;
				else
					return 0;
			}
		});

		//Koszt startu jest zerowany
		source.G = 0;
		queue.add(source);

		boolean found = false;
		while((!queue.isEmpty()) && (!found))
		{
			//the node in having the lowest f_score value
			Node current = queue.poll();

			explored.add(current);

			//goal found
			if(current.id.equals(goal.id))
			{
				found = true;
			}

			//check every child of current node
			for(Edge e : current.adjacencies)
			{
				Node child = e.target;
				double cost = e.cost;
				double temp_g_scores = current.G + cost;
				double temp_f_scores = temp_g_scores + child.H;

				/*if child node has been evaluated and 
				the newer f_score is higher, skip*/
				if((explored.contains(child)) && (temp_f_scores >= child.F))
				{
					continue;
				}

				/*else if child node is not in queue or 
				newer f_score is lower*/     
				else if((!queue.contains(child)) || (temp_f_scores < child.F))
				{
					child.parent = current;
					child.G = temp_g_scores;
					child.F = temp_f_scores;

					if(queue.contains(child))
					{
						queue.remove(child);
					}
					queue.add(child);
				}
			}
		}
	}
}