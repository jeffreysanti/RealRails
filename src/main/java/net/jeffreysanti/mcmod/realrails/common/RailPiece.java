package net.jeffreysanti.mcmod.realrails.common;

public class RailPiece {

	public static enum RailPieceType{
		RPT_STRAIGHT,
		RPT_STRAIGHT_INCLINED
	}
	
	// full constructor
	public RailPiece(String internalName, int elemID, RailPieceType rpt){
		this.internalName = internalName;
		this.elemID = elemID;
		this.rpt = rpt;
	}
	
	public String getName(){
		return internalName;
	}
	
	public int getElemID(){
		return elemID;
	}
	
	public String getGroup(){
		return group;
	}
	
	public void setGroup(String g){
		group = g;
	}
	
	public boolean isInclined(){
		return rpt == RailPieceType.RPT_STRAIGHT_INCLINED;
	}
	
	
	private String internalName;
	private int elemID;
	private String group;
	private RailPieceType rpt;
	
}
