package net.jeffreysanti.mcmod.realrails;

import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.RealRailsCommonProxy;
import net.jeffreysanti.mcmod.realrails.common.items.ItemTrackMarker;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = RealRails.MODID, version = RealRails.VERSION)
public class RealRails
{
	@SidedProxy(clientSide="net.jeffreysanti.mcmod.realrails.client.RealRailsClientProxy", serverSide="net.jeffreysanti.mcmod.realrails.common.RealRailsCommonProxy")
	public static RealRailsCommonProxy proxy;
    @Instance("RealRails")
    public static RealRails instance;
	
    public static final String MODID = "realrails";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	proxy.registerModelLoaders();
    	
    	RailRegistry.init();
    	RampRegistry.init();
    	
    	BlockRegistry.preInit(event);
    	ItemRegistry.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerRenders();
    	
    	BlockRegistry.init(event);
    	ItemRegistry.init(event);
    	
    	CraftingRegistry.init(event);
    }
}
