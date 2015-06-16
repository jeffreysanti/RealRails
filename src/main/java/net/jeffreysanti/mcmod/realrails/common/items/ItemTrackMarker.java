package net.jeffreysanti.mcmod.realrails.common.items;

import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTrackMarker extends Item {
	private final String name = "trackMarker";
	
	public ItemTrackMarker()
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
		if(!(worldIn.getBlockState(pos).getBlock() instanceof BlockRail)){
			return true;
		}
		TileEntityRail ent = (TileEntityRail)worldIn.getTileEntity(pos);
		if(ent == null){
			return true;
		}
		if(playerIn.isSneaking()){
			ent.setRailID(ent.getRailID() -1);
		}else{
			ent.setRailID(ent.getRailID() +1);
		}
		Minecraft.getMinecraft().thePlayer.sendChatMessage("ID:" + ent.getRailID() + " / Style:"+ent.getStyleID());
		ent.syncTileEntity();
		return false;
    }
}
