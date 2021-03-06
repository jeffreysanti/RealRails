package net.jeffreysanti.mcmod.realrails.common;

import java.util.HashMap;
import java.util.HashSet;

import net.jeffreysanti.mcmod.realrails.common.RailPiece.RailPieceType;

public class RailRegistry {
	
	static public void init(){
		E.clear();
		EL.clear();
		S.clear();
		SL.clear();
		G.clear();
		
		// styles {path, id, supportsIncline}
		styleToList(new RailStyle("", 1, true)); // default
		
		
		// rails {name, id, 45/135}
		railToList(new RailPiece("std_0_a", 1, RailPieceType.RPT_STRAIGHT));
		railToList(new RailPiece("std_0_b", 2, RailPieceType.RPT_STRAIGHT));
		groupToList(new RailPieceGroup("std_0", new int[][]{ {1} , {2} }));
		
		railToList(new RailPiece("std_90_a", 3, RailPieceType.RPT_STRAIGHT));
		railToList(new RailPiece("std_90_b", 4, RailPieceType.RPT_STRAIGHT));
		groupToList(new RailPieceGroup("std_90", new int[][]{ {3, 4} }));
		
		railToList(new RailPiece("std_45_a", 5, RailPieceType.RPT_STRAIGHT));
		railToList(new RailPiece("std_45_b", 6, RailPieceType.RPT_STRAIGHT));
		railToList(new RailPiece("std_45_c", 7, RailPieceType.RPT_STRAIGHT));
		groupToList(new RailPieceGroup("std_45", new int[][]{ {5, 6, 7} }));
		
		railToList(new RailPiece("std_135_a", 8, RailPieceType.RPT_STRAIGHT));
		railToList(new RailPiece("std_135_b", 9, RailPieceType.RPT_STRAIGHT));
		railToList(new RailPiece("std_135_c", 10, RailPieceType.RPT_STRAIGHT));
		groupToList(new RailPieceGroup("std_135", new int[][]{ {10, 9, 8} }));
		
		railToList(new RailPiece("inc_0_a", 11, RailPieceType.RPT_STRAIGHT_INCLINED));
		railToList(new RailPiece("inc_0_b", 12, RailPieceType.RPT_STRAIGHT_INCLINED));
		groupToList(new RailPieceGroup("inc_0", new int[][]{ {11} , {12} }));
		
		railToList(new RailPiece("inc_90_a", 13, RailPieceType.RPT_STRAIGHT_INCLINED));
		railToList(new RailPiece("inc_90_b", 14, RailPieceType.RPT_STRAIGHT_INCLINED));
		groupToList(new RailPieceGroup("inc_90", new int[][]{ {13, 14} }));
		
		railToList3x3("align_90_east", 15, 23, RailPieceType.RPT_ALIGN);
		railToList3x3("align_90_west", 24, 32, RailPieceType.RPT_ALIGN);
		railToList3x3("align_0_north", 33, 41, RailPieceType.RPT_ALIGN);
		railToList3x3("align_0_south", 42, 50, RailPieceType.RPT_ALIGN);
		
		
		
	}
	
	static protected void railToList3x3(String name, int start, int end, RailPieceType typ){
		assert(end == start+8);
		railToList(new RailPiece(name+"_0", start+0, typ));
		railToList(new RailPiece(name+"_1", start+1, typ));
		railToList(new RailPiece(name+"_2", start+2, typ));
		railToList(new RailPiece(name+"_3", start+3, typ));
		railToList(new RailPiece(name+"_4", start+4, typ));
		railToList(new RailPiece(name+"_5", start+5, typ));
		railToList(new RailPiece(name+"_6", start+6, typ));
		railToList(new RailPiece(name+"_7", start+7, typ));
		railToList(new RailPiece(name+"_8", start+8, typ));
		groupToList(new RailPieceGroup(name, new int[][]{ 
				{start+0,start+1,start+2},
				{start+3,start+4,start+5},
				{start+6,start+7,start+8} }));
	}
	
	static protected void railToList(RailPiece r){
		E.put(r.getElemID(), r);
		EL.put(r.getName(), r.getElemID());
	}
	
	static protected void styleToList(RailStyle s){
		S.put(s.getStyleID(), s);
		SL.put(s.getName(), s.getStyleID());
	}
	
	static protected void groupToList(RailPieceGroup g){
		G.put(g.getName(), g);
		for(int i : g.getAllElmIds()){
			E.get(i).setGroup(g.getName());
		}
	}
	
	static public String getTextureName(int styleID, int elmID){
		RailStyle style = S.get(styleID);
		RailPiece rail = E.get(elmID);
		
		if(style == null || rail == null)
			return "";
		
		return style.getName() + rail.getName();
	}
	
	static public HashSet<String> allTextureNames(){
		HashSet<String> ret = new HashSet();
		for(RailStyle s : S.values()){
			String prefix = s.getName();
			boolean bincline = s.doesSupportIncline();
			
			for(RailPiece r : E.values()){
				if(!bincline && r.isInclined())
					continue;
				
				
				ret.add(prefix + r.getName());
			}
			
		}
		return ret;
	}
	
	public static RailPiece getPiece(int id){
		return E.get(id);
	}
	
	public static RailPiece getPiece(String id){
		return E.get(EL.get(id));
	}
	
	public static RailPieceGroup getGroup(String s){
		return G.get(s);
	}
	
	public static RailStyle getStyle(int id){
		return S.get(id);
	}
	
	
	
	static private HashMap<Integer, RailPiece> E = new HashMap();
	static private HashMap<String, Integer> EL = new HashMap();
	
	static private HashMap<Integer, RailStyle> S = new HashMap();
	static private HashMap<String, Integer> SL = new HashMap();
	
	static private HashMap<String, RailPieceGroup> G = new HashMap();
	
	
	
}
