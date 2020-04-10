package me.qintinator.saverod.eventlisteners;

import me.qintinator.saverod.contracts.ISaverodService;
import me.qintinator.saverod.enums.ConfigProperty;
import me.qintinator.saverod.statics.ConfigPropertyMapper;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
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

        Material rod_material = Material.getMaterial((String) ConfigPropertyMapper.get(ConfigProperty.SAVEROD_MATERIAL));
        Item item = event.getItem();

        if (item.getItemStack().getType() != rod_material) {
            return;
        }

        EntityType type = event.getEntityType();
        if (type != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) event.getEntity();
        player.getInventory().addItem(saverodService.getSaveRod());
    }
}
