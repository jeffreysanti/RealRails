package net.jeffreysanti.mcmod.realrails.common.items;

import java.util.ArrayList;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.CustomCreativeTabs;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampPiece;
import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRamp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRailStraight extends Item {

	public static enum StraightHeading{
		ST_EW_0,
		ST_NS_90,
		ST_NW_135,
		ST_NE_45
	};
	
	public static class BlockRailPieceGroup{
		public BlockRailPieceGroup(BlockPos p, String railPiece){
			this.p = p;
			this.railPiece = railPiece;
		}
		
		public String railPiece;
		public BlockPos p;
	};
	
	
	private final String basename = "railitm_straight";
	private String name = "railitm_straight";
	
	private final Block block = BlockRegistry.blockRail;
	
	public ItemRailStraight(int styleID)
	{
		name = RailRegistry.getStyle(styleID).getName() + basename;
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(RealRails.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabTransport);
		this.styleID = styleID;
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public int getStyleID(){
		return styleID;
	}
	
	public static boolean checkRailPlacement(ArrayList<BlockRailPieceGroup> poses, Block block, ItemStack stack, EntityPlayer playerIn, World worldIn){
		boolean rise = false;
		for(BlockRailPieceGroup p : poses){
			if(!block.isReplaceable(worldIn, p.p)){
	            rise = true;
	            break;
	        }
		}
		if(rise){
			for(int i=0; i<poses.size(); i++){
				poses.get(i).p = poses.get(i).p.up();
			}
		}
		for(BlockRailPieceGroup p : poses){
			if(!playerIn.canPlayerEdit(p.p, EnumFacing.UP, stack))
				return false;
			if(p.p.getY() == 255)
				return false;
			if(!playerIn.canPlayerEdit(p.p, EnumFacing.UP, stack))
				return false;
			if(!worldIn.canBlockBePlaced(block, p.p, false, EnumFacing.UP, (Entity)null, stack))
				return false;
		}
		
		for(BlockRailPieceGroup p : poses){
			if(!worldIn.getBlockState(p.p.down()).getBlock().isBlockSolid(worldIn, p.p.down(), EnumFacing.UP))
				return false;
			if(!worldIn.getBlockState(p.p.down()).getBlock().isOpaqueCube())
				return false;
		}
		
		return true;
	}
	
	
	
	@Override
	// COPIED FROM ItemBlock -> Cannot Let ItemRegigistry see inheritance
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
		if(block == Blocks.snow_layer && ((Integer)iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1){
            side = EnumFacing.UP;
        }
		if (stack.stackSize == 0){
            return false;
        }
		
		float heading = playerIn.getRotationYawHead();
		if(heading < 0){
			heading += 360;
		}
		
		StraightHeading head;
		ArrayList<BlockRailPieceGroup> poses = new ArrayList();
		if(heading < 22.5 || heading > 337.5){
			head = StraightHeading.ST_NS_90;
			if(hitX > 0.5){
				poses.add(new BlockRailPieceGroup(pos, "std_90_a"));
				poses.add(new BlockRailPieceGroup(pos.east(), "std_90_b"));
			}else{
				poses.add(new BlockRailPieceGroup(pos, "std_90_b"));
				poses.add(new BlockRailPieceGroup(pos.west(), "std_90_a"));
			}
		}else if(heading < 67.5){
			head = StraightHeading.ST_NE_45;
			poses.add(new BlockRailPieceGroup(pos, "std_45_b"));
			poses.add(new BlockRailPieceGroup(pos.west(), "std_45_a"));
			poses.add(new BlockRailPieceGroup(pos.east(), "std_45_c"));
		}else if(heading < 112.5){
			head = StraightHeading.ST_EW_0;
			if(hitZ > 0.5){
				poses.add(new BlockRailPieceGroup(pos, "std_0_a"));
				poses.add(new BlockRailPieceGroup(pos.south(), "std_0_b"));
			}else{
				poses.add(new BlockRailPieceGroup(pos, "std_0_b"));
				poses.add(new BlockRailPieceGroup(pos.north(), "std_0_a"));
			}
		}else if(heading < 157.5){
			head = StraightHeading.ST_NW_135;
			poses.add(new BlockRailPieceGroup(pos, "std_135_b"));
			poses.add(new BlockRailPieceGroup(pos.east(), "std_135_a"));
			poses.add(new BlockRailPieceGroup(pos.west(), "std_135_c"));
		}else if(heading < 202.5){
			head = StraightHeading.ST_NS_90;
			if(hitX > 0.5){
				poses.add(new BlockRailPieceGroup(pos, "std_90_a"));
				poses.add(new BlockRailPieceGroup(pos.east(), "std_90_b"));
			}else{
				poses.add(new BlockRailPieceGroup(pos, "std_90_b"));
				poses.add(new BlockRailPieceGroup(pos.west(), "std_90_a"));
			}
		}else if(heading < 247.5){
			head = StraightHeading.ST_NE_45;
			poses.add(new BlockRailPieceGroup(pos, "std_45_b"));
			poses.add(new BlockRailPieceGroup(pos.west(), "std_45_a"));
			poses.add(new BlockRailPieceGroup(pos.east(), "std_45_c"));
		}else if(heading < 292.5){
			head = StraightHeading.ST_EW_0;
			if(hitZ > 0.5){
				poses.add(new BlockRailPieceGroup(pos, "std_0_a"));
				poses.add(new BlockRailPieceGroup(pos.south(), "std_0_b"));
			}else{
				poses.add(new BlockRailPieceGroup(pos, "std_0_b"));
				poses.add(new BlockRailPieceGroup(pos.north(), "std_0_a"));
			}
		}else{
			head = StraightHeading.ST_NW_135;
			poses.add(new BlockRailPieceGroup(pos, "std_135_b"));
			poses.add(new BlockRailPieceGroup(pos.east(), "std_135_a"));
			poses.add(new BlockRailPieceGroup(pos.west(), "std_135_c"));
		}
		
		boolean allIncline = true;
		for(BlockRailPieceGroup p : poses){
			if(worldIn.getBlockState(p.p).getBlock() != BlockRegistry.blockRamp){
				allIncline = false;
				break;
			}
		}
		
		if(allIncline && RailRegistry.getStyle(styleID).doesSupportIncline() && 
				(head == StraightHeading.ST_EW_0 || head == StraightHeading.ST_NS_90)){
			// in this case poses holds both positions which both should be ramps of the SAME type!
			IBlockState bsfirst = worldIn.getBlockState(poses.get(0).p);
			RampPiece rp = null;
			for(BlockRailPieceGroup p : poses){
				IBlockState bs = worldIn.getBlockState(p.p);
				if(bs.getBlock() != BlockRegistry.blockRamp)
					return false;
				TileEntityRamp te = (TileEntityRamp)worldIn.getTileEntity(p.p);
				if(te == null)
					return false;
				if(te.getTypeID() != ((TileEntityRamp)worldIn.getTileEntity(poses.get(0).p)).getTypeID())
					return false;
				rp = RampRegistry.getRamp(te.getTypeID());
				
				if(p.railPiece.equals("std_0_a"))
					p.railPiece = "inc_0_a";
				if(p.railPiece.equals("std_0_b"))
					p.railPiece = "inc_0_b";
				if(p.railPiece.equals("std_90_a"))
					p.railPiece = "inc_90_a";
				if(p.railPiece.equals("std_90_b"))
					p.railPiece = "inc_90_b";
			}
			if(rp.getFacing() == FACING.RAMP_EAST && (head != StraightHeading.ST_EW_0))
				return false;
			if(rp.getFacing() == FACING.RAMP_WEST && (head != StraightHeading.ST_EW_0))
				return false;
			if(rp.getFacing() == FACING.RAMP_NORTH && (head != StraightHeading.ST_NS_90))
				return false;
			if(rp.getFacing() == FACING.RAMP_SOUTH && (head != StraightHeading.ST_NS_90))
				return false;
			
			// all okay
			for(BlockRailPieceGroup p : poses){
				TileEntityRamp te = (TileEntityRamp)worldIn.getTileEntity(p.p);
				int rampType = te.getTypeID();
				int rampStyle = te.getStyleID();
				
				te.markForDeletion();
				worldIn.destroyBlock(p.p, false);
				
				IBlockState bs = this.block.onBlockPlaced(worldIn, p.p, side, hitX, hitY, hitZ, 0, playerIn);
				if(placeBlockAt(stack, playerIn, worldIn, p.p, side, hitX, hitY, hitZ, bs)){
	            	onBlockPlacedAt(worldIn, p.p, playerIn, head, poses, rampType, rampStyle);
	            }
			}
			--stack.stackSize;
			worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
			return true;
		}else{
			if(!checkRailPlacement(poses, block, stack, playerIn, worldIn))
				return false;
			
			for(BlockRailPieceGroup p : poses){
				IBlockState bs = this.block.onBlockPlaced(worldIn, p.p, side, hitX, hitY, hitZ, 0, playerIn);
				if(placeBlockAt(stack, playerIn, worldIn, p.p, side, hitX, hitY, hitZ, bs)){
	            	onBlockPlacedAt(worldIn, p.p, playerIn, head, poses, 1, 1);
	            }
				
			}
			--stack.stackSize;
			worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
			return true;
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
	
	
	void onBlockPlacedAt(World worldIn, BlockPos pos, EntityPlayer playerIn, StraightHeading head, ArrayList<BlockRailPieceGroup> poses, int rampType, int rampStyle){
		if(worldIn.getTileEntity(pos) == null){
			return;
		}
		TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(pos);
		te.setRampStyle(styleID);
		te.setRampType(rampType);
		te.setRampStyle(rampStyle);
		
		for(BlockRailPieceGroup p : poses){
			if(p.p.equals(pos)){
				te.setRailID(RailRegistry.getPiece(p.railPiece).getElemID());
				break;
			}
		}
		te.syncTileEntity();
	}
	
	
	
	private int styleID;
	
}
