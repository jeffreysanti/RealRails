package net.jeffreysanti.mcmod.realrails.common;

import java.util.Collection;
import java.util.HashMap;

import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;

public class RampRegistry {
	
	static public void init(){
		E.clear();
		EL.clear();
		S.clear();
		
		// styles {path, id, supports45/135}
		styleToList(1, "blocks/brick"); // default
		styleToList(2, "blocks/bedrock");
		styleToList(3, "blocks/clay");
		styleToList(4, "blocks/coal_block");
		styleToList(5, "blocks/cobblestone");
		styleToList(6, "blocks/cobblestone_mossy");
		styleToList(7, "blocks/diamond_block");
		styleToList(8, "blocks/emerald_block");
		styleToList(9, "blocks/end_stone");
		styleToList(10, "blocks/gold_block");
		styleToList(11, "blocks/glowstone");
		styleToList(12, "blocks/gravel");
		styleToList(13, "blocks/hardened_clay");
		styleToList(14, "blocks/hardened_clay_stained_black");
		styleToList(15, "blocks/hardened_clay_stained_blue");
		styleToList(16, "blocks/hardened_clay_stained_brown");
		styleToList(17, "blocks/hardened_clay_stained_cyan");
		styleToList(18, "blocks/hardened_clay_stained_gray");
		styleToList(19, "blocks/hardened_clay_stained_green");
		styleToList(20, "blocks/hardened_clay_stained_light_blue");
		styleToList(21, "blocks/hardened_clay_stained_lime");
		styleToList(22, "blocks/hardened_clay_stained_magenta");
		styleToList(23, "blocks/hardened_clay_stained_orange");
		styleToList(24, "blocks/hardened_clay_stained_pink");
		styleToList(25, "blocks/hardened_clay_stained_purple");
		styleToList(26, "blocks/hardened_clay_stained_red");
		styleToList(27, "blocks/hardened_clay_stained_silver");
		styleToList(28, "blocks/hardened_clay_stained_white");
		styleToList(29, "blocks/hardened_clay_stained_yellow");
		styleToList(30, "blocks/ice_packed");
		styleToList(31, "blocks/iron_block");
		styleToList(32, "blocks/lapis_block");
		styleToList(33, "blocks/nether_brick");
		styleToList(34, "blocks/netherrack");
		styleToList(35, "blocks/obsidian");
		styleToList(36, "blocks/planks_acacia");
		styleToList(37, "blocks/planks_big_oak");
		styleToList(38, "blocks/planks_birch");
		styleToList(39, "blocks/planks_jungle");
		styleToList(40, "blocks/planks_oak");
		styleToList(41, "blocks/planks_spruce");
		styleToList(42, "blocks/planks_oak");
		styleToList(43, "blocks/prismarine_bricks");
		styleToList(44, "blocks/prismarine_dark");
		styleToList(45, "blocks/quartz_block_top");
		styleToList(46, "blocks/red_sand");
		styleToList(47, "blocks/red_sandstone_top");
		styleToList(48, "blocks/redstone_block");
		styleToList(49, "blocks/sand");
		styleToList(50, "blocks/sandstone_top");
		styleToList(51, "blocks/slime");
		styleToList(52, "blocks/snow");
		styleToList(53, "blocks/soul_sand");
		styleToList(54, "blocks/stone");
		styleToList(55, "blocks/stone_andesite");
		styleToList(56, "blocks/stone_andesite_smooth");
		styleToList(57, "blocks/stonebrick");
		styleToList(58, "blocks/stonebrick_carved");
		styleToList(59, "blocks/stonebrick_cracked");
		styleToList(60, "blocks/stonebrick_mossy");
		styleToList(61, "blocks/stone_diorite");
		styleToList(62, "blocks/stone_diorite_smooth");
		styleToList(63, "blocks/stone_granite");
		styleToList(64, "blocks/stone_granite_smooth");
		styleToList(65, "blocks/stone_slab_top");
		styleToList(66, "blocks/wool_colored_black");
		styleToList(67, "blocks/wool_colored_blue");
		styleToList(68, "blocks/wool_colored_brown");
		styleToList(69, "blocks/wool_colored_cyan");
		styleToList(70, "blocks/wool_colored_gray");
		styleToList(71, "blocks/wool_colored_green");
		styleToList(72, "blocks/wool_colored_light_blue");
		styleToList(73, "blocks/wool_colored_lime");
		styleToList(74, "blocks/wool_colored_magenta");
		styleToList(75, "blocks/wool_colored_orange");
		styleToList(76, "blocks/wool_colored_pink");
		styleToList(77, "blocks/wool_colored_purple");
		styleToList(78, "blocks/wool_colored_red");
		styleToList(79, "blocks/wool_colored_silver");
		styleToList(80, "blocks/wool_colored_white");
		styleToList(81, "blocks/wool_colored_yellow");
		
		
		rampToList(new RampPiece("1a_east",  1, 0.0f, 1.0f, FACING.RAMP_EAST));
		rampToList(new RampPiece("1a_west",  2, 0.0f, 1.0f, FACING.RAMP_WEST));
		rampToList(new RampPiece("1a_south", 3, 0.0f, 1.0f, FACING.RAMP_SOUTH));
		rampToList(new RampPiece("1a_north", 4, 0.0f, 1.0f, FACING.RAMP_NORTH));
		
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
	
	static public String getStyle(int styleID){
		String s = S.get(styleID);
		if(s == null)
			return "";
		return s;
	}
	
	static public Collection<String> getAllStyles(){
		return S.values();
	}
	
	static protected void rampToList(RampPiece r){
		E.put(r.getElemID(), r);
		EL.put(r.getName(), r.getElemID());
	}
	
	static protected void styleToList(int i, String s){
		S.put(i, s);
	}
	
	static private HashMap<Integer, RampPiece> E = new HashMap();
	static private HashMap<String, Integer> EL = new HashMap();
	
	static private HashMap<Integer, String> S = new HashMap();

}
