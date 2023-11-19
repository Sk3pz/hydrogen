package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.delWarp
import es.skepz.hydrogen.utils.getUserFile
import es.skepz.hydrogen.utils.warpExists
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import java.util.ArrayList

class DelWarpCommand(val core: Core) : CoreCMD(core, "delwarp", "&c/delwarp <&7name&c>",
    1, "core.delwarp", true, false) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val player = getPlayer()!!
        val loc = player.location
        val name = args[0]

        for (c in name) {
            if (c !in 'A'..'Z' && c !in 'a'..'z' && c !in '0'..'9' && c != '_') {
                sendMessage(player, "&cFailed to delete warp: &f$name&c is not a valid name! must only contain a-z, 0-9 or underscores!")
                return
            }
        }

        // check if the home exists
        if (!warpExists(core, name)) {
            sendMessage(player, "&cYou do not have any warps with that name!")
            return
        }

        delWarp(core, name)
        sendMessage(player, "&7Removed your warp!")
        playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        return ArrayList()
    }

}