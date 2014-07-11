package com.minecreatr.underpressure.util;

/**
 * Created on 7/10/2014
 */
public class MathHelper {

    public static int getDistance(int xd, int yd, int zd){
        int t = Math.abs(sqr(xd)+sqr(yd)+sqr(zd));
        return (int) Math.abs(Math.sqrt(t));
    }

    public static int getDistance(int[] locs){
        return getDistance(locs[0], locs[1], locs[2]);
    }

    public static int sqr(int s){
        return s*s;
    }
}
