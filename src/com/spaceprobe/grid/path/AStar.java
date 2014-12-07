package com.spaceprobe.grid.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.spaceprobe.grid.Grid;

public class AStar implements PathFinder{

	@Override
	public ArrayList<PathNode> getPath(Grid grid, int startX, int startY, int endX, int endY) {
		PriorityQueue<PathNode> open = new PriorityQueue<PathNode>(50, new PathNodeComparator());
		ArrayList<PathNode> closed = new ArrayList<PathNode>();
		Map<List<Integer>, Double> bestValsSoFar = new HashMap<List<Integer>, Double>();
		
		open.add(new PathNode(startX, startY, null)); //starting node
		PathNode goal = new PathNode(endX, endY, null);	
		
		//there is no path to be found if we start in an invalid location
		if(!grid.isEmptySpace(startX, startY)){
			return null;
		}
		
		PathNode best = null;
		boolean found = false;
		
		//A*
		while(open.size() > 0 && !found){
			
			PathNode nextNode = open.remove();
			ArrayList<PathNode> successors = generateSuccessors(nextNode, grid);
			
			for(PathNode successor : successors){
				if(successor.hasSameCoordinates(goal)){
					found = true;
					return getPathCoordinates(successor);
				}				
				successor.setFValue(nextNode.getFValue() + euclideanQuick(nextNode, successor) + euclideanQuick(successor, goal));
				
				List<Integer> xy = Arrays.asList(new Integer[]{successor.getX(), successor.getY()});
				if (bestValsSoFar.get(xy) == null || bestValsSoFar.get(xy) > successor.getFValue()){
					open.add(successor);
					bestValsSoFar.put(Arrays.asList(new Integer[]{successor.getX(), successor.getY()}), successor.getFValue());
				}
				
				closed.add(nextNode);
				
				if(best== null || euclideanQuick(best, goal) > euclideanQuick(successor, goal))
					best = successor;				
			}//for each successor
		}//while nodes to search for
		
		if(!found){
			return getPathCoordinates(best);
		}
		
		return null;
	}
	
	private ArrayList<PathNode> getPathCoordinates(PathNode endNode){
		ArrayList<PathNode> result = new ArrayList<PathNode>();
		PathNode n = endNode;
		while(n != null){
			result.add(n);
			n = n.getParent();
		}
		return result;
	}
	
	private ArrayList<PathNode> generateSuccessors(PathNode n, Grid grid){		
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(n.getX()-1, n.getX(), n.getX()+1));
		ArrayList<Integer> b = new ArrayList<Integer>( Arrays.asList(n.getY()-1, n.getY(), n.getY()+1));
		
		ArrayList<PathNode> result = new ArrayList<PathNode>();		
		for(int x : a){	for(int y : b){	
			
			if(x == a.get(1) && y == b.get(1))
				continue;				
			
			if(x >= 0 && y >= 0 && x < grid.getSize() && y < grid.getSize() && grid.isEmptySpace(x, y))
				result.add(new PathNode(x, y, n));	
			
		}}
		return result;
	}
	
	private double euclideanQuick(PathNode a, PathNode b){ //no sqrt
		return Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2);
	}
}
