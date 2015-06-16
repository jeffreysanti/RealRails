package net.jeffreysanti.mcmod.realrails.client;

import net.jeffreysanti.mcmod.realrails.RealRails;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

/*
 *  Thanks So Much To herbix!!
 *  http://www.minecraftforge.net/forum/index.php/topic,28714.0.html
 *  
 *  This class allows for generated models without TESRs
 */

@SideOnly(Side.CLIENT)
public class CustomModelLoader implements ICustomModelLoader {
	
	private IResourceManager resourceManager;
	   
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public boolean accepts(ResourceLocation l) {
        return l.getResourceDomain().equals(RealRails.MODID) && l.getResourcePath().startsWith("models/block/builtin/");
    }

    @Override
    public IModel loadModel(ResourceLocation l) {
        String r = l.getResourcePath().substring("models/block/builtin/".length());
        if(r.equals("rail")) {
            return new BlockModelRail(resourceManager);
        }
        throw new RuntimeException("A builtin model '" + r + "' is not defined.");
    }
}
