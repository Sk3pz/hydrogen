package es.skepz.hydrogen

import es.skepz.hydrogen.commands.*
import org.bukkit.plugin.java.JavaPlugin
import es.skepz.hydrogen.events.*
import es.skepz.hydrogen.files.ServerFiles
import es.skepz.hydrogen.files.UserFile
import java.util.*
import kotlin.collections.HashMap

class Core : JavaPlugin() {

    val files = ServerFiles(this)

    val userFiles = HashMap<UUID, UserFile>()
    // requester, target (targets can have multiple requests at one time, but requesters can only have one outgoing request)
    val tpaRequests = HashMap<UUID, UUID>()
    val tpahereRequests = HashMap<UUID, UUID>()
    var sleeping = 0.0

    override fun onEnable() {

        // set default spawn if spawnpoint is not already set
        files.data.default("spawn.world", server.worlds[0].uid.toString())
        files.data.default("spawn.x", server.worlds[0].spawnLocation.x)
        files.data.default("spawn.y", server.worlds[0].spawnLocation.y)
        files.data.default("spawn.z", server.worlds[0].spawnLocation.z)
        files.data.default("spawn.pitch", server.worlds[0].spawnLocation.pitch)
        files.data.default("spawn.yaw", server.worlds[0].spawnLocation.yaw)

        // register events
        EventPlayerJoin(this).register()
        EventPlayerLogin(this).register()
        EventPlayerQuit(this).register()
        EventSleepHandle(this).register()
        EventAsyncPlayerChat(this).register()
        EventPlayerDie(this).register()
        EventPlayerKill(this).register()

        // register commands
        HomeCommand(this).register()
        SetHomeCommand(this).register()
        DelHomeCommand(this).register()
        SpawnCommand(this).register()
        SetSpawnCommand(this).register()
        EnchantCommand(this).register()
        MetaCommand(this).register()
        WarpCommand(this).register()
        SetWarpCommand(this).register()
        DelHomeCommand(this).register()
        TpaCommand(this).register()
        TphereCommand(this).register()
        TpaCancel(this).register()
        TpacceptCommand(this).register()
        TpdenyCommand(this).register()
        TplistCommand(this).register()
    }

    override fun onDisable() {

    }

}