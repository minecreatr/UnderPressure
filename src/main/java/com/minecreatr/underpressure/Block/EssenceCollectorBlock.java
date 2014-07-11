package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.reference.Textures;
import com.minecreatr.underpressure.tile.ChamberTile;
import com.minecreatr.underpressure.tile.EssenseCollectorTile;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/28/2014
 */
public class EssenceCollectorBlock extends BasicBlock{

    public EssenceCollectorBlock(){
        super(Material.rock, ModBlocks.ESSENCE_COLLECTOR_BLOCK_NAME);
        this.setBlockTextureName(Textures.INFUSION_BLOCK_ACTIVE);
    }

    public boolean hasTileEntity(int meta){
        return true;
    }

    public TileEntity createTileEntity(World world, int meta){
        return new EssenseCollectorTile(1000);
    }
}
