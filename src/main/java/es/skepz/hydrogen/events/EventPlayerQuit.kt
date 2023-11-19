package es.skepz.hydrogen.events

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.serverBroadcast
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent

class EventPlayerQuit(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onJoin(event: PlayerQuitEvent) {
        event.quitMessage = null
        serverBroadcast("&8(&c-&8) &7${event.player.name}")
    }

}