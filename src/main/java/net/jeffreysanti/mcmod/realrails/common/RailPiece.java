package net.jeffreysanti.mcmod.realrails.common;

public class RailPiece {

	public RailPiece(String internalName, int elemID){
		this(internalName, elemID, false);
	}
	
	// full constructor
	public RailPiece(String internalName, int elemID, boolean b45_135){
		this.internalName = internalName;
		this.elemID = elemID;
		this.b45_135 = b45_135;
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
	
	public boolean is45_135(){
		return b45_135;
	}
	
	
	private String internalName;
	private int elemID;
	private String group;
	
	private boolean b45_135;
}
