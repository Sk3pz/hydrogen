package es.skepz.hydrogen.files

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.wrappers.CFGFile

class ServerFiles(val plugin: Core) {

    var config: CFGFile
    var rules:  CFGFile
    var filter: CFGFile
    var data:   CFGFile
    var warps:  CFGFile
    var worlds: CFGFile

    fun reload() {
        config.reload()
        rules.reload()
        filter.reload()
        data.reload()
        warps.reload()
        worlds.reload()
    }

    fun reloadConfigs() {
        config = CFGFile(plugin, "config", "")
        rules = CFGFile(plugin, "rules",  "")
        filter = CFGFile(plugin, "filter", "")
        data = CFGFile(plugin, "data",   "ds")
        worlds = CFGFile(plugin, "worlds", "ds")
        warps = CFGFile(plugin, "warps",  "ds")
        reload()
    }

    fun restore() {
        if (!plugin.dataFolder.exists()) plugin.dataFolder.mkdir()

        reloadConfigs()

        config.default("default-world", "world")
        config.default("op-override-filter", true)
        config.default("bypass-bed-spawn", false)
        config.default("beds.auto-sleep", false)
        config.default("beds.percent-sleep", 75)
        config.default("warps.shorten-coords", false)

        rules.default("rules", listOf("No leaking personal information",
                "No impersonating members", "No arguing with the staff team", "No hacking",
                "No begging for a role", "Be polite and respectful"))

        filter.default("words", listOf("nigger", "nig", "bitch", "fuck", "ass", "nigga", "shit",
                "damn", "cunt", "clit", "dick", "penis", "cock", "vagina", "hell", "faggot", "boob", "/|/"))

        data.default("chat.muted", false)
        data.default("chat.slew", false)
        data.default("chat.slew-delay", 5)
        data.default("lockdown", false)

        worlds.default("worlds", listOf("world", "world_nether", "world_the_end"))
    }

    init {
        config = CFGFile(plugin, "config", "")
        rules = CFGFile(plugin, "rules",  "")
        filter = CFGFile(plugin, "filter", "")
        data = CFGFile(plugin, "data",   "ds")
        worlds = CFGFile(plugin, "worlds", "ds")
        warps = CFGFile(plugin, "warps",  "ds")
        restore()
    }

}