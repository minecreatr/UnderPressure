package com.minecreatr.underpressure.util;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/26/2014
 */
public class BlockData {

    public int x;
    public int y;
    public int z;
    public BlockData(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public int[] toPrim(){
        int[] out = {x, y, z};
        return out;
    }

    public static BlockData fromPrim(int[] in){
        return new BlockData(in[0], in[1], in[2]);
    }

    public TileEntity getTile(World worldObj){
        return worldObj.getTileEntity(x, y, z);
    }

    public Block getBlock(World worldObj){
        return worldObj.getBlock(x, y, z);
    }
}
