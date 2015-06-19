package net.jeffreysanti.mcmod.realrails;

import java.util.ArrayList;

import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampStyle;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRailStraight;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRampMarker;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRampStyler;
import net.jeffreysanti.mcmod.realrails.common.items.ItemTrackMarker;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ItemRegistry {

	public static ItemTrackMarker itemTrackMarker;
	public static ItemRampMarker itemRampMarker;
	public static ItemRampStyler itemRampStyler;
	
	public static ItemRamp itemRampBase;
	public static ArrayList<ItemRamp> itemRamps = new ArrayList();
	
	public static ItemRailStraight itemRailStraight;
	
	static void preInit(FMLPreInitializationEvent event){
		itemTrackMarker = new ItemTrackMarker();
		itemRampMarker = new ItemRampMarker();
		itemRampStyler = new ItemRampStyler();
		
		itemRampBase = new ItemRamp(RampRegistry.BASEID);
		for(RampStyle s : RampRegistry.getAllStyles()){
			if(s.getStyleID() == RampRegistry.BASEID)
				continue;
			itemRamps.add(new ItemRamp(s.getStyleID()));
		}
		
		itemRailStraight = new ItemRailStraight(1);
	}
	
	
	
	static void init(FMLInitializationEvent event){
	}
	
	public static ItemRamp getItemRampOfStyle(int styleID){
		if(styleID == RampRegistry.BASEID)
			return itemRampBase;
		
		for(ItemRamp r : itemRamps){
			if(r.getStyleID() == styleID)
				return r;
		}
		return null;
	}
}
