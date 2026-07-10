package org.mrfaiergm_studios.playerSitMrFaiergmPLG2;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;



public final class PlayerSitMrFaiergmPLG2 extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("sit").setExecutor(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player player = (Player) sender;
        Location playerloc = player.getLocation();
        Location playersit = playerloc.clone().subtract(0, 1.8, 0);
        if(command.getName().equalsIgnoreCase("sit")){
            if(playerloc.subtract(0, 1.7,0).getBlock().getType() == Material.AIR){
                return true;
            }
            ArmorStand stand = (ArmorStand) playersit.getWorld().spawnEntity(playersit, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setMetadata("SitStand", new FixedMetadataValue(PlayerSitMrFaiergmPLG2.this, true));
            stand.setGravity(false);
            stand.addPassenger(player);
        }
        return false;

    }
    @EventHandler
    public void playerunsit(EntityDismountEvent event){
        Player player = (Player) event.getEntity();
        Location playerlocation = event.getEntity().getLocation();
        if(event.getEntity() instanceof  Player){
            if(event.getDismounted().hasMetadata("SitStand")){
                event.getDismounted().remove();
                Location ext = playerlocation.clone().add(0, 0.5,0);
                player.teleport(playerlocation);

            }
        }
    }
}
