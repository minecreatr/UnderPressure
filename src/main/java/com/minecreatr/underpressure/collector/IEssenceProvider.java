package com.minecreatr.underpressure.collector;

/**
 * Created on 6/28/2014
 */
public interface IEssenceProvider {


    public int getEssence();
    public int getOutputPerTick();
    public int takeEssence(int amount);
}
