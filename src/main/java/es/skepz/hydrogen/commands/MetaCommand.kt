package es.skepz.hydrogen.commands

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.colorize
import es.skepz.hydrogen.tuodlib.playSound
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreCMD
import es.skepz.hydrogen.utils.getSpawn
import es.skepz.hydrogen.utils.getUserFile
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.ArrayList

class MetaCommand(val core: Core) : CoreCMD(core, "meta", "&c/meta <&7name&c|&7lore&c> <&7data&c>",
    1, "core.meta", true, true) {

    override fun init() {
        // not used for this function
    }

    override fun run() {
        val modeRaw = args[0].lowercase()
        args.removeAt(0)
        val data = colorize(args.joinToString(" "))
        val player = getPlayer()!!

        val item = player.inventory.itemInMainHand
        if (item.type == Material.AIR) {
            sendMessage(sender, "&cYou must be holding something in your hand to use this command!")
            return;
        }
        val meta = item.itemMeta

        when (modeRaw) {
            "unbreakable" -> {
                meta.isUnbreakable = !meta.isUnbreakable
            }
            "name" -> {
                if (args.size < 1) {
                    invalidUse()
                    return
                }
                meta.setDisplayName(data)
            }
            "lore" -> {
                if (args.size < 1) {
                    invalidUse()
                    return
                }
                meta.lore = data.split("&n")
            }
            else -> {
                invalidUse()
                return;
            }
        }
        item.itemMeta = meta
        sendMessage(sender, "&7Updated your item!")
    }

    override fun registerTabComplete(sender: CommandSender, args: ArrayList<String>): List<String> {
        val completions = ArrayList<String>()

        if (args.size == 1) {
            StringUtil.copyPartialMatches(args[0], listOf("name", "lore", "unbreakable"), completions)
        }

        return completions
    }

}