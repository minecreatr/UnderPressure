package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.UnderPressure;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created on 6/23/2014
 */
public class BasicBlock extends Block{

    public BasicBlock(Material material, String name){
        super(material);
        this.setBlockName(name);
        this.setCreativeTab(UnderPressure.tab);
    }
}
