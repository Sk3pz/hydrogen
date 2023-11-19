package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getUserFile
import es.skepz.hydrogen.utils.getWarp
import es.skepz.hydrogen.utils.getWarps
import es.skepz.hydrogen.utils.warpExists
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.ArrayList

class WarpCommand(val core: Core) : CoreCMD(core, "warp", "&c/warp <&7name&c>",
    0, "none", true, true) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val player = getPlayer()!!

        if (args.size == 0) {
            val warps = getWarps(core)
            if (warps.isEmpty()) {
                sendMessage(sender, "&cYou do not have any homes set!")
                return;
            }
            var list = "&7Warps: "
            for (x in warps.indices) {
                val h = warps[x]
                list = list.plus("&b$h")
                if (x != warps.size - 1) {
                    list = list.plus("&7, ")
                }
            }
            sendMessage(sender, list)
            return
        }

        val name = args[0]

        for (c in name) {
            if (c !in 'A'..'Z' && c !in 'a'..'z' && c !in '0'..'9' && c != '_') {
                sendMessage(player, "&cFailed to set home: &f$name&c is not a valid name! must only contain a-z, 0-9 or underscores!")
                return
            }
        }

        // check if the home exists
        if (!warpExists(core, name)) {
            sendMessage(player, "&cYou do not have a home named &f$name&c! Set one with /sethome <&7$name&c>")
            return
        }

        val warp = getWarp(core, name) ?: return sendMessage(player, "&cFailed to recall home location from your file. Please contact an admin if this persists!")
        player.teleport(warp)
        sendMessage(sender, "&7Woosh! You have been teleported to &b$name&7!")
        playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        val completions = ArrayList<String>()

        val warps = getWarps(core)
        if (args.size == 1) {
            StringUtil.copyPartialMatches(args[0], warps, completions)
        }
        return completions
    }

}