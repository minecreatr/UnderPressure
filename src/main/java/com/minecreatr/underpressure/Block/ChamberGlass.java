package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.reference.Textures;

/**
 * Created on 6/27/2014
 */
public class ChamberGlass extends ChamberBlock{

    public ChamberGlass(){
        super();
        this.setBlockTextureName(Textures.CHAMBER_GLASS_TEXTURE);
        this.setBlockName(ModBlocks.CHAMBER_GLASS_NAME);
        this.setLightOpacity(0);
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }
}
