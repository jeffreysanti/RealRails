package net.jeffreysanti.mcmod.realrails.common;

public class RailStyle {
	
	public RailStyle(String name, int styleID){
		this(name, styleID, true, true);
	}
	
	// complete constructor
	public RailStyle(String name, int styleID, boolean b45_135, boolean bInclined){
		this.internalName = name;
		this.styleID = styleID;
		this.b45_135 = b45_135;
		this.bInclined = bInclined;
	}
	
	
	public boolean doesSupports45_135(){
		return b45_135;
	}
	
	public boolean doesSupportIncline(){
		return bInclined;
	}
	
	public String getName(){
		return internalName;
	}
	
	public int getStyleID(){
		return styleID;
	}
	
	private boolean b45_135;
	private boolean bInclined;
	
	private String internalName;
	private int styleID;

}
