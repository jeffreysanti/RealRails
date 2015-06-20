package net.jeffreysanti.mcmod.realrails.common.items;

import java.util.ArrayList;

import net.jeffreysanti.mcmod.realrails.BlockRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampPiece;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.RailPieceGroup.BlockRailPieceGroup;
import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRail;
import net.jeffreysanti.mcmod.realrails.common.blocks.TileEntityRamp;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRailStraight.StraightHeading;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRail3x3 extends Item {

	private final String basename = "railitm_";
	private String name;
	
	private final Block block = BlockRegistry.blockRail;
	
	public ItemRail3x3(String name, String defPrefix, int styleID)
	{
		this.name = RailRegistry.getStyle(styleID).getName() + basename + name;
		GameRegistry.registerItem(this, this.name);
		setUnlocalizedName(RealRails.MODID + ":" + this.name);
		setCreativeTab(CreativeTabs.tabTransport);
		this.styleID = styleID;
		this.defPrefix = defPrefix;
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public int getStyleID(){
		return styleID;
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
		
		ArrayList<BlockRailPieceGroup> poses = new ArrayList();
		poses.add(new BlockRailPieceGroup(pos, defPrefix+"_4"));
		poses.add(new BlockRailPieceGroup(pos.north().west(), defPrefix+"_0"));
		poses.add(new BlockRailPieceGroup(pos.north(), defPrefix+"_1"));
		poses.add(new BlockRailPieceGroup(pos.north().east(), defPrefix+"_2"));
		poses.add(new BlockRailPieceGroup(pos.west(), defPrefix+"_3"));
		poses.add(new BlockRailPieceGroup(pos.east(), defPrefix+"_5"));
		poses.add(new BlockRailPieceGroup(pos.south().west(), defPrefix+"_6"));
		poses.add(new BlockRailPieceGroup(pos.south(), defPrefix+"_7"));
		poses.add(new BlockRailPieceGroup(pos.south().east(), defPrefix+"_8"));
		
		
		if(!ItemRailStraight.checkRailPlacement(poses, block, stack, playerIn, worldIn))
			return false;
		
		for(BlockRailPieceGroup p : poses){
			IBlockState bs = this.block.onBlockPlaced(worldIn, p.p, side, hitX, hitY, hitZ, 0, playerIn);
			if(placeBlockAt(stack, playerIn, worldIn, p.p, side, hitX, hitY, hitZ, bs)){
            	onBlockPlacedAt(worldIn, p.p, playerIn, poses);
            }
			
		}
		--stack.stackSize;
		worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
		return true;
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
	
	
	void onBlockPlacedAt(World worldIn, BlockPos pos, EntityPlayer playerIn, ArrayList<BlockRailPieceGroup> poses){
		if(worldIn.getTileEntity(pos) == null){
			return;
		}
		TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(pos);
		te.setRampStyle(styleID);
		
		for(BlockRailPieceGroup p : poses){
			if(p.p.equals(pos)){
				te.setRailID(RailRegistry.getPiece(p.railPiece).getElemID());
				break;
			}
		}
		te.syncTileEntity();
	}
	
	
	
	private int styleID;
	private String defPrefix;
}
