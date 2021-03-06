package com.spaceprobe.grid;

import java.util.List;

import com.spaceprobe.grid.path.PathNode;

public interface Grid {
	public boolean isEmptySpace(int x, int y);
	public Object getRepresentation();
	public int getSize();
	public Grid getCopy();
	public void makePath(int x, int y);
	public void drawPath(List<PathNode> path);
}
