package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getSpawn
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import java.util.ArrayList

class SpawnCommand(val core: Core) : CoreCMD(core, "spawn", "&c/spawn",
    0, "none", true, false) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val spawnLoc = getSpawn(core)
        // todo: get pitch and yaw
        getPlayer()!!.teleport(spawnLoc)
        sendMessage(sender, "&7Woosh! You have been teleported to &bSpawn&7!")
        playSound(getPlayer()!!, Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1.0f)
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        return ArrayList()
    }

}