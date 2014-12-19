package com.spaceprobe.grid.path;

public class PathNode{
	private int x;
	private int y;
	private PathNode parent;
	private double fValue =0;
	private double distSoFar = 0;
	private double estimatedRemainingDist = 0;
	
	public PathNode(int x, int y, PathNode parent){
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	public boolean hasSameCoordinates(PathNode n){
		return (n.getX() == this.getX() && n.getY() == this.getY());
	}
	
	public int getX(){return x;}		
	public int getY(){return y;}		
	public PathNode getParent(){return parent;}		
	
	public void setDistSoFar(double j){this.distSoFar = j;}
	public double getDistSoFar(){return this.distSoFar;}
	public void setEstimatedRemainingDist(double j){this.estimatedRemainingDist = j;}
	public double getFValue(){return distSoFar + estimatedRemainingDist;}
}
