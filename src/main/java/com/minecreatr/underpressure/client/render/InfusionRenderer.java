package com.minecreatr.underpressure.client.render;

import com.minecreatr.underpressure.Infusion;
import com.minecreatr.underpressure.client.model.ModelPedestal;
import com.minecreatr.underpressure.reference.Textures;
import com.minecreatr.underpressure.tile.ChamberTile;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created on 6/26/2014
 */
public class InfusionRenderer extends TileEntitySpecialRenderer{

    RenderItem renderItem = new RenderItem();
    EntityItem item;
    private final ModelPedestal model;
    ResourceLocation brick = new ResourceLocation(Textures.OBSIDIAN_TEXTURE);

    public InfusionRenderer(){
        model = new ModelPedestal();
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        if (entity instanceof ChamberTile){
            ChamberTile tile = (ChamberTile) entity;
            if (tile.getStackInSlot(0)!=null){
                if(item == null)
                    item = new EntityItem(tile.getWorldObj(), tile.xCoord, tile.yCoord + 1, tile.zCoord, tile.getStackInSlot(0));
                GL11.glPushMatrix();
                item.age = (int) entity.getWorldObj().getTotalWorldTime();
                item.setEntityItemStack(tile.getStackInSlot(0));
                item.hoverStart=0.2f;
                //GL11.glColor4f(1F, 1F, 1F, 1F);
                GL11.glTranslatef(0.5F, 0.87F, 0.5F);
                ((Render) RenderManager.instance.entityRenderMap.get(EntityItem.class)).doRender(item, x, y, z, 4F, f);
                GL11.glTranslatef(-0.5F, -0.87F, -0.5F);
                GL11.glTranslatef((float) x + 0.5F, (float) y+0.2f, (float) z + 0.5F);
                Minecraft.getMinecraft().renderEngine.bindTexture(brick);
                GL11.glPushMatrix();
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                //A reference to your Model file. Again, very important.
                this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                //Tell it to stop rendering for both the PushMatrix's
                GL11.glPopMatrix();
                GL11.glPopMatrix();
                tile.getWorldObj().spawnParticle("fire", tile.xCoord+Math.random(), tile.yCoord+.25, tile.zCoord+Math.random(), 1, 1, 1);
            }
            else {
                GL11.glTranslatef((float) x + 0.5F, (float) y+0.2f, (float) z + 0.5F);
                Minecraft.getMinecraft().renderEngine.bindTexture(brick);
                GL11.glPushMatrix();
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                //A reference to your Model file. Again, very important.
                this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
                //Tell it to stop rendering for both the PushMatrix's
                GL11.glPopMatrix();
            }
        }
    }
}
