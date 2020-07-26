package me.infinityz.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FasterVillager extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlace2(BlockPlaceEvent e) {
        switch (e.getBlock().getType()) {
            case BLAST_FURNACE:
            case SMOKER:
            case CARTOGRAPHY_TABLE:
            case BREWING_STAND:
            case COMPOSTER:
            case BARREL:
            case FLETCHING_TABLE:
            case CAULDRON:
            case LECTERN:
            case STONECUTTER:
            case LOOM:
            case SMITHING_TABLE:
            case GRINDSTONE: {
                e.getBlock().getLocation().getNearbyLivingEntities(10, 10).stream()
                        .filter(it -> it.getType() == EntityType.VILLAGER)
                        .filter(it -> (((Villager) it).getVillagerLevel() == 1
                                && ((Villager) it).getVillagerExperience() == 0))
                        .findFirst().ifPresent(it -> {
                            ((Villager) it).setMemory(MemoryKey.JOB_SITE, e.getBlock().getLocation());
                        });
                break;
            }
            default: {
                break;
            }
        }

    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlace2(BlockBreakEvent e) {
        switch (e.getBlock().getType()) {
            case BLAST_FURNACE:
            case SMOKER:
            case CARTOGRAPHY_TABLE:
            case BREWING_STAND:
            case COMPOSTER:
            case BARREL:
            case FLETCHING_TABLE:
            case CAULDRON:
            case LECTERN:
            case STONECUTTER:
            case LOOM:
            case SMITHING_TABLE:
            case GRINDSTONE: {
                e.getBlock().getLocation().getNearbyLivingEntities(10, 10).stream()
                        .filter(it -> it.getType() == EntityType.VILLAGER)
                        .filter(it -> (((Villager) it).getVillagerLevel() == 1
                                && ((Villager) it).getVillagerExperience() == 0)
                                && ((Villager) it).getMemory(MemoryKey.JOB_SITE) != null
                                && ((Villager) it).getMemory(MemoryKey.JOB_SITE).equals(e.getBlock().getLocation()))
                        .findFirst().ifPresent(it -> {
                            ((Villager) it).setMemory(MemoryKey.JOB_SITE, e.getBlock().getLocation());
                        });
                break;
            }
            default: {
                break;
            }
        }

    }

}