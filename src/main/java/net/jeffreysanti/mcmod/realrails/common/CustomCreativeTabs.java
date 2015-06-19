package net.jeffreysanti.mcmod.realrails.common;

import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomCreativeTabs {
	
	public static CreativeTabs tabDecoration = new CreativeTabs("RealRails Decoration") {
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return ItemRegistry.itemRampStyler;
	    }
	};
	
}
