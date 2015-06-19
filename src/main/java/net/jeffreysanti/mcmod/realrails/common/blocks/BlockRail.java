package net.jeffreysanti.mcmod.realrails.common.blocks;

import java.util.List;

import net.jeffreysanti.mcmod.realrails.ItemRegistry;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampPiece;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.jeffreysanti.mcmod.realrails.common.items.ItemRamp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRail extends BlockContainer {

	public static final IUnlistedProperty<Integer> UP_STYLE = new IUnlistedProperty<Integer>() {
	    @Override
	    public String getName() {
	        return "rail.style";
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
    
    public static final IUnlistedProperty<Integer> UP_ELEM = new IUnlistedProperty<Integer>() {
	    @Override
	    public String getName() {
	        return "rail.elem";
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
    
    public static final IUnlistedProperty<Integer> UP_RAMPTYPE = new IUnlistedProperty<Integer>() {
	    @Override
	    public String getName() {
	        return "rail.ramptype";
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
    
    public static final IUnlistedProperty<Integer> UP_RAMPSTYLE = new IUnlistedProperty<Integer>() {
	    @Override
	    public String getName() {
	        return "rail.rampstyle";
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
	
	
	private final String name = "rail";
	
	public BlockRail() {
		super(Material.iron);
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName(RealRails.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabMisc);
		this.setHardness(10.0f);
		
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRail();
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
    protected BlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty[] {  }, 
        		new IUnlistedProperty[]{ UP_ELEM, UP_STYLE, UP_RAMPTYPE, UP_RAMPSTYLE });
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if(state instanceof IExtendedBlockState) {
        	TileEntityRail te = (TileEntityRail)world.getTileEntity(pos);
        	if(te == null){
        		return state;
        	}
            return ((IExtendedBlockState)state)
            		.withProperty(UP_ELEM, te.getRailID())
            		.withProperty(UP_STYLE, te.getStyleID())
            		.withProperty(UP_RAMPTYPE, te.getRampType())
            		.withProperty(UP_RAMPSTYLE, te.getRampStyle());
        }
        return state;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public EnumWorldBlockLayer getBlockLayer()
    {
      return EnumWorldBlockLayer.CUTOUT;
    }
    @Override
    public int getRenderType() {
      return 3;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
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
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(pos);
		if(te == null || RailRegistry.getPiece(te.getRailID()) == null){
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
			return;
		}
		if(!RailRegistry.getPiece(te.getRailID()).isInclined() || 
				RampRegistry.getRamp(te.getRampType()) == null){
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
			return;
		}
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, RampRegistry.getRamp(te.getRampType()).getMaxY(), 1.0F);
    }
    
    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
    	if(!BlockRamp.rampCollisionBoxes(worldIn, pos, state, mask, list, collidingEntity)){
    		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
    	}
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
    	TileEntityRail te = (TileEntityRail)worldIn.getTileEntity(pos);
    	if(te != null && RailRegistry.getPiece(te.getRailID()) != null){
    		
    		// drop ramp
    		if(RailRegistry.getPiece(te.getRailID()).isInclined()){
    			ItemStack stack = new ItemStack(ItemRegistry.getItemRampOfStyle(te.getRampStyle()), 1);
    			worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack));
    		}
    		
    		te.onBreak(worldIn, pos, state);
    	}
        super.breakBlock(worldIn, pos, state);
    }
}
