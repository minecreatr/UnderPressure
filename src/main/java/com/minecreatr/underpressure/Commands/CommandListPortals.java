package com.minecreatr.underpressure.Commands;

import com.minecreatr.underpressure.ModWorldData;
import com.minecreatr.underpressure.util.BlockData;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/10/2014
 */
public class CommandListPortals extends CommandBase{

    public String getCommandName(){
        return "listPortals";
    }

    public String getCommandUsage(ICommandSender var1){
        return "/listPortals";
    }

    public List getCommandAliases(){
        return null;
    }

    public void processCommand(ICommandSender sender, String[] args){
        ArrayList<BlockData> portals = ModWorldData.forWorld(sender.getEntityWorld()).portals;
        for (int i=0;i<portals.size();i++){
            BlockData cur = portals.get(i);
            sender.addChatMessage(new ChatComponentText("Portal: x="+cur.x+" y="+cur.y+" z="+cur.z));
        }
        if (portals.size()<=0){
            sender.addChatMessage(new ChatComponentText("No Portals In World"));
        }
    }

    /**
     * Returns true if the given command sender is allowed to use this command.
     */
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        return MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(sender.getCommandSenderName());
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender var1, String[] var2){
        return null;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] var1, int var2){
        return false;
    }
}
