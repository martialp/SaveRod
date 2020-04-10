package me.qintinator.saverod.eventlisteners;

import me.qintinator.saverod.contracts.ISaverodService;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class OnEntityPickupItem implements Listener {
    private final ISaverodService saverodService;

    public OnEntityPickupItem(ISaverodService saverodService) {
        this.saverodService = saverodService;
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        Bukkit.broadcastMessage("Works..?");


        EntityType type = event.getEntityType();
        if (type != EntityType.PLAYER) {
            return;
        }

        Bukkit.broadcastMessage("Works!");
        Player player = (Player) event.getEntity();
        player.getInventory().addItem(saverodService.getSaveRod());
    }
}
