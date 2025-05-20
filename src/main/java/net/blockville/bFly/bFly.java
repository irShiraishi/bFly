package net.blockville.bFly;

import net.blockville.bFly.commands.Fly;
import net.blockville.bFly.utilities.Getters;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class bFly extends JavaPlugin implements Listener {
    public Getters gettersInst = new Getters();

    @Override
    public void onEnable() {
        System.out.println("Plugin has been enabled! :3");
        getServer().getPluginManager().registerEvents(new Fly(), this);
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

    }

    @Override
    public void onDisable() {
        System.out.println("Plugin has been disabled!");
    }
}
