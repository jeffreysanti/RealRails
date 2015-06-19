package net.jeffreysanti.mcmod.realrails.common;

public class RailStyle {
	
	
	// complete constructor
	public RailStyle(String name, int styleID, boolean bInclined){
		this.internalName = name;
		this.styleID = styleID;
		this.bInclined = bInclined;
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
	
	private boolean bInclined;
	
	private String internalName;
	private int styleID;

}
