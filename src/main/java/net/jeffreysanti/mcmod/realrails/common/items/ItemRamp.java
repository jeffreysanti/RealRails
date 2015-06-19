package net.jeffreysanti.mcmod.realrails.common.items;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.CustomCreativeTabs;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.BlockRamp;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRamp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRamp extends Item {
	private final String basename = "rampitm";
	private String name = "rampitm";
	
	private final Block block = BlockRegistry.blockRamp;
	
	public ItemRamp(int styleID)
	{
		name = basename + "_" + styleID;
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(RealRails.MODID + ":" + name);
		setCreativeTab(CustomCreativeTabs.tabDecoration);
		this.styleID = styleID;
		this.setFull3D();
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public int getStyleID(){
		return styleID;
	}
	
	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining){
		return null;
	}
	
	
	@Override
	// COPIED FROM ItemBlock -> Cannot Let ItemRegigistry see inheritance
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block == Blocks.snow_layer && ((Integer)iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1)
        {
            side = EnumFacing.UP;
        }
        else if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(side);
        }

        if (stack.stackSize == 0)
        {
            return false;
        }
        else if (!playerIn.canPlayerEdit(pos, side, stack))
        {
            return false;
        }
        else if (pos.getY() == 255 && this.block.getMaterial().isSolid())
        {
            return false;
        }
        else if (worldIn.canBlockBePlaced(this.block, pos, false, side, (Entity)null, stack))
        {
            int i = this.getMetadata(stack.getMetadata());
            IBlockState iblockstate1 = this.block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, i, playerIn);

            if (placeBlockAt(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ, iblockstate1))
            {
            	onBlockPlacedAt(worldIn, pos, playerIn);
                worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
	
	// COPIED FROM ItemBlock -> Cannot Let ItemRegigistry see inheritance
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState, 3)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this.block)
        {
            ItemBlock.setTileEntityNBT(world, pos, stack, player);
            this.block.onBlockPlacedBy(world, pos, state, player, stack);
        }

        return true;
    }
	
	
	void onBlockPlacedAt(World worldIn, BlockPos pos, EntityPlayer playerIn){
		if(worldIn.getTileEntity(pos) == null){
			return;
		}
		TileEntityRamp te = (TileEntityRamp)worldIn.getTileEntity(pos);
		te.setStyleID(styleID);
		if(playerIn.getHorizontalFacing() == EnumFacing.EAST){
			te.setTypeID(RampRegistry.RAMP45_EAST);
		}else if(playerIn.getHorizontalFacing() == EnumFacing.WEST){
			te.setTypeID(RampRegistry.RAMP45_WEST);
		}else if(playerIn.getHorizontalFacing() == EnumFacing.SOUTH){
			te.setTypeID(RampRegistry.RAMP45_SOUTH);
		}else{ // north
			te.setTypeID(RampRegistry.RAMP45_NORTH);
		}
		te.syncTileEntity();
	}
	
	
	
	private int styleID;
}