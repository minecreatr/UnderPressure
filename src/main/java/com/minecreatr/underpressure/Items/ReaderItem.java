package com.minecreatr.underpressure.Items;

import com.minecreatr.underpressure.ModWorldData;
import com.minecreatr.underpressure.UnderPressure;
import com.minecreatr.underpressure.collector.IEssenceTile;
import com.minecreatr.underpressure.util.BlockData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created on 6/28/2014
 */
public class ReaderItem extends Item{

    public ReaderItem(){
        setMaxStackSize(1);
        setUnlocalizedName(ModItems.READER_ITEM_NAME);
        setCreativeTab(UnderPressure.tab);
    }
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
        if (world.getTileEntity(x, y, z)!=null){
            if (world.getTileEntity(x, y, z) instanceof IEssenceTile){
                IEssenceTile tile = (IEssenceTile) world.getTileEntity(x, y, z);
                player.addChatMessage(new ChatComponentText("Essence: "+tile.getEssence()+"/"+tile.getMaxEssence()));
                return true;
            }
        }
        return false;
    }

}
