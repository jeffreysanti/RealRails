package net.jeffreysanti.mcmod.realrails.common;

public class RampPiece {

	public static enum FACING{
    	RAMP_NORTH,
    	RAMP_EAST,
    	RAMP_SOUTH,
    	RAMP_WEST
    }
	
	public RampPiece(String internalName, int elemID, float miny, float maxy, FACING f){
		this.internalName = internalName;
		this.elemID = elemID;
		this.maxy = maxy;
		this.miny = miny;
		this.face = f;
	}
	
	public String getName(){
		return internalName;
	}
	
	public int getElemID(){
		return elemID;
	}
	
	public float getMinY(){
		return miny;
	}
	
	public float getMaxY(){
		return maxy;
	}
	
	public FACING getFacing(){
		return face;
	}
	
	private String internalName;
	private int elemID;
	
	private float miny, maxy;
	private FACING face;
	
}
