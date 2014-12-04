package com.spaceprobe.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar implements PathFinder{
	private int emptySpaceIndicator;
	private int pathIndicator;
	
	public AStar(int emptySpaceIndicator, int pathIndicator){
		this.emptySpaceIndicator = emptySpaceIndicator;
		this.pathIndicator = pathIndicator;
	}

	@Override
	public void addShortestPath(int[][] grid, int startX, int startY, int endX, int endY) {
		PriorityQueue<Node> open = new PriorityQueue<Node>(grid.length*grid.length, new NodeComparator());
		ArrayList<Node> closed = new ArrayList<Node>();
		
		open.add(new Node(startX, startY, null)); //starting node
		Node goal = new Node(endX, endY, null);	
		
		//there is no path to be found if we start in an invalid location
		if(grid[startX][startY]!= emptySpaceIndicator){
			return;
		}
		
		Node best = null;
		boolean found = false;
		
		//A*
		while(open.size() > 0 && !found){
			
			Node nextNode = open.remove();
			ArrayList<Node> successors = generateSuccessors(nextNode, grid.length, grid);	
			for(Node successor : successors){

				if(successor.hasSameCoordinates(goal)){
					found = true;
					drawPath(successor, grid);
					return;
				}
				
				successor.setFValue(
						nextNode.getFValue() + 
						euclidean(nextNode, successor) +
						euclidean(successor, goal)
				);
				
				//this pains me, need to extend PriorityQueue to get these down to O(1)
				boolean skip = false;
				for(Node n : open){
					if (n.hasSameCoordinates(successor) && n.getFValue()<successor.getFValue()){
						skip = true;
					}
				}
				for(Node n : closed){
					if (n.hasSameCoordinates(successor) && n.getFValue()<successor.getFValue()){
						skip = true;
					}
				}
				
				if(!skip){
					open.add(successor);
				}
				
				closed.add(nextNode);
				
				if(best== null || euclidean(best, goal) > euclidean(successor, goal)){
					best = successor;
				}
			}
		}
		if(!found){
			drawPath(best, grid);
		}
	}
	
	private void drawPath(Node endNode, int[][] grid){
		Node n = endNode;
		while(n != null){
			grid[n.getX()][n.getY()] = pathIndicator;
			n = n.getParent();
		}
	}
	
	private ArrayList<Node> generateSuccessors(Node n, int length, int[][] grid){		
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(n.getX()-1, n.getX(), n.getX()+1));
		ArrayList<Integer> b = new ArrayList<Integer>( Arrays.asList(n.getY()-1, n.getY(), n.getY()+1));
		
		ArrayList<Node> result = new ArrayList<Node>();		
		for(int x : a){
			for(int y : b){
				
				if(x == a.get(1) && y == b.get(1)){
					continue;
				}
				
				if(x >= 0 && y >= 0 && x <= length-1 && y <= length-1 && grid[x][y] == emptySpaceIndicator){
					result.add(new Node(x, y, n));
				}
			}
		}
		return result;
	}
	
	private double euclidean(Node a, Node b){
		return Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
	}
	
	public class NodeComparator implements Comparator<Node>
	{
	    @Override
	    public int compare(Node x, Node y){	    	
	        if (x.getFValue() < y.getFValue()){
	            return -1;
	        }else if (x.getFValue() > y.getFValue()){
	            return 1;
	        }else{
	        	return 0;	
	        }	        
	    }
	}
	
	private class Node{
		private int x;
		private int y;
		private Node parent;
		private double fValue =0;
		
		public Node(int x, int y, Node parent){
			this.x = x;
			this.y = y;
			this.parent = parent;
		}
		
		public boolean hasSameCoordinates(Node n){
			return (n.getX() == this.getX() && n.getY() == this.getY());
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public Node getParent(){
			return parent;
		}
		
		public void setFValue(double f){
			this.fValue = f;
		}
		
		public double getFValue(){
			return fValue;
		}
	}
}
