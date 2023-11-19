package es.skepz.hydrogen.events

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerLoginEvent
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent
import es.skepz.hydrogen.utils.login

class EventPlayerLogin(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onLogin(event: PlayerLoginEvent) {
        login(core, event.player, event)
    }

}