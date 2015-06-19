package net.jeffreysanti.mcmod.realrails.common.items;

import net.jeffreysanti.mcmod.realrails.RealRails;
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

public class ItemRampStyler extends Item {
	private final String name = "rampStyler";
	
	public ItemRampStyler()
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
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockRamp){
			TileEntityRamp ent = (TileEntityRamp)worldIn.getTileEntity(pos);
			if(ent == null){
				return true;
			}
			if(playerIn.isSneaking()){
				ent.setStyleID(ent.getStyleID() -1);
			}else{
				ent.setStyleID(ent.getStyleID() +1);
			}
			Minecraft.getMinecraft().thePlayer.sendChatMessage("ID:" + ent.getTypeID() + " / Style:"+ent.getStyleID());
			ent.syncTileEntity();
			return false;
		}else if(worldIn.getBlockState(pos).getBlock() instanceof BlockRail){
			TileEntityRail ent = (TileEntityRail)worldIn.getTileEntity(pos);
			if(ent == null){
				return true;
			}
			if(playerIn.isSneaking()){
				ent.setRampStyle(ent.getRampStyle() -1);
			}else{
				ent.setRampStyle(ent.getRampStyle() +1);
			}
			ent.syncTileEntity();
			return false;
		}
		
		return true;
    }
}