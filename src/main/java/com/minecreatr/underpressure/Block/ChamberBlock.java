package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.reference.Textures;
import com.minecreatr.underpressure.tile.ChamberTile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created on 6/23/2014
 */
public class ChamberBlock extends BasicBlock{

    private IIcon glassIcon;
    public ChamberBlock(){
        super(Material.rock, ModBlocks.CHAMBER_BLOCK_NAME);
        this.setBlockTextureName(Textures.CHAMBER_BLOCK_TEXTURE);
    }


}
