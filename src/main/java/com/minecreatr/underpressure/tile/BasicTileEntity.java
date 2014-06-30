package com.minecreatr.underpressure.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created on 6/26/2014
 */
public class BasicTileEntity extends TileEntity{

    protected byte state;


    public BasicTileEntity(){
        state = 0;
    }
    public BasicTileEntity(byte state){
        this.state=state;
    }
    public short getState()
    {
        return state;
    }

    public void setState(byte state)
    {
        this.state = state;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound){
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey("state"))
        {
            this.state = nbtTagCompound.getByte("state");
        }
        readData(nbtTagCompound);
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound){
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setByte("state", state);
        writeData(nbtTagCompound);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    public void writeData(NBTTagCompound compound) {}

    public void readData(NBTTagCompound compound) {}

}
