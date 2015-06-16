package net.jeffreysanti.mcmod.realrails;

import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.jeffreysanti.mcmod.realrails.common.items.ItemTrackMarker;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class BlockRegistry {
	
	public static BlockRail blockRail;
	
	static void preInit(FMLPreInitializationEvent event){
		
		// title entities
		TileEntityRail.register();
		
		// blocks
		blockRail = new BlockRail();		
	}
	
	static void init(FMLInitializationEvent event){

	}
	
}
