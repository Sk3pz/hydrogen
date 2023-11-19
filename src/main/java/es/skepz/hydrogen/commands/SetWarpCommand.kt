package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getWarp
import es.skepz.hydrogen.utils.setWarp
import es.skepz.hydrogen.utils.warpExists
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import java.util.ArrayList

class SetWarpCommand(val core: Core) : CoreCMD(core, "setwarp", "&c/setwarp <&7name&c>",
    1, "core.setwarp", true, false) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val player = getPlayer()!!
        val loc = player.location
        val name = args[0]

        for (c in name) {
            if (c !in 'A'..'Z' && c !in 'a'..'z' && c !in '0'..'9' && c != '_') {
                sendMessage(player, "&cFailed to set warp: &f$name&c is not a valid name! must only contain a-z, 0-9 or underscores!")
                return
            }
        }

        // check if the home exists
        if (warpExists(core, name)) {
            val warp = getWarp(core, name)!!
            sendMessage(player, "&cA warp with that name already exists! overwriting!\n" +
                    "&cThe previous location was in ${warp.world} at: &6x:&c ${warp.x} &6y:&c ${warp.y} &6z:&c ${warp.z}")
            setWarp(core, name, loc)
            playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
            return
        }

        setWarp(core, name, loc)
        sendMessage(player, "&7New warp set!")
        playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        return ArrayList()
    }

}