package com.spaceprobe.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.spaceprobe.grid.path.PathNode;

public class SpaceGrid implements Grid{
	
	protected static int emptySpaceIndicator = 0;
	protected static int asteroidIndicator = 1;
	protected static int gravWellIndicator = 2;
	protected static int gravZoneIndicator = 3;
	protected static int pathIndicator = 4;
	
	private int[][] theGrid;
	private int length;
	
	public SpaceGrid(int n, int asteroidPercent, int gravPercent){
		this.length = n;
		theGrid = new int[n][n];		
		
		Random r = new Random();
		for (int x = 0; x < n; x++){ for (int y = 0; y < n; y++){
			
		   int next = r.nextInt(100);			 
		   
		   if(next < asteroidPercent){				   
			   makeAsteroid(x, y);			   
			   
		   }else if(next >= asteroidPercent && next <= asteroidPercent + gravPercent){				   
			   makeGravWell(x, y);				   
			   colorAdjacents(
					   new ArrayList<Integer>(Arrays.asList(x-1, x, x+1)), 
					   new ArrayList<Integer>(Arrays.asList(y-1, y, y+1))					   
			   );				   
		   }			   
		}}//for	
	}	
	
	private void colorAdjacents(ArrayList<Integer> a,ArrayList<Integer> b){
		for(int x : a){  for(int y : b){			
			if(x == a.get(1) && y == b.get(1))
				continue;			
			
			if(x >= 0 && y >= 0 && x < length && y < length && !isGravWell(x, y)){
				makeGravZone(x, y);				
			}			
		}}//for
	}
	
	protected boolean isGravWell(int x, int y){
		return theGrid[x][y] == gravWellIndicator;
	}
	
	protected void makeAsteroid(int x, int y){		
		theGrid[x][y] = asteroidIndicator;
	}
	
	protected void makeGravWell(int x, int y){
		theGrid[x][y] = gravWellIndicator;
	}
	
	protected void makeGravZone(int x, int y){
		theGrid[x][y] = gravZoneIndicator;
	}
	
	@Override
	public boolean isEmptySpace(int x, int y) {
		return theGrid[x][y] == SpaceGrid.emptySpaceIndicator;
	}
	
	@Override
	public void makePath(int x, int y){
		theGrid[x][y] = pathIndicator;
	}
	
	@Override
	public int[][] getRepresentation(){
		return theGrid;
	}
	
	@Override
	public int getSize(){
		return length;
	}

	@Override
	public Grid getCopy() {
		return new SpaceGrid(theGrid);	
	}
	
	private SpaceGrid(int [][] existingGrid){
		this.length = existingGrid.length;
		this.theGrid = new int[length][length];
		
		for(int i = 0; i < length; i++)
			theGrid[i] = existingGrid[i].clone();
	}

	@Override
	public void drawPath(ArrayList<PathNode> path) {
		for(PathNode n : path){
			makePath(n.getX(), n.getY());
		}		
	}	
}
