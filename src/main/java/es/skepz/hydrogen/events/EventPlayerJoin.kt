package es.skepz.hydrogen.events

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.cFoc
import es.skepz.hydrogen.tuodlib.cPri
import es.skepz.hydrogen.tuodlib.info
import es.skepz.hydrogen.tuodlib.serverBroadcast
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent
import es.skepz.hydrogen.utils.getSpawn

class EventPlayerJoin(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage = null
        serverBroadcast("&8(&a+&8) &7${event.player.name}")

        if (!event.player.hasPlayedBefore()) {
            serverBroadcast("${cPri()}Please welcome ${cFoc()}${event.player.name} ${cPri()}to the server!")
            event.player.teleport(getSpawn(core))
        }

        info(event.player, "Welcome to the server!", "Thanks for playing!", "${cPri()}Welcome to the server!\n" +
                "${cPri()}Be sure to do ${cFoc()}/rules${cPri()} to read the rules!\n" +
                "${cPri()}Do ${cFoc()}/spawn ${cPri()}to go to spawn!\n" +
                "${cPri()}Dont be afraid to ask staff for help!")

    }

}