package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getUserFile
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.ArrayList

class HomeCommand(val core: Core) : CoreCMD(core, "home", "&c/home <&7name&c>",
    0, "none", true, true) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val player = getPlayer()!!
        val file = getUserFile(core, player)

        if (args.size == 0) {
            val homes = file.getHomes()
            if (homes.isEmpty()) {
                sendMessage(sender, "&cYou do not have any homes set!")
                return;
            }
            var homelist = "&7Homes: "
            for (x in homes.indices) {
                val h = homes[x]
                homelist = homelist.plus("&b$h")
                if (x != homes.size - 1) {
                    homelist = homelist.plus("&7, ")
                }
            }
            sendMessage(sender, homelist)
            return
        }

        val name = args[0]

        for (c in name) {
            if (c !in 'A'..'Z' && c !in 'a'..'z' && c !in '0'..'9' && c != '_') {
                sendMessage(player, "&cFailed to get home: &f$name&c is not a valid name! must only contain a-z, 0-9 or underscores!")
                return
            }
        }

        // check if the home exists
        if (!file.homeExists(name)) {
            sendMessage(player, "&cYou do not have a home named &f$name&c! Set one with /sethome <&7$name&c>")
            return
        }

        val home = file.getHome(core, name) ?: return sendMessage(player, "&cFailed to recall home location from your file. Please contact an admin if this persists!")
        player.teleport(home)
        sendMessage(sender, "&7Woosh! You have been teleported to &b$name&7!")
        playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        val completions = ArrayList<String>()

        val player = getPlayer()!!
        val file = getUserFile(core, player)

        val homes = file.getHomes()
        if (args.size == 1) {
            StringUtil.copyPartialMatches(args[0], homes, completions)
        }
        return completions
    }

}