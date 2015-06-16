package net.jeffreysanti.mcmod.realrails.client;



import com.google.common.base.Function;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;

/*
 *  Thanks So Much To herbix!!
 *  http://www.minecraftforge.net/forum/index.php/topic,28714.0.html
 *  
 *  This class allows for generated models without TESRs
 */

@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public abstract class SmartBlockModel implements IFlexibleBakedModel, ISmartBlockModel {

    private boolean isAmbientOcclusion;
    private boolean isGui3d;
    private VertexFormat format;
    protected Function<ResourceLocation, TextureAtlasSprite> textureGetter;
    protected TextureAtlasSprite tex;

    public SmartBlockModel(
            boolean ambientOcclusion,
            boolean gui3d,
            VertexFormat format,
            TextureAtlasSprite tex,
            Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        this.isAmbientOcclusion = ambientOcclusion;
        this.isGui3d = gui3d;
        this.format = format;
        this.textureGetter = bakedTextureGetter;
        this.tex = tex;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return isAmbientOcclusion;
    }

    @Override
    public boolean isGui3d() {
        return isGui3d;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    public TextureAtlasSprite getTexture(ResourceLocation rl) {
    	return textureGetter.apply(rl);
    }
    
    @Override
    public TextureAtlasSprite getTexture() {
    	return tex;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public VertexFormat getFormat() {
        return format;
    }
    
    
    // Thanks to TheGreyGhost
    // https://github.com/TheGreyGhost/MinecraftByExample/blob/master/src/main/java/minecraftbyexample/mbe15_item_smartitemmodel/ChessboardSmartItemModel.java
    static public int[] vertexToInts(float x, float y, float z, int color, TextureAtlasSprite texture, float u, float v)
    {
        return new int[] {
                Float.floatToRawIntBits(x),
                Float.floatToRawIntBits(y),
                Float.floatToRawIntBits(z),
                color,
                Float.floatToRawIntBits(texture.getInterpolatedU(u)),
                Float.floatToRawIntBits(texture.getInterpolatedV(v)),
                0
    	};
    }
}
