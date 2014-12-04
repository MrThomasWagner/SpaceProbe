package com.spaceprobe.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GridGeneratorImpl implements GridGenerator{
	
	private int asteroidIndicator = 1;
	private int gravWellIndicator = 2;
	private int gravZoneIndicator = 3;
	
	public GridGeneratorImpl(int asteroidIndicator, int gravWellIndicator, int gravZoneIndicator){
		this.asteroidIndicator = asteroidIndicator;
		this.gravWellIndicator = gravWellIndicator;
		this.gravZoneIndicator = gravZoneIndicator;
	}
	
	private void colorAdjacents(ArrayList<Integer> a,ArrayList<Integer> b, int[][]result){
		int length = result.length;
		for(int x : a){
			for(int y : b){
				
				if(x == a.get(1) && y == b.get(1)){
					continue;
				}
				
				if(x >= 0 && y >= 0 && x <= length-1 && y <= length-1){
					
					if(result[x][y]!= gravWellIndicator){
						result[x][y] = gravZoneIndicator;
					}
				}
			}
		}
	}
	
	@Override
	public int[][] getGrid(int n, int asteroidPercent, int gravPercent) {
		Random r = new Random();
		
		int[][] result = new int[n][n];
		for (int x = 0; x < result.length; ++x){
		   for (int y = 0; y < result[0].length; ++y)
		   {
			   
			   int next = r.nextInt(100);
			   
			   if(next < asteroidPercent ){
				   
				   result[x][y] = asteroidIndicator;				   
				   
			   }else if(next >= asteroidPercent && next <= asteroidPercent + gravPercent){
				   
				   result[x][y] = gravWellIndicator;
				   
				   colorAdjacents(
						   new ArrayList<Integer>(Arrays.asList(x-1, x, x+1)), 
						   new ArrayList<Integer>( Arrays.asList(y-1, y, y+1)),
						   result
				   );				   
			   }
			   
		   }
		}		
		return result;
	}
}
