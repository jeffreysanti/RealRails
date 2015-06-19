package net.jeffreysanti.mcmod.realrails.client;

import java.util.Collection;

import com.google.common.base.Function;

import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRamp;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.common.property.IExtendedBlockState;

public class ItemModelRamp  implements IModel {

	
	BlockModelRamp bmr = null;
	
	public ItemModelRamp(IResourceManager resourceManager) {
		bmr = new BlockModelRamp(resourceManager);
    }
	
	@Override
	public Collection<ResourceLocation> getDependencies() {
		return bmr.getDependencies();
	}

	@Override
	public Collection<ResourceLocation> getTextures() {
		return bmr.getTextures();
	}

	@Override
	public IFlexibleBakedModel bake(IModelState state, VertexFormat format,
			Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		
		 return new SmartItemModel(false, true, format, bakedTextureGetter.apply(null), bakedTextureGetter){
			 
			@Override
			public IBakedModel handleItemState(ItemStack stack) {
				
				Builder b = new Builder(this, null);
        		b.setTexture(this.getTexture());
        		
				if(stack.getItem() instanceof ItemRamp){
					ItemRamp itm = (ItemRamp)stack.getItem();
					
					int rampStyle = itm.getStyleID();
					int rampType = 1;
					
					bmr.buildRampMesh(b, rampType, rampStyle, textureGetter);					
				}        		
        		
				
				IBakedModel ret = b.makeBakedModel();
				return ret;
			}
			 
		 };
	}

	@Override
	public IModelState getDefaultState() {
		return null;
	}

}
