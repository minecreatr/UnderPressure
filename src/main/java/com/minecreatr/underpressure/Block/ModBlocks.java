package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.tile.ChamberTile;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlockWithMetadata;

/**
 * Created on 6/23/2014
 */
public class ModBlocks {

    public static String CHAMBER_BLOCK_NAME = "underpressure.chamberBlock";
    public static String INFUSION_BLOCK_NAME = "underpressure.infusionBlock";
    public static String CHAMBER_GLASS_NAME = "underpressure.chamberGlass";
    public static String ESSENCE_COLLECTOR_BLOCK_NAME = "underpressure.essenceCollectorBlock";
    public static Block chamberBlock;
    public static Block infusionBlock;
    public static Block chamberGlass;
    public static Block essenceCollectorBlock;

    public static void init(){
        chamberBlock=new ChamberBlock();
        GameRegistry.registerBlock(chamberBlock, CHAMBER_BLOCK_NAME);

        infusionBlock = new InfusionBlock();
        GameRegistry.registerBlock(infusionBlock, INFUSION_BLOCK_NAME);

        chamberGlass = new ChamberGlass();
        GameRegistry.registerBlock(chamberGlass, CHAMBER_GLASS_NAME);

        essenceCollectorBlock = new EssenceCollectorBlock();
        GameRegistry.registerBlock(essenceCollectorBlock, ESSENCE_COLLECTOR_BLOCK_NAME);
    }
}
