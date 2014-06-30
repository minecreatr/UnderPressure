package com.minecreatr.underpressure.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created on 6/28/2014
 */
public class ModItems {

    public static String READER_ITEM_NAME = "underpressure.readerItem";
    public static String INFUSION_ITEM_BLOCK_NAME = "underpressure.infusionItemBlock";
    public static Item readerItem;

    public static void init(){
        readerItem = new ReaderItem();
        GameRegistry.registerItem(readerItem, READER_ITEM_NAME);
    }
}
