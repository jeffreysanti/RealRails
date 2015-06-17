package net.jeffreysanti.mcmod.realrails;

import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRamp;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRamp;
import net.jeffreysanti.mcmod.realrails.common.items.ItemTrackMarker;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class BlockRegistry {
	
	public static BlockRail blockRail;
	public static BlockRamp blockRamp;
	
	static void preInit(FMLPreInitializationEvent event){
		
		// title entities
		TileEntityRail.register();
		TileEntityRamp.register();
		
		// blocks
		blockRail = new BlockRail();
		blockRamp = new BlockRamp();
	}
	
	static void init(FMLInitializationEvent event){

	}
	
}
