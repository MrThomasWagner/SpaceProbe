package com.spaceprobe.grid.path;

import java.util.List;

import com.spaceprobe.grid.Grid;

public interface PathFinder {
	public List<PathNode> getPath(Grid grid, int startX, int startY, int endX, int endY);
}
