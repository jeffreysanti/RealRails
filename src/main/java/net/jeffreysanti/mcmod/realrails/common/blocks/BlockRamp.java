package net.jeffreysanti.mcmod.realrails.common.blocks;

import java.util.List;
import java.util.Random;

import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampPiece;
import net.jeffreysanti.mcmod.realrails.common.RampPiece.FACING;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRamp extends BlockContainer {

	public static final IUnlistedProperty<Integer> UP_STYLE = new IUnlistedProperty<Integer>() {
	    @Override
	    public String getName() {
	        return "ramp.style";
	    }
	    @Override
	    public boolean isValid(Integer value) {
	        return true;
	    }
	    @Override
	    public Class<Integer> getType() {
	        return Integer.class;
	    }
	    @Override
	    public String valueToString(Integer value) {
	        return value.toString();
	    }
    };
    
    public static final IUnlistedProperty<Integer> UP_TYPE = new IUnlistedProperty<Integer>() {
	    @Override
	    public String getName() {
	        return "ramp.type";
	    }
	    @Override
	    public boolean isValid(Integer value) {
	        return true;
	    }
	    @Override
	    public Class<Integer> getType() {
	        return Integer.class;
	    }
	    @Override
	    public String valueToString(Integer value) {
	        return value.toString();
	    }
    };
	
	
	private final String name = "ramp";
	
	public BlockRamp() {
		super(Material.iron);
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName(RealRails.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabMisc);
		this.setHardness(3.0f);
		this.setLightOpacity(255);
		
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRamp();
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
    protected BlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty[] {  }, new IUnlistedProperty[]{ UP_TYPE, UP_STYLE });
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if(state instanceof IExtendedBlockState) {
        	TileEntityRamp te = (TileEntityRamp)world.getTileEntity(pos);
        	if(te == null){
        		return state;
        	}
            return ((IExtendedBlockState)state)
            		.withProperty(UP_TYPE, te.getTypeID())
            		.withProperty(UP_STYLE, te.getStyleID());
        }
        return state;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public EnumWorldBlockLayer getBlockLayer()
    {
      return EnumWorldBlockLayer.SOLID;
    }
    @Override
    public int getRenderType() {
      return 3;
    }
    

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public boolean isFullCube()
    {
        return false;
    }
    

    @Override
    public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        if(side == EnumFacing.UP)
        	return false;
        return true;
    }
   
    @Override
    public boolean getUseNeighborBrightness()
    {
        return true;
    }
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
    	List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
    	return ret;
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	// drop item and THEN destroy tile entity (in super method)
    	TileEntityRamp te = (TileEntityRamp)worldIn.getTileEntity(pos);
    	if(te != null && !te.isMarkedForDeletion()){
    		ItemStack stack = new ItemStack(ItemRegistry.getItemRampOfStyle(te.getStyleID()), 1);
    		worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack));
    	}
        super.breakBlock(worldIn, pos, state);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        TileEntityRamp te = (TileEntityRamp)worldIn.getTileEntity(pos);
		if(te == null){
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			return;
		}
		RampPiece p = RampRegistry.getRamp(te.getTypeID());
		if(p == null){
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			return;
		}
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, p.getMaxY(), 1.0F);
    }
    
    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
    	if(!rampCollisionBoxes(worldIn, pos, state, mask, list, collidingEntity)){
    		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
    	}
    }

    // reusable code for rails :D
    public static final int MAXCOLLISIONITERS = 4;
	public static boolean rampCollisionBoxes(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		double slope, iterSlope = (1.0/MAXCOLLISIONITERS);
		RampPiece p = null;
		if(worldIn.getTileEntity(pos) instanceof TileEntityRamp){
			TileEntityRamp te = (TileEntityRamp)worldIn.getTileEntity(pos);
			p = RampRegistry.getRamp(te.getTypeID());
			if(p == null){
				return false;
			}
			slope = p.getMaxY() - p.getMinY();
		}else if(worldIn.getTileEntity(pos) instanceof TileEntityRail){
			TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(pos);
			if(RailRegistry.getPiece(te.getRailID()) == null ||
					!RailRegistry.getPiece(te.getRailID()).isInclined()){
				return false;
			}
			p = RampRegistry.getRamp(te.getRampType());
			if(p == null){
				return false;
			}
			slope = p.getMaxY() - p.getMinY();
		}else{
			return false;
		}
		
		if(p.getFacing() == FACING.RAMP_EAST){ // Up along +X
			double z1 = pos.getZ();
	    	double z2 = pos.getZ() + 1.0;
	    	
	    	for(int i=0; i<MAXCOLLISIONITERS; i++){
	    		double normMin = i*iterSlope;
	    		double normMax = (i+1.0)*iterSlope;
	    		
	    		double x1 = pos.getX() + normMin;
	    		double x2 = pos.getX() + normMax;
	    		double y1 = pos.getY() + p.getMinY() + normMin*slope;
	    		double y2 = pos.getY() + p.getMinY() + normMax*slope;
	    		if(mask.intersectsWith(new AxisAlignedBB(x1, y1, z1, x2, y2, z2)))
	    			list.add(new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
	    	}
		}else if(p.getFacing() == FACING.RAMP_WEST){ // up along -X
			double z1 = pos.getZ();
	    	double z2 = pos.getZ() + 1.0;
	    	
	    	for(int i=0; i<MAXCOLLISIONITERS; i++){
	    		double normMin = i*iterSlope;
	    		double normMax = (i+1.0)*iterSlope;
	    		
	    		double x1 = pos.getX() + normMin;
	    		double x2 = pos.getX() + normMax;
	    		double y1 = pos.getY() + p.getMaxY() - normMin*slope;
	    		double y2 = pos.getY() + p.getMaxY() - normMax*slope;
	    		if(mask.intersectsWith(new AxisAlignedBB(x1, y1, z1, x2, y2, z2)))
	    			list.add(new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
	    	}
		}if(p.getFacing() == FACING.RAMP_SOUTH){ // Up along +Z
			double x1 = pos.getX();
	    	double x2 = pos.getX() + 1.0;
	    	
	    	for(int i=0; i<MAXCOLLISIONITERS; i++){
	    		double normMin = i*iterSlope;
	    		double normMax = (i+1.0)*iterSlope;
	    		
	    		double z1 = pos.getZ() + normMin;
	    		double z2 = pos.getZ() + normMax;
	    		double y1 = pos.getY() + p.getMinY() + normMin*slope;
	    		double y2 = pos.getY() + p.getMinY() + normMax*slope;
	    		if(mask.intersectsWith(new AxisAlignedBB(x1, y1, z1, x2, y2, z2)))
	    			list.add(new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
	    	}
		}else{ // NORTH rising up along -Z
			double x1 = pos.getX();
	    	double x2 = pos.getX() + 1.0;
	    	
	    	for(int i=0; i<MAXCOLLISIONITERS; i++){
	    		double normMin = i*iterSlope;
	    		double normMax = (i+1.0)*iterSlope;
	    		
	    		double z1 = pos.getZ() + normMin;
	    		double z2 = pos.getZ() + normMax;
	    		double y1 = pos.getY() + p.getMaxY() - normMin*slope;
	    		double y2 = pos.getY() + p.getMaxY() - normMax*slope;
	    		if(mask.intersectsWith(new AxisAlignedBB(x1, y1, z1, x2, y2, z2)))
	    			list.add(new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
	    	}
		}
		return true;
	}
    
}
