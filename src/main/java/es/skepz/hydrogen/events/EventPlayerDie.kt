package es.skepz.hydrogen.events

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent
import es.skepz.hydrogen.utils.getSpawn
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent

class EventPlayerDie(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        if (event.player.bedSpawnLocation == null) {
            event.player.teleport(getSpawn(core))
        }
    }

}