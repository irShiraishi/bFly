package net.blockville.bFly.commands;

import net.blockville.bFly.utilities.Getters;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import net.blockville.bFly.bFly;


import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Fly implements CommandExecutor, Listener {
    bFly mainPlugin = bFly.getPlugin(bFly.class);
    Getters gettersInst = mainPlugin.gettersInst;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (sender instanceof Player p){
            if (p.hasPermission("fly.use")) {
                if (!p.getAllowFlight()) {
                    p.sendMessage(ChatColor.DARK_GRAY + "** " + ChatColor.AQUA + "Flight enabled" + ChatColor.DARK_GRAY + "!");
                    p.setAllowFlight(true);
                } else {
                    if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                        gettersInst.getPlayerEventHashmap().put(p.getName(), false);
                    } else {
                        gettersInst.getPlayerEventHashmap().put(p.getName(), true);
                    }
                    p.sendMessage(ChatColor.DARK_GRAY + "** " + ChatColor.AQUA + "Flight disabled" + ChatColor.DARK_GRAY + "!");
                    p.setAllowFlight(false);
                }
            }
        }
        return true;
    }

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (gettersInst.getPlayerEventHashmap().containsKey(p.getName())) {
                if (gettersInst.getPlayerEventHashmap().get(p.getName()) == false) {
                    gettersInst.getPlayerEventHashmap().put(p.getName(), true);
                    e.setDamage(0.0);
                    e.setCancelled(true);
                }
            }
        }
    }
}

