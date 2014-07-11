package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.Infusion;
import com.minecreatr.underpressure.InfusionRegistry;
import com.minecreatr.underpressure.reference.Textures;
import com.minecreatr.underpressure.tile.ChamberTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created on 6/27/2014
 */
public class InfusionBlock extends BasicBlock{

    public InfusionBlock(){
        super(Material.rock, ModBlocks.INFUSION_BLOCK_NAME);
        this.setBlockTextureName(Textures.TRANSPARENT_TEXTURE);
    }

    public boolean hasTileEntity(int meta){
        return true;
    }

    public TileEntity createTileEntity(World world, int meta){
        return new ChamberTile();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
        if (world.getTileEntity(x, y, z) instanceof ChamberTile){
            if (player.inventory.getCurrentItem()!=null && ((ChamberTile)world.getTileEntity(x, y, z)).getStackInSlot(0)==null) {
                if (InfusionRegistry.isInfusable(player.inventory.getCurrentItem().getItem())) {
                    //if (Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()) == ModBlocks.chamberBlock || Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()) == ModBlocks.infusionBlock);
                    ChamberTile tile = (ChamberTile) world.getTileEntity(x, y, z);
                    ItemStack stack = player.inventory.mainInventory[player.inventory.currentItem].splitStack(1);
                    tile.setInventorySlotContents(0, stack);
                    return true;
                }
            }
            else if(player.inventory.getCurrentItem()==null){
                ChamberTile tile = (ChamberTile) world.getTileEntity(x, y, z);
                ItemStack stack = tile.getStackInSlot(0);
                player.inventory.mainInventory[player.inventory.currentItem]=stack;
                tile.decrStackSize(0, 1);
            }
        }
        return false;
    }

    public boolean isOpaqueCube(){
        return false;
    }
}
