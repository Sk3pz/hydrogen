package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getUserFile
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import java.util.ArrayList

class SetHomeCommand(val core: Core) : CoreCMD(core, "sethome", "&c/sethome <&7name&c>",
    1, "none", true, false) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val player = getPlayer()!!
        val loc = player.location
        val name = args[0]

        for (c in name) {
            if (c !in 'A'..'Z' && c !in 'a'..'z' && c !in '0'..'9' && c != '_') {
                sendMessage(player, "&cFailed to set home: &f$name&c is not a valid name! must only contain a-z, 0-9 or underscores!")
                return
            }
        }

        val file = getUserFile(core, player)

        // check if the home exists
        if (file.homeExists(name)) {
            val home = file.getHome(core, name)!!
            sendMessage(player, "&cA home with that name already exists! overwriting!\n" +
                    "&cThe previous location was in ${home.world} at: &6x:&c ${home.x} &6y:&c ${home.y} &6z:&c ${home.z}")
            file.setHome(name, loc)
            playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
            return
        }

        file.setHome(name, loc)
        sendMessage(player, "&7New home set!")
        playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        return ArrayList()
    }

}