package net.jeffreysanti.mcmod.realrails.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampPiece;
import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRamp;
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
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Function;
import com.google.common.primitives.Ints;

@SideOnly(Side.CLIENT)
public class BlockModelRamp implements IModel {

	public static final ResourceLocation TBreak = new ResourceLocation("blocks/iron_block");  


    public BlockModelRamp(IResourceManager resourceManager) {

    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Arrays.asList();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
    	//HashSet<String> texs = RailRegistry.allTextureNames();
    	ArrayList<ResourceLocation> finalTexs = new ArrayList();
    	finalTexs.add(TBreak);
    	
    	//for(String s : texs){
    	//	finalTexs.add(new ResourceLocation(RealRails.MODID+":blocks/rails/"+s));
    	//}
    	
        return finalTexs;
    }
    
    public static ResourceLocation textureFromData(int rampType, int rampStyle){
    	//String path = RealRails.MODID+":blocks/rails/";
    	//path += RailRegistry.getTextureName(railStyle, railElem);
    	//return new ResourceLocation(path);
    	return TBreak;
    }

    
    protected static int[] topFace(TextureAtlasSprite texture, float ymin, float ymax, FACING f){
    	if(f == FACING.RAMP_EAST){
    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,ymax,1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymax,0, Color.WHITE.getRGB(), texture, 16, 0),
    				SmartBlockModel.vertexToInts(0,ymin,0, Color.WHITE.getRGB(), texture, 0,  0),
    				SmartBlockModel.vertexToInts(0,ymin,1, Color.WHITE.getRGB(), texture, 0,  16));
    	}else if(f== FACING.RAMP_WEST){
    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,ymin,1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymin,0, Color.WHITE.getRGB(), texture, 16, 0),
    				SmartBlockModel.vertexToInts(0,ymax,0, Color.WHITE.getRGB(), texture, 0,  0),
    				SmartBlockModel.vertexToInts(0,ymax,1, Color.WHITE.getRGB(), texture, 0,  16));
    	}else if(f== FACING.RAMP_SOUTH){
    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,ymax,1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymin,0, Color.WHITE.getRGB(), texture, 16, 0),
    				SmartBlockModel.vertexToInts(0,ymin,0, Color.WHITE.getRGB(), texture, 0,  0),
    				SmartBlockModel.vertexToInts(0,ymax,1, Color.WHITE.getRGB(), texture, 0,  16));
    	}
    	return Ints.concat(
				SmartBlockModel.vertexToInts(1,ymin,1, Color.WHITE.getRGB(), texture, 16, 16),
				SmartBlockModel.vertexToInts(1,ymax,0, Color.WHITE.getRGB(), texture, 16, 0),
				SmartBlockModel.vertexToInts(0,ymax,0, Color.WHITE.getRGB(), texture, 0,  0),
				SmartBlockModel.vertexToInts(0,ymin,1, Color.WHITE.getRGB(), texture, 0,  16));
    }
    
    protected static int[] frontBackFace(TextureAtlasSprite texture, float ymax, EnumFacing f){
    	float umx = 16 - 16*ymax;
    	if(f == EnumFacing.EAST){
    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,0,   1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,0,   0, Color.WHITE.getRGB(), texture, 0,  16),
    				SmartBlockModel.vertexToInts(1,ymax,0, Color.WHITE.getRGB(), texture, 0,  umx),
    				SmartBlockModel.vertexToInts(1,ymax,1, Color.WHITE.getRGB(), texture, 16, umx));
    	}else if(f == EnumFacing.WEST){
    		return Ints.concat(
    				SmartBlockModel.vertexToInts(0,0,   1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(0,ymax,1, Color.WHITE.getRGB(), texture, 16, umx),
    				SmartBlockModel.vertexToInts(0,ymax,0, Color.WHITE.getRGB(), texture, 0,  umx),
    				SmartBlockModel.vertexToInts(0,0,   0, Color.WHITE.getRGB(), texture, 0,  16));
    	}else if(f == EnumFacing.SOUTH){
    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,0,   1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymax,1, Color.WHITE.getRGB(), texture, 16, umx),
    				SmartBlockModel.vertexToInts(0,ymax,1, Color.WHITE.getRGB(), texture, 0,  umx),
    				SmartBlockModel.vertexToInts(0,0,   1, Color.WHITE.getRGB(), texture, 0,  16));
    	}
    	return Ints.concat(
				SmartBlockModel.vertexToInts(1,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
				SmartBlockModel.vertexToInts(0,0,   0, Color.WHITE.getRGB(), texture, 0,  16),
				SmartBlockModel.vertexToInts(0,ymax,0, Color.WHITE.getRGB(), texture, 0,  umx),
				SmartBlockModel.vertexToInts(1,ymax,0, Color.WHITE.getRGB(), texture, 16, umx));
    }
    
    protected static int[] sideFace(TextureAtlasSprite texture, float ymin, float ymax, EnumFacing f, FACING f2){
    	float umx = 16.0f - 16*ymax;
    	float umn = 16.0f - 16*ymin;
    	if(f2 == FACING.RAMP_EAST){
	    	if(f == EnumFacing.SOUTH){
	    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,0,   1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymax,1, Color.WHITE.getRGB(), texture, 16, umx),
    				SmartBlockModel.vertexToInts(0,ymin,1, Color.WHITE.getRGB(), texture, 0,  umn),
    				SmartBlockModel.vertexToInts(0,0,   1, Color.WHITE.getRGB(), texture, 0,  16));
	    	}else{ // NORTH
	    		return Ints.concat(
					SmartBlockModel.vertexToInts(1,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
					SmartBlockModel.vertexToInts(0,0,   0, Color.WHITE.getRGB(), texture, 0,  16),
					SmartBlockModel.vertexToInts(0,ymin,0, Color.WHITE.getRGB(), texture, 0,  umn),
					SmartBlockModel.vertexToInts(1,ymax,0, Color.WHITE.getRGB(), texture, 16, umx));
	    	}
    	}else if(f2 == FACING.RAMP_WEST){ 
    		if(f == EnumFacing.SOUTH){
	    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,0,   1, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymin,1, Color.WHITE.getRGB(), texture, 16, umn),
    				SmartBlockModel.vertexToInts(0,ymax,1, Color.WHITE.getRGB(), texture, 0,  umx),
    				SmartBlockModel.vertexToInts(0,0,   1, Color.WHITE.getRGB(), texture, 0,  16));
	    	}else{ // NORTH
	    		return Ints.concat(
					SmartBlockModel.vertexToInts(1,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
					SmartBlockModel.vertexToInts(0,0,   0, Color.WHITE.getRGB(), texture, 0,  16),
					SmartBlockModel.vertexToInts(0,ymax,0, Color.WHITE.getRGB(), texture, 0,  umx),
					SmartBlockModel.vertexToInts(1,ymin,0, Color.WHITE.getRGB(), texture, 16, umn));
	    	}
    	}else if(f2 == FACING.RAMP_NORTH){ 
    		if(f == EnumFacing.EAST){
	    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymax,0, Color.WHITE.getRGB(), texture, 16, umx),
    				SmartBlockModel.vertexToInts(1,ymin,1, Color.WHITE.getRGB(), texture, 0,  umn),
    				SmartBlockModel.vertexToInts(1,0,   1, Color.WHITE.getRGB(), texture, 0,  16));
	    	}else{ // WEST
	    		return Ints.concat(
    				SmartBlockModel.vertexToInts(0,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(0,0,   1, Color.WHITE.getRGB(), texture, 0,  16),
    				SmartBlockModel.vertexToInts(0,ymin,1, Color.WHITE.getRGB(), texture, 0,  umn),
    				SmartBlockModel.vertexToInts(0,ymax,0, Color.WHITE.getRGB(), texture, 16, umx));
	    	}
    	}else{
    		if(f == EnumFacing.EAST){
	    		return Ints.concat(
    				SmartBlockModel.vertexToInts(1,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(1,ymin,0, Color.WHITE.getRGB(), texture, 16, umn),
    				SmartBlockModel.vertexToInts(1,ymax,1, Color.WHITE.getRGB(), texture, 0,  umx),
    				SmartBlockModel.vertexToInts(1,0,   1, Color.WHITE.getRGB(), texture, 0,  16));
	    	}else{ // WEST
	    		return Ints.concat(
    				SmartBlockModel.vertexToInts(0,0,   0, Color.WHITE.getRGB(), texture, 16, 16),
    				SmartBlockModel.vertexToInts(0,0,   1, Color.WHITE.getRGB(), texture, 0,  16),
    				SmartBlockModel.vertexToInts(0,ymax,1, Color.WHITE.getRGB(), texture, 0,  umx),
    				SmartBlockModel.vertexToInts(0,ymin,0, Color.WHITE.getRGB(), texture, 16, umn));
	    	}
    	}
    }
    
    protected static int[] bottomFace(TextureAtlasSprite texture){
		return Ints.concat(
				SmartBlockModel.vertexToInts(1,0,1, Color.WHITE.getRGB(), texture, 16, 16),
				SmartBlockModel.vertexToInts(0,0,1, Color.WHITE.getRGB(), texture, 0,  16),
				SmartBlockModel.vertexToInts(0,0,0, Color.WHITE.getRGB(), texture, 0,  0),
				SmartBlockModel.vertexToInts(1,0,0, Color.WHITE.getRGB(), texture, 16, 0));
    }
    
    public static void buildRampMesh(Builder b, int typeID, int styleID, Function<ResourceLocation, TextureAtlasSprite> f){
    	RampPiece p = RampRegistry.getRamp(typeID);
    	if(p == null)
    		return;
    	
    	ResourceLocation texture = textureFromData(typeID, styleID);
    	TextureAtlasSprite tex = f.apply(texture);
    	
    	float rampMin = p.getMinY();
		float rampMax = p.getMaxY();
		FACING face = p.getFacing();
		
		// top
		b.addGeneralQuad(new BakedQuad(topFace(tex, rampMin, rampMax, face), 0, EnumFacing.UP));
		
		// bottom
		b.addGeneralQuad(new BakedQuad(bottomFace(tex), 0, EnumFacing.DOWN));
		
		if(face == FACING.RAMP_EAST){
			b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMax, EnumFacing.EAST), 
					0, EnumFacing.EAST));
    		b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMin, EnumFacing.WEST), 
    				0, EnumFacing.WEST));
    		
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.SOUTH, face), 
    				0, EnumFacing.SOUTH));
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.NORTH, face), 
    				0, EnumFacing.NORTH));
		}else if(face == FACING.RAMP_WEST){
			b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMax, EnumFacing.WEST), 
					0, EnumFacing.WEST));
    		b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMin, EnumFacing.EAST), 
    				0, EnumFacing.EAST));
    		
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.SOUTH, face), 
    				0, EnumFacing.SOUTH));
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.NORTH, face), 
    				0, EnumFacing.NORTH));
		}else if(face == FACING.RAMP_NORTH){
			b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMax, EnumFacing.NORTH), 
					0, EnumFacing.NORTH));
    		b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMin, EnumFacing.SOUTH), 
    				0, EnumFacing.SOUTH));
    		
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.EAST, face), 
    				0, EnumFacing.EAST));
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.WEST, face), 
    				0, EnumFacing.WEST));
		}else{ // SOUTH
			b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMax, EnumFacing.SOUTH), 
					0, EnumFacing.SOUTH));
    		b.addGeneralQuad(new BakedQuad(frontBackFace(tex, rampMin, EnumFacing.NORTH), 
    				0, EnumFacing.NORTH));
    		
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.EAST, face), 
    				0, EnumFacing.EAST));
    		b.addGeneralQuad(new BakedQuad(sideFace(tex, rampMin, rampMax, EnumFacing.WEST, face), 
    				0, EnumFacing.WEST));
		}
    }
    
    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new SmartBlockModel(false, false, format, bakedTextureGetter.apply(TBreak), bakedTextureGetter){
        	@Override
        	public IBakedModel handleBlockState(IBlockState state) {
        		Builder b = new Builder(this, null);
        		b.setTexture(this.getTexture());
        		
        		int rampType = 1;
        		int rampStyle = 1;
                if(state instanceof IExtendedBlockState) {
                	IExtendedBlockState ns = (IExtendedBlockState)state;
                	if(ns.getValue(BlockRamp.UP_TYPE) != null && ns.getValue(BlockRamp.UP_STYLE) != null){
                		rampType = ((IExtendedBlockState)state).getValue(BlockRamp.UP_TYPE);
                		rampStyle = ((IExtendedBlockState)state).getValue(BlockRamp.UP_STYLE);
                	}
                }
 
        		buildRampMesh(b, rampType, rampStyle, textureGetter);
        		return b.makeBakedModel();
        	}
        };
    }

    @Override
    public IModelState getDefaultState() {
        return null;
    }
}
