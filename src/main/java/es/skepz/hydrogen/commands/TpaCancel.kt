package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.util.*

class TpaCancel(val core: Core) : CoreCMD(core, "tpcancel", "&c/tpcancel",
    0, "none", true, false) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val player = getPlayer()!!
        if (core.tpaRequests.containsKey(player.uniqueId)) {
            val target = Bukkit.getPlayer(core.tpaRequests[player.uniqueId]!!)
            if (target != null) {
                sendMessage(target, "&b${player.name} &7has canceled their request to teleport to you.")
                sendMessage(sender, "&7You have canceled your request to teleport to &b${target.name}&7.")
            } else {
                sendMessage(sender, "&7You have canceled your request.")
            }
            core.tpaRequests.remove(player.uniqueId)
            return
        }
        if (core.tpahereRequests.containsKey(player.uniqueId)) {
            val target = Bukkit.getPlayer(core.tpahereRequests[player.uniqueId]!!)
            if (target != null) {
                sendMessage(target, "&b${player.name} &7has canceled their request to teleport you to them.")
                sendMessage(sender, "&7You have canceled your request to teleport &b${target.name} &7to you.")
            } else {
                sendMessage(sender, "&7You have canceled your request.")
            }
            core.tpaRequests.remove(player.uniqueId)
            return
        }

        sendMessage(sender, "&cYou dont have any outgoing requests.")
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        return ArrayList()
    }

}