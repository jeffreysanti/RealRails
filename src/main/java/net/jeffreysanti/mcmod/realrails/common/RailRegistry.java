package net.jeffreysanti.mcmod.realrails.common;

import java.util.HashMap;
import java.util.HashSet;

public class RailRegistry {
	
	static public void init(){
		E.clear();
		EL.clear();
		S.clear();
		SL.clear();
		G.clear();
		
		// styles {path, id, supports45/135}
		styleToList(new RailStyle("", 1, true)); // default
		
		
		// rails {name, id, 45/135}
		railToList(new RailPiece("std_0_a", 1, false));
		railToList(new RailPiece("std_0_b", 2, false));
		groupToList(new RailPieceGroup("std_0", new int[][]{ {1} , {2} }));
		
		railToList(new RailPiece("std_90_a", 3, false));
		railToList(new RailPiece("std_90_b", 4, false));
		groupToList(new RailPieceGroup("std_90", new int[][]{ {3, 4} }));
		
		railToList(new RailPiece("std_45_a", 5, true));
		railToList(new RailPiece("std_45_b", 6, true));
		railToList(new RailPiece("std_45_c", 7, true));
		groupToList(new RailPieceGroup("std_45", new int[][]{ {5, 6, 7} }));
		
		railToList(new RailPiece("std_135_a", 8, true));
		railToList(new RailPiece("std_135_b", 9, true));
		railToList(new RailPiece("std_135_c", 10, true));
		groupToList(new RailPieceGroup("std_135", new int[][]{ {10, 9, 8} }));
		
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
			boolean b45135 = s.doesSupports45_135();
			
			for(RailPiece r : E.values()){
				if(!b45135 && r.is45_135())
					continue;
				
				
				ret.add(prefix + r.getName());
			}
			
		}
		return ret;
	}
	
	public static RailPiece getPiece(int id){
		return E.get(id);
	}
	
	public static RailPieceGroup getGroup(String s){
		return G.get(s);
	}
	
	
	static private HashMap<Integer, RailPiece> E = new HashMap();
	static private HashMap<String, Integer> EL = new HashMap();
	
	static private HashMap<Integer, RailStyle> S = new HashMap();
	static private HashMap<String, Integer> SL = new HashMap();
	
	static private HashMap<String, RailPieceGroup> G = new HashMap();
	
	
	
}
