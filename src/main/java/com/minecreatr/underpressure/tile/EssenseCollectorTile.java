package com.minecreatr.underpressure.tile;

import com.minecreatr.underpressure.collector.IEssenceProvider;
import com.minecreatr.underpressure.collector.IEssenceTile;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created on 6/28/2014
 */
public class EssenseCollectorTile extends BasicTileEntity implements IEssenceTile, IEssenceProvider{

    private int essence;
    private int maxEssence;

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
    }

    @Override
    public void readData(NBTTagCompound compound) {
        if (compound.hasKey("essence")){
            this.essence=compound.getInteger("essence");
        }
        if (compound.hasKey("maxEssence")){
            this.maxEssence=compound.getInteger("maxEssence");
        }
    }

    public void updateEntity(){
        if (this.essence<this.maxEssence){
            this.essence=this.essence+1;
        }
    }

    public int getMaxEssence(){
        return this.maxEssence;
    }

}
