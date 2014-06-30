package com.minecreatr.underpressure;

import net.minecraft.item.Item;

import java.util.HashMap;

/**
 * Created on 6/26/2014
 */
public class InfusionRegistry {

    protected static HashMap<Item, Infusion>  infusions = new HashMap<Item, Infusion>();

    public static void addInfusion(Item input, Item output, int energy){
        infusions.put(input, new Infusion(input, output, energy));
    }

    public static boolean isInfusable(Item item){
        return infusions.containsKey(item);
    }

    public static int getEnergyRequired(Item item){
        Infusion infusion = infusions.get(item);
        return infusion.energy;
    }

    public static Item getOutput(Item input){
        return infusions.get(input).output;
    }
}
