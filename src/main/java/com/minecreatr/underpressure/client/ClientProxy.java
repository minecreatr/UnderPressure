package com.minecreatr.underpressure.client;

import com.minecreatr.underpressure.Block.ChamberBlock;
import com.minecreatr.underpressure.CommonProxy;
import com.minecreatr.underpressure.client.render.InfusionRenderer;
import com.minecreatr.underpressure.tile.ChamberTile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.resources.Language;
import org.omg.PortableInterceptor.ClientRequestInfo;

/**
 * Created on 6/23/2014
 */
public class ClientProxy extends CommonProxy{
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(ChamberTile.class, new InfusionRenderer());
    }
}
