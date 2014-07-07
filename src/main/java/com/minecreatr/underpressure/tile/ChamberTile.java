package com.minecreatr.underpressure.tile;

import com.minecreatr.underpressure.Block.ModBlocks;
import com.minecreatr.underpressure.Infusion;
import com.minecreatr.underpressure.InfusionRegistry;
import com.minecreatr.underpressure.client.FXEssence;
import com.minecreatr.underpressure.collector.IEssenceProvider;
import com.minecreatr.underpressure.collector.IEssenceTile;
import com.minecreatr.underpressure.util.BlockData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 6/26/2014
 */
public class ChamberTile extends BasicTileEntity implements IInventory, IEssenceTile{

    private ItemStack[] slots;
    private int size;
    private int essence;
    private int maxEssence;
    private ArrayList<BlockData> blocks = new ArrayList<BlockData>();
    private int cooldown;
    private int work;
    IIcon particleIcon = Minecraft.getMinecraft().getTextureMapBlocks().registerIcon("underpressure:textures/particle/essence.png");


    public ChamberTile(){
        super();
        this.size = 1;
        slots = new ItemStack[this.getSizeInventory()];
        this.essence=0;
        this.maxEssence=1500;
        cooldown = 0;
        work = 0;
    }
    public ItemStack getStackInSlot(int par1){
        try {
            return slots[par1];
        } catch (NullPointerException exception){
            return null;
        }
    }


    public int getSizeInventory(){
        return this.size;
    }
    public ItemStack decrStackSize(int var1, int var2){
        if (var1!=0){
            return null;
        }
        if (var2>=1){
            slots[var1]=null;
            this.markDirty();
        }
        return slots[var1];
    }
    public ItemStack getStackInSlotOnClosing(int par1){
        if (slots[par1] != null)
        {
            ItemStack itemstack = slots[par1];
            slots[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int var1, ItemStack var2){
        if (var1==0){
            slots[var1]=var2;
        }
    }

    public String getInventoryName(){
        return "PortalChamber";
    }

    public boolean hasCustomInventoryName(){
        return false;
    }

    public int getInventoryStackLimit(){
        return 1;
    }

    public boolean isUseableByPlayer(EntityPlayer var1){
        return true;
    }

    public void openInventory(){}

    public void closeInventory(){}

    public boolean isItemValidForSlot(int var1, ItemStack var2){
        if (var1==0){
            return InfusionRegistry.isInfusable(var2.getItem());
        }
        else {
            return false;
        }
    }



    public Block block(int x, int y, int z){
        return worldObj.getBlock(x, y, z);
    }

    @Override
    public void writeData(NBTTagCompound compound) {
        NBTTagList nbttaglist = new NBTTagList();

        if (slots !=null){
            for (int i = 0; i < slots.length; ++i)
            {
                if (slots[i] != null)
                {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte)i);
                    this.slots[i].writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }
        }

        compound.setTag("Items", nbttaglist);

        NBTTagList blocks1 = new NBTTagList();
        for (int i=0;i<blocks.size();i++){
            NBTTagCompound compound1 = new NBTTagCompound();
            compound1.setIntArray("block", blocks.get(i).toPrim());
            blocks1.appendTag(compound1);
        }
        compound.setTag("blocks", blocks1);
        compound.setInteger("work", this.work);
        compound.setInteger("cooldown", this.cooldown);
    }

    @Override
    public void readData(NBTTagCompound compound) {
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < slots.length)
            {
                slots[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        int size = compound.getInteger("blocksSize");
        NBTTagList blocks1 = compound.getTagList("blocks", size);
        for (int i=0;i<blocks1.tagCount();i++){
            NBTTagCompound compound1 = blocks1.getCompoundTagAt(i);
            BlockData data = BlockData.fromPrim(compound1.getIntArray("block"));
            blocks.add(data);
        }
        this.cooldown=compound.getInteger("cooldown");
        this.work = compound.getInteger("work");
    }

    public void updateEntity(){
        if (this.essence<this.maxEssence){
            this.drawEssence();
        }
        if (this.cooldown<=0){
            checkBlocks();
            cooldown=100;
        }

        if (this.essence>this.maxEssence){
            this.essence=this.maxEssence;
        }
        if (this.getStackInSlot(0)!=null){
            if (InfusionRegistry.isInfusable(this.getStackInSlot(0).getItem())){
               if (this.essence>1){
                   this.essence--;
                   work++;
                   if (work>=InfusionRegistry.getEnergyRequired(this.getStackInSlot(0).getItem())){
                       this.slots[0]= new ItemStack(InfusionRegistry.getOutput(this.getStackInSlot(0).getItem()));
                       this.work=0;
                   }
               }
            }
        }
        else {
            this.work=0;
        }
        cooldown--;
    }

    public int getInputPerTick(){
        return 15;
    }

    public void drawEssence(){
        for (int i=0;i<blocks.size();i++){
            if (blocks.get(i).getTile(worldObj) !=null){
                if (blocks.get(i).getTile(worldObj) instanceof IEssenceProvider){
                    IEssenceProvider tile = (IEssenceProvider) blocks.get(i).getTile(worldObj);
                    if (tile.getOutputPerTick()<=this.getInputPerTick()){
                        this.essence=this.essence+tile.takeEssence(tile.getOutputPerTick());
                    }
                    else {
                        this.essence=this.essence+tile.takeEssence(this.getInputPerTick());
                    }
                }
                else {
                    blocks.remove(i);
                }
            }
            else {
                blocks.remove(i);
            }
        }
    }

    public void drawParticles(BlockData data){
        int startX = data.x;
        int startY = data.y;
        int startZ = data.z;
    }

    public void checkBlocks(){
        for (int i=-6; i<6;i++){
                for (int h=-6;h<6;h++) {
                int tempX = this.xCoord + i;
                int tempZ = this.zCoord + h;
                if (!(i == 0 && h == 0)) {
                    if (worldObj.getTileEntity(tempX, yCoord, tempZ) != null) {
                        if (worldObj.getTileEntity(tempX, yCoord, tempZ) instanceof IEssenceTile) {
                            TileEntity tile = worldObj.getTileEntity(tempX, yCoord, tempZ);
                            BlockData data = new BlockData(tile.xCoord, tile.yCoord, tile.zCoord);
                            blocks.add(data);
                        }
                    }
                }
            }

        }
    }

    public int getEssence(){
        return this.essence;
    }
    public int getMaxEssence(){
        return this.maxEssence;
    }



}
