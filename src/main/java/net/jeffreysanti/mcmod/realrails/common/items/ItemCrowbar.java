package net.jeffreysanti.mcmod.realrails.common.items;

import java.util.List;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailPiece;
import net.jeffreysanti.mcmod.realrails.common.RailPieceGroup;
import net.jeffreysanti.mcmod.realrails.common.RailPieceGroup.BlockRailPieceGroup;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRamp;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRamp;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCrowbar extends Item {
	private final String name = "crowbar";
	
	public ItemCrowbar()
	{
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(RealRails.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(worldIn.getBlockState(pos).getBlock() == BlockRegistry.blockRail){
			TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(pos);
			if(te == null){
				return true;
			}
			
			RailPiece p = RailRegistry.getPiece(te.getRailID());
			if(p.getName().startsWith("align_")){
				RailPieceGroup grp = RailRegistry.getGroup(p.getGroup());
				if(grp.getName().equals("align_90_east")){
					RailPieceGroup.subGroups(grp, RailRegistry.getGroup("align_90_west"), worldIn, pos, p.getElemID());
				}else if(grp.getName().equals("align_90_west")){
					RailPieceGroup.subGroups(grp, RailRegistry.getGroup("align_0_north"), worldIn, pos, p.getElemID());
				}else if(grp.getName().equals("align_0_north")){
					RailPieceGroup.subGroups(grp, RailRegistry.getGroup("align_0_south"), worldIn, pos, p.getElemID());
				}else{
					RailPieceGroup.subGroups(grp, RailRegistry.getGroup("align_90_east"), worldIn, pos, p.getElemID());
				}
			}
		}
		return true;
    }
}