package net.jeffreysanti.mcmod.realrails.client;


import akka.util.Collections;

import com.google.common.base.Function;
import com.google.common.primitives.Ints;

import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel.Builder;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@SideOnly(Side.CLIENT)
public class BlockModelRail implements IModel {

	public static final ResourceLocation TBreak = new ResourceLocation("blocks/iron_block");  


    public BlockModelRail(IResourceManager resourceManager) {

    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Arrays.asList();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
    	HashSet<String> texs = RailRegistry.allTextureNames();
    	ArrayList<ResourceLocation> finalTexs = new ArrayList();
    	finalTexs.add(TBreak);
    	
    	for(String s : texs){
    		finalTexs.add(new ResourceLocation(RealRails.MODID+":blocks/rails/"+s));
    	}
    	
        return finalTexs;
    }
    
    public ResourceLocation textureFromData(int railElem, int railStyle){
    	String path = RealRails.MODID+":blocks/rails/";
    	path += RailRegistry.getTextureName(railStyle, railElem);
    	System.out.println(path + " ::: "+railElem + " / "+railStyle);
    	return new ResourceLocation(path);
    }

    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new SmartBlockModel(false, false, format, bakedTextureGetter.apply(TBreak), bakedTextureGetter){
        	@Override
        	public IBakedModel handleBlockState(IBlockState state) {
        		Builder b = new Builder(this, null);
        		b.setTexture(this.getTexture());
        		
        		int railElem = 1;
        		int railStyle = 1;
                if(state instanceof IExtendedBlockState) {
                	IExtendedBlockState ns = (IExtendedBlockState)state;
                	if(ns.getValue(BlockRail.UP_ELEM) != null && ns.getValue(BlockRail.UP_STYLE) != null){
                		railElem = ((IExtendedBlockState)state).getValue(BlockRail.UP_ELEM);
                		railStyle = ((IExtendedBlockState)state).getValue(BlockRail.UP_STYLE);
                	}
                }
                ResourceLocation texture = textureFromData(railElem, railStyle);
        		
        		int[] ftop = Ints.concat(
        				vertexToInts(1,0.125f,1, Color.WHITE.getRGB(), getTexture(texture), 16, 16),
        				vertexToInts(1,0.125f,0, Color.WHITE.getRGB(), getTexture(texture), 16, 0),
        				vertexToInts(0,0.125f,0, Color.WHITE.getRGB(), getTexture(texture), 0,  0),
        				vertexToInts(0,0.125f,1, Color.WHITE.getRGB(), getTexture(texture), 0,  16));
        		
        		b.addGeneralQuad(new BakedQuad(ftop, 0, EnumFacing.UP));
        		
        		return b.makeBakedModel();
        	}
        };
    }

    @Override
    public IModelState getDefaultState() {
        return null;
    }
}