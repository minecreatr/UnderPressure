package com.minecreatr.underpressure.util;

import com.minecreatr.underpressure.ModWorldData;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created on 7/10/2014
 */
public class LocHelper {

    public static BlockData getClosestPortal(World worldObj, int x, int y, int z){
        ArrayList<BlockData> portals = ModWorldData.forWorld(worldObj).portals;
        int curClosestVal = Integer.MAX_VALUE;
        BlockData curClosest = null;
        for (int i=0;i<portals.size();i++){
            BlockData cur = portals.get(i);
            if (MathHelper.getDistance(Math.abs(cur.x-x), Math.abs(cur.y-y), Math.abs(cur.z-z))>curClosestVal){
                curClosestVal=MathHelper.getDistance(Math.abs(cur.x-x), Math.abs(cur.y-y), Math.abs(cur.z-z));
                curClosest = portals.get(i);
            }
        }
        return curClosest;
    }

    public static int getDistanceFromPoint(int x, int y, int z, BlockData point){
        return MathHelper.getDistance(Math.abs(point.x-x), Math.abs(point.y-y), Math.abs(point.z-z));
    }
}
