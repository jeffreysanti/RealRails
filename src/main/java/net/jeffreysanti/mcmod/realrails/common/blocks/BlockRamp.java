package net.jeffreysanti.mcmod.realrails.common.blocks;

import net.jeffreysanti.mcmod.realrails.RealRails;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
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
}
