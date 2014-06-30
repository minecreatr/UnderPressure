package com.minecreatr.underpressure.util;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created on 6/29/2014
 */
public class ObfuscationHelper {

    public static ResourceLocation getParticleTexture() {
        return ReflectionHelper.getPrivateValue(EffectRenderer.class, null, LibObfuscation.PARTICLE_TEXTURES);
    }
}
