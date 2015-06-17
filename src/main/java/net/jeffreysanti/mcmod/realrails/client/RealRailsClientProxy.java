package net.jeffreysanti.mcmod.realrails.client;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RealRailsCommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class RealRailsClientProxy extends RealRailsCommonProxy {

	@Override
	public void registerRenders() {
		ModelLoaderRegistry.registerLoader(new CustomModelLoader());
		
		// BLOCKS
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		
    	renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockRegistry.blockRail), 0, new ModelResourceLocation(RealRails.MODID + ":" + BlockRegistry.blockRail.getName(), "inventory"));
    	renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockRegistry.blockRamp), 0, new ModelResourceLocation(RealRails.MODID + ":" + BlockRegistry.blockRamp.getName(), "inventory"));
	
    	
    	// ITEMS
    	renderItem.getItemModelMesher().register(ItemRegistry.itemTrackMarker, 0, new ModelResourceLocation(RealRails.MODID + ":" + ItemRegistry.itemTrackMarker.getName(), "inventory"));
    	renderItem.getItemModelMesher().register(ItemRegistry.itemRampMarker, 0, new ModelResourceLocation(RealRails.MODID + ":" + ItemRegistry.itemRampMarker.getName(), "inventory"));
	}
}
