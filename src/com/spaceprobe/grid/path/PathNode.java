package com.spaceprobe.grid.path;

public class PathNode{
	private int x;
	private int y;
	private PathNode parent;
	private double fValue =0;
	
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
	
	public void setFValue(double f){this.fValue = f;}		
	public double getFValue(){return fValue;}
}
