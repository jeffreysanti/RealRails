package net.jeffreysanti.mcmod.realrails.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.BlockPos;

public class RailPieceGroup {
	
	/*
	 * Layout is grid from N/W Corner:
	 * 
	 *    {  {-1,  4,  -1},
	 *       {-1,  2,  -1},
	 *       {1,   7,   3} }   where south center track piece is value 7
	 */
	public RailPieceGroup(String grpName, int[][] layout){
		this.layout = layout;
		this.group = grpName;
	}
	
	public String getName(){
		return group;
	}
	
	public List<BlockPos> getAllTrackPieces(BlockPos point, int elmid){
		List<BlockPos> ret = new ArrayList<BlockPos>();
		int xHere=-1;
		int zHere=-1;
		for(int z=0; z<layout.length; z++){
			boolean found = false;
			for(int x=0; x<layout[z].length; x++){
				if(layout[z][x] == elmid){
					found = true;
					xHere = x;
					zHere = z;
					break;
				}
				if(found)
					break;
			}
		}
		if(xHere == -1)
			return ret;
		
		// now add all other blocks in piece
		for(int z=0; z<layout.length; z++){
			for(int x=0; x<layout[z].length; x++){
				if(x == xHere && z == zHere)
					continue;
				if(layout[z][x] < 0)
					continue;
				int dx = x-xHere;
				int dz = z-zHere;
				ret.add(point.add(dx, 0, dz));
			}
		}
		
		return ret;
	}
	
	public List<Integer> getAllElmIds(){
		List<Integer> ret = new ArrayList<Integer>();
		for(int z=0; z<layout.length; z++){
			for(int x=0; x<layout[z].length; x++){
				if(layout[z][x] < 0)
					continue;
				ret.add(layout[z][x]);
			}
		}
		return ret;
	}
	
	private int[][] layout;
	private String group;
	
}
