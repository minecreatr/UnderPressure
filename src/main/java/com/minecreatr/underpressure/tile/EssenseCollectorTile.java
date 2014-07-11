package com.minecreatr.underpressure.tile;

import com.minecreatr.underpressure.collector.IEssenceProvider;
import com.minecreatr.underpressure.collector.IEssenceTile;
import com.minecreatr.underpressure.util.LocHelper;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created on 6/28/2014
 */
public class EssenseCollectorTile extends BasicTileEntity implements IEssenceTile, IEssenceProvider{

    private int essence;
    private int maxEssence;
    private float buildBuffer;

    public EssenseCollectorTile(int maxEssence){
        super();
        this.essence=0;
        this.maxEssence=maxEssence;
    }

    public int getEssence(){
        return this.essence;
    }

    public int getOutputPerTick(){
        return 10;
    }

    public int takeEssence(int amount){
        if (amount>this.essence){
            int x = this.essence;
            this.essence=0;
            return x;
        }
        else {
            this.essence=this.essence-amount;
            return amount;
        }
    }
    @Override
    public void writeData(NBTTagCompound compound) {
        compound.setInteger("essence", this.essence);
        compound.setInteger("maxEssence", this.maxEssence);
        compound.setFloat("buildBuffer", buildBuffer);
    }

    @Override
    public void readData(NBTTagCompound compound) {
        if (compound.hasKey("essence")){
            this.essence=compound.getInteger("essence");
        }
        if (compound.hasKey("maxEssence")){
            this.maxEssence=compound.getInteger("maxEssence");
        }
        if (compound.hasKey("buildBuffer")){
            this.buildBuffer=compound.getFloat("buildBuffer");
        }
    }

    public void updateEntity(){
        if (buildBuffer>=1){
            buildBuffer--;
            essence++;
        }
        if (this.essence<this.maxEssence){
            if(LocHelper.getClosestPortal(worldObj, xCoord, yCoord, zCoord)!=null) {
                if (LocHelper.getDistanceFromPoint(xCoord, yCoord, zCoord, LocHelper.getClosestPortal(worldObj, xCoord, yCoord, zCoord))<=11) {
                    int dist = LocHelper.getDistanceFromPoint(xCoord, yCoord, zCoord, LocHelper.getClosestPortal(worldObj, xCoord, yCoord, zCoord));
                    float toAdd = 1-(dist/11);
                    buildBuffer=buildBuffer+toAdd;
                }
            }
        }
    }

    public int getMaxEssence(){
        return this.maxEssence;
    }

}
