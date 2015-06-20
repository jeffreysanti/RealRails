package net.jeffreysanti.mcmod.realrails.common;

import java.util.ArrayList;
import java.util.List;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RailPieceGroup {
	
	public static class BlockRailPieceGroup{
		public BlockRailPieceGroup(BlockPos p, String railPiece){
			this.p = p;
			this.railPiece = railPiece;
		}
		
		public String railPiece;
		public BlockPos p;
	};
	
	
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
	
	public List<BlockRailPieceGroup> getAllTrackPieces(BlockPos point, int elmid){
		List<BlockRailPieceGroup> ret = new ArrayList<BlockRailPieceGroup>();
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
				ret.add(new BlockRailPieceGroup(point.add(dx, 0, dz), 
						RailRegistry.getPiece(layout[z][x]).getName()));
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
	
	// swaps one rail group for another (OF SAME SIZE AND SHAPE!)
	public static void subGroups(RailPieceGroup gold, RailPieceGroup gnew, World worldIn, BlockPos point, int elmid){
		assert(gold.layout.length == gnew.layout.length);
		for(int i=0; i<gold.layout.length; i++){
			assert(gold.layout[i].length == gnew.layout[i].length);
		}
		
		// get all pieces of old (including original)
		List<BlockRailPieceGroup> lst = gold.getAllTrackPieces(point, elmid);
		lst.add(new BlockRailPieceGroup(point, RailRegistry.getPiece(elmid).getName()));
		
		for(int z=0; z<gold.layout.length; z++){
			for(int x=0; x<gold.layout[z].length; x++){
				if(gold.layout[z][x] < 0)
					continue;
				
				for(BlockRailPieceGroup p : lst){
					if(p.railPiece.equals(RailRegistry.getPiece(gold.layout[z][x]).getName())){
						// make swap here
						if(worldIn.getBlockState(p.p).getBlock() != BlockRegistry.blockRail)
							break;
						
						TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(p.p);
						if(te == null)
							break;
						
						te.setRailID(gnew.layout[z][x]);
						te.syncTileEntity();
						break;
					}
				}
				
			}
		}
	}
	
	private int[][] layout;
	private String group;
	
}
