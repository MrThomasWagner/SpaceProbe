package com.spaceprobe.controller;
 
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.spaceprobe.grid.Grid;
import com.spaceprobe.grid.GridFactory;
import com.spaceprobe.grid.path.PathFinder;
import com.spaceprobe.grid.path.PathNode;

@Controller
public class SpaceProbeGridController {
 
	@Autowired
	public GridFactory gridFactory;
	
	@Autowired
	public PathFinder pathFinder;
	
	@RequestMapping("/spaceprobe")
	public ModelAndView getSpaceProbePath(
			@RequestParam Map<String,String> allRequestParams) {
		
		int cellDimensions = 10;
		Grid grid = gridFactory.getGrid(allRequestParams);	
		
		if(grid == null){
			return null;
		}
		
		Grid newGrid = grid.getCopy();
		
		//for now going from top-left of the grid to the bottom right
		ArrayList<PathNode> path = pathFinder.getPath(grid, 0, 0, grid.getSize()-1, grid.getSize()-1);
		
		if(path != null)
			newGrid.drawPath(path);
		
		return new ModelAndView("spaceprobe", ImmutableMap.of(
				"grid", grid.getRepresentation(), 
				"gridWithPath", newGrid.getRepresentation(),
				"rowWidth", Integer.parseInt(allRequestParams.get("n"))*cellDimensions, //need to move this into the front end
				"cellDimensions", cellDimensions // and this
		));
		
	}
}