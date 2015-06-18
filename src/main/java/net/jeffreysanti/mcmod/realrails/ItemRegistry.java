package net.jeffreysanti.mcmod.realrails;

import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRampMarker;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRampStyler;
import net.jeffreysanti.mcmod.realrails.common.items.ItemTrackMarker;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ItemRegistry {

	public static ItemTrackMarker itemTrackMarker;
	public static ItemRampMarker itemRampMarker;
	public static ItemRampStyler itemRampStyler;
	
	public static ItemRamp itemRamp1;
	
	static void preInit(FMLPreInitializationEvent event){
		itemTrackMarker = new ItemTrackMarker();
		itemRampMarker = new ItemRampMarker();
		itemRampStyler = new ItemRampStyler();
		
		itemRamp1 = new ItemRamp(1);
	}
	
	static void init(FMLInitializationEvent event){
	}
}
