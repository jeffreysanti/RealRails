package net.jeffreysanti.mcmod.realrails.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RampRegistry {
	
	public final static int BASEID = 82;
	
	public final static int RAMP45_EAST = 1;
	public final static int RAMP45_WEST = 2;
	public final static int RAMP45_SOUTH = 3;
	public final static int RAMP45_NORTH = 4;
	
	static public void init(){
		E.clear();
		EL.clear();
		S.clear();
		
		// styles {path, id, supports45/135}
		styleToList(new RampStyle(1, "blocks/brick", new ItemStack(Blocks.brick_block))); // default
		styleToList(new RampStyle(2, "blocks/bedrock", new ItemStack(Blocks.bedrock)));
		styleToList(new RampStyle(3, "blocks/clay", new ItemStack(Blocks.clay)));
		styleToList(new RampStyle(4, "blocks/coal_block", new ItemStack(Blocks.coal_block)));
		styleToList(new RampStyle(5, "blocks/cobblestone", new ItemStack(Blocks.cobblestone)));
		styleToList(new RampStyle(6, "blocks/cobblestone_mossy", new ItemStack(Blocks.mossy_cobblestone)));
		styleToList(new RampStyle(7, "blocks/diamond_block", new ItemStack(Blocks.diamond_block)));
		styleToList(new RampStyle(8, "blocks/emerald_block", new ItemStack(Blocks.emerald_block)));
		styleToList(new RampStyle(9, "blocks/end_stone", new ItemStack(Blocks.end_stone)));
		styleToList(new RampStyle(10, "blocks/gold_block", new ItemStack(Blocks.gold_block)));
		styleToList(new RampStyle(11, "blocks/glowstone", new ItemStack(Blocks.glowstone)));
		styleToList(new RampStyle(12, "blocks/gravel", new ItemStack(Blocks.gravel)));
		styleToList(new RampStyle(13, "blocks/hardened_clay", new ItemStack(Blocks.hardened_clay)));
		styleToList(new RampStyle(14, "blocks/hardened_clay_stained_black", new ItemStack(Blocks.stained_hardened_clay, 1, 15)));
		styleToList(new RampStyle(15, "blocks/hardened_clay_stained_blue", new ItemStack(Blocks.stained_hardened_clay, 1, 11)));
		styleToList(new RampStyle(16, "blocks/hardened_clay_stained_brown", new ItemStack(Blocks.stained_hardened_clay, 1, 12)));
		styleToList(new RampStyle(17, "blocks/hardened_clay_stained_cyan", new ItemStack(Blocks.stained_hardened_clay, 1, 9)));
		styleToList(new RampStyle(18, "blocks/hardened_clay_stained_gray", new ItemStack(Blocks.stained_hardened_clay, 1, 7)));
		styleToList(new RampStyle(19, "blocks/hardened_clay_stained_green", new ItemStack(Blocks.stained_hardened_clay, 1, 13)));
		styleToList(new RampStyle(20, "blocks/hardened_clay_stained_light_blue", new ItemStack(Blocks.stained_hardened_clay, 1, 3)));
		styleToList(new RampStyle(21, "blocks/hardened_clay_stained_lime", new ItemStack(Blocks.stained_hardened_clay, 1, 5)));
		styleToList(new RampStyle(22, "blocks/hardened_clay_stained_magenta", new ItemStack(Blocks.stained_hardened_clay, 1, 2)));
		styleToList(new RampStyle(23, "blocks/hardened_clay_stained_orange", new ItemStack(Blocks.stained_hardened_clay, 1, 1)));
		styleToList(new RampStyle(24, "blocks/hardened_clay_stained_pink", new ItemStack(Blocks.stained_hardened_clay, 1, 6)));
		styleToList(new RampStyle(25, "blocks/hardened_clay_stained_purple", new ItemStack(Blocks.stained_hardened_clay, 1, 10)));
		styleToList(new RampStyle(26, "blocks/hardened_clay_stained_red", new ItemStack(Blocks.stained_hardened_clay, 1, 14)));
		styleToList(new RampStyle(27, "blocks/hardened_clay_stained_silver", new ItemStack(Blocks.stained_hardened_clay, 1, 8)));
		styleToList(new RampStyle(28, "blocks/hardened_clay_stained_white", new ItemStack(Blocks.stained_hardened_clay, 1, 0)));
		styleToList(new RampStyle(29, "blocks/hardened_clay_stained_yellow", new ItemStack(Blocks.stained_hardened_clay, 1, 4)));
		styleToList(new RampStyle(30, "blocks/ice_packed", new ItemStack(Blocks.packed_ice)));
		styleToList(new RampStyle(31, "blocks/iron_block", new ItemStack(Blocks.iron_block)));
		styleToList(new RampStyle(32, "blocks/lapis_block", new ItemStack(Blocks.lapis_block)));
		styleToList(new RampStyle(33, "blocks/nether_brick", new ItemStack(Blocks.nether_brick)));
		styleToList(new RampStyle(34, "blocks/netherrack", new ItemStack(Blocks.netherrack)));
		styleToList(new RampStyle(35, "blocks/obsidian", new ItemStack(Blocks.obsidian)));
		styleToList(new RampStyle(36, "blocks/planks_acacia", new ItemStack(Blocks.planks, 1, 4)));
		styleToList(new RampStyle(37, "blocks/planks_big_oak", new ItemStack(Blocks.planks, 1, 5)));
		styleToList(new RampStyle(38, "blocks/planks_birch", new ItemStack(Blocks.planks, 1, 2)));
		styleToList(new RampStyle(39, "blocks/planks_jungle", new ItemStack(Blocks.planks, 1, 3)));
		styleToList(new RampStyle(40, "blocks/planks_oak", new ItemStack(Blocks.planks, 1, 0)));
		styleToList(new RampStyle(41, "blocks/planks_spruce", new ItemStack(Blocks.planks, 1, 1)));
		// 42 OPEN
		styleToList(new RampStyle(43, "blocks/prismarine_bricks", new ItemStack(Blocks.prismarine, 1, 1)));
		styleToList(new RampStyle(44, "blocks/prismarine_dark", new ItemStack(Blocks.prismarine, 1, 2)));
		styleToList(new RampStyle(45, "blocks/quartz_block_top", new ItemStack(Blocks.quartz_block)));
		styleToList(new RampStyle(46, "blocks/red_sand", new ItemStack(Blocks.sand, 1, 1)));
		styleToList(new RampStyle(47, "blocks/red_sandstone_top", new ItemStack(Blocks.red_sandstone)));
		styleToList(new RampStyle(48, "blocks/redstone_block", new ItemStack(Blocks.redstone_block)));
		styleToList(new RampStyle(49, "blocks/sand", new ItemStack(Blocks.sand, 1, 0)));
		styleToList(new RampStyle(50, "blocks/sandstone_top", new ItemStack(Blocks.sandstone)));
		styleToList(new RampStyle(51, "blocks/slime", new ItemStack(Blocks.slime_block)));
		styleToList(new RampStyle(52, "blocks/snow", new ItemStack(Blocks.snow)));
		styleToList(new RampStyle(53, "blocks/soul_sand", new ItemStack(Blocks.soul_sand)));
		styleToList(new RampStyle(54, "blocks/stone", new ItemStack(Blocks.stone, 1, 0)));
		styleToList(new RampStyle(55, "blocks/stone_andesite", new ItemStack(Blocks.stone, 1, 5)));
		styleToList(new RampStyle(56, "blocks/stone_andesite_smooth", new ItemStack(Blocks.stone, 1, 6)));
		styleToList(new RampStyle(57, "blocks/stonebrick", new ItemStack(Blocks.stonebrick, 1, 0)));
		styleToList(new RampStyle(58, "blocks/stonebrick_carved", new ItemStack(Blocks.stonebrick, 1, 3)));
		styleToList(new RampStyle(59, "blocks/stonebrick_cracked", new ItemStack(Blocks.stonebrick, 1, 2)));
		styleToList(new RampStyle(60, "blocks/stonebrick_mossy", new ItemStack(Blocks.stonebrick, 1, 1)));
		styleToList(new RampStyle(61, "blocks/stone_diorite", new ItemStack(Blocks.stone, 1, 3)));
		styleToList(new RampStyle(62, "blocks/stone_diorite_smooth", new ItemStack(Blocks.stone, 1, 4)));
		styleToList(new RampStyle(63, "blocks/stone_granite", new ItemStack(Blocks.stone, 1, 1)));
		styleToList(new RampStyle(64, "blocks/stone_granite_smooth", new ItemStack(Blocks.stone, 1, 2)));
		styleToList(new RampStyle(65, "blocks/stone_slab_top", new ItemStack(Blocks.double_stone_slab, 1, 0)));
		styleToList(new RampStyle(66, "blocks/wool_colored_black", new ItemStack(Blocks.wool, 1, 15)));
		styleToList(new RampStyle(67, "blocks/wool_colored_blue", new ItemStack(Blocks.wool, 1, 11)));
		styleToList(new RampStyle(68, "blocks/wool_colored_brown", new ItemStack(Blocks.wool, 1, 12)));
		styleToList(new RampStyle(69, "blocks/wool_colored_cyan", new ItemStack(Blocks.wool, 1, 9)));
		styleToList(new RampStyle(70, "blocks/wool_colored_gray", new ItemStack(Blocks.wool, 1, 7)));
		styleToList(new RampStyle(71, "blocks/wool_colored_green", new ItemStack(Blocks.wool, 1, 13)));
		styleToList(new RampStyle(72, "blocks/wool_colored_light_blue", new ItemStack(Blocks.wool, 1, 3)));
		styleToList(new RampStyle(73, "blocks/wool_colored_lime", new ItemStack(Blocks.wool, 1, 5)));
		styleToList(new RampStyle(74, "blocks/wool_colored_magenta", new ItemStack(Blocks.wool, 1, 2)));
		styleToList(new RampStyle(75, "blocks/wool_colored_orange", new ItemStack(Blocks.wool, 1, 1)));
		styleToList(new RampStyle(76, "blocks/wool_colored_pink", new ItemStack(Blocks.wool, 1, 6)));
		styleToList(new RampStyle(77, "blocks/wool_colored_purple", new ItemStack(Blocks.wool, 1, 10)));
		styleToList(new RampStyle(78, "blocks/wool_colored_red", new ItemStack(Blocks.wool, 1, 14)));
		styleToList(new RampStyle(79, "blocks/wool_colored_silver", new ItemStack(Blocks.wool, 1, 8)));
		styleToList(new RampStyle(80, "blocks/wool_colored_white", new ItemStack(Blocks.wool, 1, 0)));
		styleToList(new RampStyle(81, "blocks/wool_colored_yellow", new ItemStack(Blocks.wool, 1, 4)));
		styleToList(new RampStyle(BASEID, RealRails.MODID+":blocks/rampframe", null));
		
		
		rampToList(new RampPiece("1a_east",  RAMP45_EAST, 0.0f, 1.0f, FACING.RAMP_EAST));
		rampToList(new RampPiece("1a_west",  RAMP45_WEST, 0.0f, 1.0f, FACING.RAMP_WEST));
		rampToList(new RampPiece("1a_south", RAMP45_SOUTH, 0.0f, 1.0f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("1a_north", RAMP45_NORTH, 0.0f, 1.0f, FACING.RAMP_NORTH));
		
		rampToList(new RampPiece("2a_east",  5,  0.0f, 0.5f, FACING.RAMP_EAST));
		rampToList(new RampPiece("2b_east",  6,  0.5f, 1.0f, FACING.RAMP_EAST));
		rampToList(new RampPiece("2a_west",  7,  0.0f, 0.5f, FACING.RAMP_WEST));
		rampToList(new RampPiece("2b_west",  8,  0.5f, 1.0f, FACING.RAMP_WEST));
		rampToList(new RampPiece("2a_south", 9,  0.0f, 0.5f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("2b_south", 10, 0.5f, 1.0f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("2a_north", 11, 0.0f, 0.5f, FACING.RAMP_NORTH));
		rampToList(new RampPiece("2b_north", 12, 0.5f, 1.0f, FACING.RAMP_NORTH));
		
		rampToList(new RampPiece("3a_east",  13,  0.0f,  0.33f, FACING.RAMP_EAST));
		rampToList(new RampPiece("3b_east",  14,  0.33f, 0.66f, FACING.RAMP_EAST));
		rampToList(new RampPiece("3c_east",  15,  0.66f, 1.0f,  FACING.RAMP_EAST));
		
		rampToList(new RampPiece("3a_west",  16,  0.0f,  0.33f, FACING.RAMP_WEST));
		rampToList(new RampPiece("3b_west",  17,  0.33f, 0.66f, FACING.RAMP_WEST));
		rampToList(new RampPiece("3c_west",  18,  0.66f, 1.0f,  FACING.RAMP_WEST));
		
		rampToList(new RampPiece("3a_south", 19,  0.0f,  0.33f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("3b_south", 20,  0.33f, 0.66f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("3c_south", 21,  0.66f, 1.0f,  FACING.RAMP_SOUTH));
		
		rampToList(new RampPiece("3a_north", 22,  0.0f,  0.33f, FACING.RAMP_NORTH));
		rampToList(new RampPiece("3b_north", 23,  0.33f, 0.66f, FACING.RAMP_NORTH));
		rampToList(new RampPiece("3c_north", 24,  0.66f, 1.0f,  FACING.RAMP_NORTH));
	}
	
	static public RampPiece getRamp(int typeID){
		return E.get(typeID);
	}
	
	static public String getStyleTexture(int styleID){
		RampStyle st = S.get(styleID);
		if(st == null)
			return "";
		return st.getTexture();
	}
	
	static public Collection<String> getAllStyleTextures(){
		ArrayList<String> lst = new ArrayList();
		for(RampStyle s : S.values()){
			lst.add(s.getTexture());
		}
		return lst;
	}
	
	static public RampStyle getStyle(int sid){
		return S.get(sid);
	}
	
	static public Collection<RampStyle> getAllStyles(){
		return S.values();
	}
	
	static protected void rampToList(RampPiece r){
		E.put(r.getElemID(), r);
		EL.put(r.getName(), r.getElemID());
	}
	
	static protected void styleToList(RampStyle s){
		S.put(s.getStyleID(), s);
	}
	
	public static void registerCrafting() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.itemRampBase),
				new String[]{"  S",  " S ", "S L",},
			    'S',"stickWood", 
			    'L', "logWood"));
		for(ItemRamp r : ItemRegistry.itemRamps){
			GameRegistry.addShapelessRecipe(new ItemStack(r, 1),
					new ItemStack(ItemRegistry.itemRampBase, 1),
					getStyle(r.getStyleID()).getRecipeIngrd());
		}
	}
	
	static private HashMap<Integer, RampPiece> E = new HashMap();
	static private HashMap<String, Integer> EL = new HashMap();
	
	static private HashMap<Integer, RampStyle> S = new HashMap();

	

}
