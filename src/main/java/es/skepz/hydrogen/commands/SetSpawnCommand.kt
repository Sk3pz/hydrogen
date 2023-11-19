package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getSpawn
import es.skepz.hydrogen.utils.setSpawn
import org.bukkit.command.CommandSender
import java.util.ArrayList

class SetSpawnCommand(val core: Core) : CoreCMD(core, "setspawn", "&c/setspawn",
    0, "core.setspawn", true, false) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val loc = getPlayer()!!.location

        setSpawn(core, loc)
        sendMessage(sender, "&7Spawn set to your position.")
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        return ArrayList()
    }

}