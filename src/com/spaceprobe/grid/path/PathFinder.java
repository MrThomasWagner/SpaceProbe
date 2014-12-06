package com.spaceprobe.grid.path;

import java.util.ArrayList;

import com.spaceprobe.grid.Grid;

public interface PathFinder {
	public ArrayList<PathNode> getPath(Grid grid, int startX, int startY, int endX, int endY);
}
