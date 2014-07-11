package com.minecreatr.underpressure;

import com.minecreatr.underpressure.Block.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created on 7/10/2014
 */
public class UnderPressureCreativeTab extends CreativeTabs{
    public UnderPressureCreativeTab(String tabLabel){
        super(tabLabel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return Item.getItemFromBlock(ModBlocks.essenceCollectorBlock);
    }

}
