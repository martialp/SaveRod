package me.qintinator.saverod.eventlisteners;

import me.qintinator.saverod.contracts.ISaverodService;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class OnEntityDeathEvent implements Listener {

    private final ISaverodService saverodService;

    public OnEntityDeathEvent(ISaverodService saverodService) {
        this.saverodService = saverodService;
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Location location = entity.getLocation();

        if (entity.getType() != EntityType.GHAST) {
            return;
        }

        boolean dropSaverod = getRandomBool(25);
        if (!dropSaverod) {
            return;
        }

        World world = Objects.requireNonNull(location.getWorld());
        world.dropItemNaturally(location, saverodService.getSaveRod());
    }

    private boolean getRandomBool(final double percentChanceOfTrue) {
        return Math.random() * 100 < percentChanceOfTrue;
    }
}
