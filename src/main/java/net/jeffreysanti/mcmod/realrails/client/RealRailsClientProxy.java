package net.jeffreysanti.mcmod.realrails.client;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampStyle;
import net.jeffreysanti.mcmod.realrails.common.RealRailsCommonProxy;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ISmartItemModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;

public class RealRailsClientProxy extends RealRailsCommonProxy {

	@Override
	public void registerModelLoaders() {
		ModelLoaderRegistry.registerLoader(new CustomModelLoader());
	}
	
	@Override
	public void registerRenders() {
		
		// BLOCKS
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		
    	renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockRegistry.blockRail), 0, new ModelResourceLocation(RealRails.MODID + ":" + BlockRegistry.blockRail.getName(), "inventory"));
    	renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockRegistry.blockRamp), 0, new ModelResourceLocation(RealRails.MODID + ":" + BlockRegistry.blockRamp.getName(), "inventory"));
	
    	
    	// ITEMS
    	renderItem.getItemModelMesher().register(ItemRegistry.itemTrackMarker, 0, new ModelResourceLocation(RealRails.MODID + ":" + ItemRegistry.itemTrackMarker.getName(), "inventory"));
    	renderItem.getItemModelMesher().register(ItemRegistry.itemRampMarker, 0, new ModelResourceLocation(RealRails.MODID + ":" + ItemRegistry.itemRampMarker.getName(), "inventory"));
    	renderItem.getItemModelMesher().register(ItemRegistry.itemRampStyler, 0, new ModelResourceLocation(RealRails.MODID + ":" + ItemRegistry.itemRampStyler.getName(), "inventory"));
    	
    	renderItem.getItemModelMesher().register(ItemRegistry.itemRampBase, 0, new ModelResourceLocation(RealRails.MODID + ":" + ItemRegistry.itemRampBase.getName(), "inventory"));
    	for(ItemRamp r : ItemRegistry.itemRamps){
    		renderItem.getItemModelMesher().register(r, 0, new ModelResourceLocation(RealRails.MODID + ":" + r.getName(), "inventory"));
		}
	}
}
