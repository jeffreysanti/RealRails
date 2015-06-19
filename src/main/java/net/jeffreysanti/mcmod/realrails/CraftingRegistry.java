package net.jeffreysanti.mcmod.realrails;

import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CraftingRegistry {

	static void init(FMLInitializationEvent event){
		RampRegistry.registerCrafting();
	}
	
}
