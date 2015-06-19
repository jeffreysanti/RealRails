package net.jeffreysanti.mcmod.realrails.common.blocks;

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

public class TileEntityRamp extends TileEntity {
	
	public TileEntityRamp(){
		super();
		
		typeID = 1;
		styleID = 1;
		forDeletion = false;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound){
        super.readFromNBT(tagCompound);
        this.readSyncData(tagCompound);
    }
	private void readSyncData(NBTTagCompound tagCompound){
		this.typeID = tagCompound.getInteger("typeID");
        this.styleID = tagCompound.getInteger("styleID");
        
        if(RampRegistry.getRamp(this.typeID) == null){
        	this.typeID = 1;
        }
        if(RampRegistry.getStyleTexture(this.styleID).isEmpty()){
        	this.styleID = 1;
        }
	}
   
    @Override
    public void writeToNBT(NBTTagCompound tagCompound){
        super.writeToNBT(tagCompound);
        this.writeSyncData(tagCompound);
    }
    private void writeSyncData(NBTTagCompound tagCompound){
    	tagCompound.setInteger("typeID", this.typeID);
        tagCompound.setInteger("styleID", this.styleID);
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
    
    public int getTypeID(){
    	return typeID;
    }
    public int getStyleID(){
    	return styleID;
    }
    
    public void setTypeID(int i){
    	if(RampRegistry.getRamp(i) != null){
    		typeID = i;
    	}
    }
    
    public void setStyleID(int i){
    	if(!RampRegistry.getStyleTexture(this.styleID).isEmpty()){
    		styleID = i;
        }
    }
    
    public void markForDeletion(){
    	forDeletion = true;
    }
    public boolean isMarkedForDeletion(){
    	return forDeletion;
    }
    
    
    private int typeID;
    private int styleID;
    private boolean forDeletion;
	
    private static final String name = "ramp";
	public static void register(){
		GameRegistry.registerTileEntity(TileEntityRamp.class, RealRails.MODID + "_" + name);
	}
}