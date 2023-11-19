package es.skepz.hydrogen.utils

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerLoginEvent
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.files.UserFile
import es.skepz.hydrogen.tuodlib.colorize
import java.util.*

fun getUserFile(plugin: Core, player: Player): UserFile {
    return UserFile(plugin, player)
}
fun getUserFile(plugin: Core, player: UUID): UserFile? {
    return plugin.userFiles[player]
}

fun login(plugin: Core, player: Player, event: PlayerLoginEvent): Boolean {
    // create user file
    val file = UserFile(plugin, player)
    // ban check
    if (file.isBanned()) {
        event.kickMessage = colorize("&cYou are banned from this server!\n" +
                "&cReason: &4${file.banReason()}\n" +
                (if (file.banSender() == "none") "" else "&cBanned by: &4${file.banSender()}\n") +
                (if (file.banTime() == -1L) "&cThis ban is permanent." else "&cBanned until: &4${file.bannedUntil()}"))
        event.disallow(PlayerLoginEvent.Result.KICK_BANNED, event.kickMessage)
        return false
    }
    return true
}

fun login(plugin: Core, player: Player) {
    // create user file
    val file = UserFile(plugin, player)
    // ban check
    if (file.isBanned()) {
        player.kickPlayer(colorize("&cYou are banned from this server!\n" +
                "&cReason: &4${file.banReason()}\n" +
                (if (file.banSender() == "none") "" else "&cBanned by: &4${file.banSender()}\n") +
                (if (file.banTime() == -1L) "&cThis ban is permanent." else "&cBanned until: &4${file.bannedUntil()}")))
        return
    }
}

fun logout(plugin: Core, player: Player) {
    plugin.userFiles.remove(player.uniqueId)
}