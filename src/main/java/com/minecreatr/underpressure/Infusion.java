package com.minecreatr.underpressure;

import net.minecraft.item.Item;

/**
 * Created on 6/26/2014
 */
public class Infusion {

    protected Item input;
    protected Item output;
    protected int energy;

    public Infusion(Item input, Item output, int energy){
        this.input=input;
        this.output=output;
        this.energy=energy;
    }

}
