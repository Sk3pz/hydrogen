package es.skepz.hydrogen.events

import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.files.UserFile
import es.skepz.hydrogen.tuodlib.invalid
import es.skepz.hydrogen.tuodlib.serverBroadcast
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent

class EventAsyncPlayerChat(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        if (!event.isAsynchronous) {
            // safety check as suggested
            return
        }

        event.isCancelled = true

        val player = event.player
        if (core.userFiles[player.uniqueId] == null) {
            core.userFiles[player.uniqueId] = UserFile(core, player)
        }

        // mute check
        val file = core.userFiles[player.uniqueId]!!
        if (file.isMuted()) {
            invalid(player, "Cannot send message!", "You are muted in this chat.",
                    "&cReason: &4${file.muteReason()}\n" +
                            (if (file.muteSender() == "none") "" else "&cMuted by: &4${file.muteSender()}\n") +
                            (if (file.muteTime() == -1L) "&cThis mute is permanent." else "&cMuted until: &4${file.mutedUntil()}"))
            return
        }

        val nameColor = if (player.isOp) {
            "&b"
        } else {
            "&8"
        }

        // parse the message here


        serverBroadcast("$nameColor${player.name} &7> &f${event.message}")

    }

}