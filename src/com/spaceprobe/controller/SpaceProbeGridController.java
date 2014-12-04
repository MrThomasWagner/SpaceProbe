package com.spaceprobe.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.spaceprobe.grid.GridGenerator;
import com.spaceprobe.path.PathFinder;
 
/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class SpaceProbeGridController {
 
	@Autowired
	public GridGenerator gridGenerator;
	
	@Autowired
	public PathFinder pathFinder;
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld(
			@RequestParam("n") int n,
			@RequestParam("asteroidPercent") int asteroidPercent,
			@RequestParam("gravPercent") int gravPercent) {
		
		int cellDimensions = 10;
		
		int[][] grid = gridGenerator.getGrid(n, asteroidPercent, gravPercent);
		
		int [][] newGrid = new int[grid.length][];
		for(int i = 0; i < grid.length; i++){
			newGrid[i] = grid[i].clone();
		}
		
		//for now going from top-left of the grid to the bottom right
		pathFinder.addShortestPath(newGrid, 0, 0, newGrid.length-1, newGrid.length-1);
		
		return new ModelAndView("welcome", ImmutableMap.of(
				"grid", grid, 
				"gridWithPath", newGrid,
				"rowWidth", n*cellDimensions,
				"cellDimensions", cellDimensions
		));
		
	}
}