package net.jeffreysanti.mcmod.realrails.common.blocks;

import scala.actors.threadpool.Arrays;
import net.jeffreysanti.mcmod.realrails.RealRails;
import net.jeffreysanti.mcmod.realrails.common.RailPiece;
import net.jeffreysanti.mcmod.realrails.common.RailPieceGroup;
import net.jeffreysanti.mcmod.realrails.common.RailRegistry;
import net.jeffreysanti.mcmod.realrails.common.RampRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRail extends TileEntity {
	
	public TileEntityRail(){
		super();
		
		elmID = 1;
		styleID = 1;
		rampType = 1;
    	rampStyle = 1;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound){
        super.readFromNBT(tagCompound);
        this.readSyncData(tagCompound);
    }
	private void readSyncData(NBTTagCompound tagCompound){
		this.elmID = tagCompound.getInteger("elmID");
        this.styleID = tagCompound.getInteger("styleID");
        
        if(RailRegistry.getTextureName(this.styleID, this.elmID).isEmpty()){
        	this.elmID = 1;
        	this.styleID = 1;
    	}
        if(RailRegistry.getPiece(this.elmID).isInclined()){
        	this.rampType = tagCompound.getInteger("rampType");
        	this.rampStyle = tagCompound.getInteger("rampStyle");
        	
        	if(RampRegistry.getRamp(this.rampType) == null){
            	this.rampType = 1;
            }
            if(RampRegistry.getStyle(this.rampStyle).isEmpty()){
            	this.rampStyle = 1;
            }
        }
	}
   
    @Override
    public void writeToNBT(NBTTagCompound tagCompound){
        super.writeToNBT(tagCompound);
        this.writeSyncData(tagCompound);
    }
    private void writeSyncData(NBTTagCompound tagCompound){
    	tagCompound.setInteger("elmID", this.elmID);
        tagCompound.setInteger("styleID", this.styleID);
        
        if(RailRegistry.getPiece(this.elmID).isInclined()){
        	tagCompound.setInteger("rampType", this.rampType);
            tagCompound.setInteger("rampStyle", this.rampStyle);
        }
	}
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncData(syncData);
        return new S35PacketUpdateTileEntity(this.pos, 1, syncData);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
    	this.readSyncData(pkt.getNbtCompound());
    	worldObj.markBlockRangeForRenderUpdate(pos, pos);
    }
    
    public void syncTileEntity(){
    	worldObj.markBlockForUpdate(this.pos);
    	markDirty();
    }
    
    public int getRailID(){
    	return elmID;
    }
    public int getStyleID(){
    	return styleID;
    }
    
    public int getRampType(){
    	return rampType;
    }
    public int getRampStyle(){
    	return rampStyle;
    }
    
    public void setRailID(int i){
    	if(!RailRegistry.getTextureName(styleID, i).isEmpty()){
    		elmID = i;
    	}
    }
    
    public void setStyleID(int i){
    	if(!RailRegistry.getTextureName(i, elmID).isEmpty()){
    		styleID = i;
    	}
    }
    
    public void setRampType(int i){
    	if(RampRegistry.getRamp(i) != null){
    		rampType = i;
    	}
    }
    
    public void setRampStyle(int i){
    	if(!RampRegistry.getStyle(this.styleID).isEmpty()){
    		rampStyle = i;
        }
    }
    
    public void onBreak(World worldIn, BlockPos pos, IBlockState state){
    	RailPiece p = RailRegistry.getPiece(elmID);
    	if(p == null)
    		return;
    	RailPieceGroup g = RailRegistry.getGroup(p.getGroup());
    	if(g == null)
    		return;
    	
    	for(BlockPos bp : g.getAllTrackPieces(pos, elmID)){
    		worldIn.setBlockToAir(bp);
    	}
    }
    
    
    
    private int elmID;
    private int styleID;
    
    private int rampType;
    private int rampStyle;
	
    private static final String name = "rail";
	public static void register(){
		GameRegistry.registerTileEntity(TileEntityRail.class, RealRails.MODID + "_" + name);
	}
}
