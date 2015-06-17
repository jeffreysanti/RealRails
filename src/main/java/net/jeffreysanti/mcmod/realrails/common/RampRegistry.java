package net.jeffreysanti.mcmod.realrails.common;

import java.util.HashMap;

import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;

public class RampRegistry {
	
	static public void init(){
		E.clear();
		EL.clear();
		
		rampToList(new RampPiece("1a_east",  1, 0.0f, 1.0f, FACING.RAMP_EAST));
		rampToList(new RampPiece("1a_west",  2, 0.0f, 1.0f, FACING.RAMP_WEST));
		rampToList(new RampPiece("1a_south", 3, 0.0f, 1.0f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("1a_north", 4, 0.0f, 1.0f, FACING.RAMP_NORTH));
		
		rampToList(new RampPiece("2a_east",  5,  0.0f, 0.5f, FACING.RAMP_EAST));
		rampToList(new RampPiece("2b_east",  6,  0.5f, 1.0f, FACING.RAMP_EAST));
		rampToList(new RampPiece("2a_west",  7,  0.0f, 0.5f, FACING.RAMP_WEST));
		rampToList(new RampPiece("2b_west",  8,  0.5f, 1.0f, FACING.RAMP_WEST));
		rampToList(new RampPiece("2a_south", 9,  0.0f, 0.5f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("2b_south", 10, 0.5f, 1.0f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("2a_north", 11, 0.0f, 0.5f, FACING.RAMP_NORTH));
		rampToList(new RampPiece("2b_north", 12, 0.5f, 1.0f, FACING.RAMP_NORTH));
		
		rampToList(new RampPiece("3a_east",  13,  0.0f,  0.33f, FACING.RAMP_EAST));
		rampToList(new RampPiece("3b_east",  14,  0.33f, 0.66f, FACING.RAMP_EAST));
		rampToList(new RampPiece("3c_east",  15,  0.66f, 1.0f,  FACING.RAMP_EAST));
		
		rampToList(new RampPiece("3a_west",  16,  0.0f,  0.33f, FACING.RAMP_WEST));
		rampToList(new RampPiece("3b_west",  17,  0.33f, 0.66f, FACING.RAMP_WEST));
		rampToList(new RampPiece("3c_west",  18,  0.66f, 1.0f,  FACING.RAMP_WEST));
		
		rampToList(new RampPiece("3a_south", 19,  0.0f,  0.33f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("3b_south", 20,  0.33f, 0.66f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("3c_south", 21,  0.66f, 1.0f,  FACING.RAMP_SOUTH));
		
		rampToList(new RampPiece("3a_north", 22,  0.0f,  0.33f, FACING.RAMP_NORTH));
		rampToList(new RampPiece("3b_north", 23,  0.33f, 0.66f, FACING.RAMP_NORTH));
		rampToList(new RampPiece("3c_north", 24,  0.66f, 1.0f,  FACING.RAMP_NORTH));
	}
	
	static public RampPiece getRamp(int typeID){
		return E.get(typeID);
	}
	
	static protected void rampToList(RampPiece r){
		E.put(r.getElemID(), r);
		EL.put(r.getName(), r.getElemID());
	}
	
	static private HashMap<Integer, RampPiece> E = new HashMap();
	static private HashMap<String, Integer> EL = new HashMap();

}
