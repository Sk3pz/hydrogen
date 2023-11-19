package es.skepz.hydrogen.events

import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerBedLeaveEvent
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.*
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent
import kotlin.math.floor
import kotlin.math.roundToInt

class EventSleepHandle(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onSleep(event: PlayerBedEnterEvent) {
        if (event.bedEnterResult != PlayerBedEnterEvent.BedEnterResult.OK || !core.files.config.cfg.getBoolean("beds.auto-sleep")) return
        core.sleeping++

        var percentRequired = 25.0 / 100.0 // default percentage
        if (core.files.config.cfg.get("beds.percent-sleep") != null)
            percentRequired = (core.files.config.cfg.getInt("beds.percent-sleep") / 100.0).coerceAtMost(1.0)
        val playerCount = core.server.onlinePlayers.size // online players
        val percentSleeping = core.sleeping / playerCount // percentage of players sleeping = amount sleeping / amount online
        var playersReq = playerCount * percentRequired // required players to sleep = amount online * percentage of players required
        if (playersReq < 1 || percentRequired <= 0) playersReq = 1.0 // default values
        if (percentSleeping >= percentRequired) { // when enough players are sleeping
            serverBroadcast("${cFoc()}${event.player.name} ${cPri()}is sleeping in a bed. ${cSnd()}Time for day${cPri()}!")
            event.bed.location.world.time = 0
            event.bed.location.world.setStorm(false)
            event.bed.location.world.isThundering = false
            for (p in core.server.onlinePlayers) playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
        } else { // not enough players sleeping
            serverBroadcast("${cFoc()}${event.player.name} ${cPri()}is sleeping in a bed. ${cSnd()}${core.sleeping.roundToInt()}${cPri()}/${cSnd()}${playersReq.roundToInt()}${cPri()}!")
        }
    }

    @EventHandler
    fun onLeave(event: PlayerBedLeaveEvent) {
        core.sleeping--
    }

}