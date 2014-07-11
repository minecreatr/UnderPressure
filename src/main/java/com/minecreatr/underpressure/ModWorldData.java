package com.minecreatr.underpressure;

import com.minecreatr.underpressure.util.BlockData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

import java.util.ArrayList;

/**
 * Created on 7/9/2014
 */
public class ModWorldData extends WorldSavedData{

    final static String key = "underpressure.worldData";
    public ArrayList<BlockData> portals = new ArrayList<BlockData>();

    public ModWorldData(){
        super(key);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        // Get your data from the nbt here
        int portalsN = nbt.getInteger("portalsN");
        NBTTagList portalsL = nbt.getTagList("portals", portalsN);
        for (int i=0;i<portalsL.tagCount();i++){
            portals.add(BlockData.fromPrim(portalsL.getCompoundTagAt(i).getIntArray("locs")));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        // Put your data in the nbt here
        NBTTagList portalsL = new NBTTagList();
        nbt.setInteger("portalsN", portals.size());
        for (int i=0;i<portals.size();i++){
            NBTTagCompound cur = new NBTTagCompound();
            cur.setIntArray("locs", portals.get(i).toPrim());
            portalsL.appendTag(cur);
        }
        nbt.setTag("portals", portalsL);
    }

    public static ModWorldData forWorld(World world) {
        // Retrieves the MyWorldData instance for the given world, creating it if necessary
        MapStorage storage = world.perWorldStorage;
        ModWorldData result = (ModWorldData)storage.loadData(ModWorldData.class, key);
        if (result == null) {
            result = new ModWorldData();
            storage.setData(key, result);
        }
        return result;
    }

    public ArrayList<BlockData> getPortals(){
        return this.portals;
    }
}
