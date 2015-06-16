package net.jeffreysanti.mcmod.realrails;

import net.jeffreysanti.mcmod.realrails.common.items.ItemTrackMarker;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ItemRegistry {

	public static ItemTrackMarker itemTrackMarker;
	
	static void preInit(FMLPreInitializationEvent event){
		itemTrackMarker = new ItemTrackMarker();
	}
	
	static void init(FMLInitializationEvent event){
	}
}
