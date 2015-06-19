package net.jeffreysanti.mcmod.realrails.common;

import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;
import net.minecraft.item.ItemStack;

public class RampStyle {

	public RampStyle(int styleID, String texture, ItemStack craftingIngr){
		this.texture = texture;
		this.styleID = styleID;
		this.craftingIngr = craftingIngr;
	}
	
	public String getTexture(){
		return texture;
	}
	
	public int getStyleID(){
		return styleID;
	}
	
	public ItemStack getRecipeIngrd(){
		return craftingIngr;
	}
	
	
	private String texture;
	private int styleID;
	private ItemStack craftingIngr;
	
}
