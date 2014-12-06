package com.spaceprobe.grid.path;

import java.util.Comparator;

public class PathNodeComparator implements Comparator<PathNode>
{
    @Override
    public int compare(PathNode x, PathNode y){	    	
        if (x.getFValue() < y.getFValue())
            return -1;
        else if (x.getFValue() > y.getFValue())
            return 1;
        else
        	return 0;	        
    }
}