package com.spaceprobe.grid;

import java.util.Map;

public class GridFactory {
	private static final String TYPE = "type";
	private static final String SPACE_GRID = "space_grid";
	
	public Grid getGrid(Map<String, String> params){
		String type = params.get(TYPE);
		if(type.equals(SPACE_GRID)){
			return new SpaceGrid(
					Integer.parseInt(params.get("n")), 
					Integer.parseInt(params.get("asteroidPercent")), 
					Integer.parseInt(params.get("gravPercent")));
		}
		return null;
	}
}
