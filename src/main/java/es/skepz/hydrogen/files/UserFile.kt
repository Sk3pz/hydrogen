package es.skepz.hydrogen.files

import org.bukkit.entity.Player
import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.wrappers.CFGFile
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.text.SimpleDateFormat
import java.util.*

class UserFile(plugin: Core, player: Player) : CFGFile(plugin, player.uniqueId.toString(), "users") {
    fun getPrefix(): String? {
        val prefix = cfg.getString("perms.prefix")
        return if (prefix == "none") null else prefix
    }
    fun setPrefix(prefix: String) {
        set("perms.prefix", prefix)
    }

    fun getBal(): Long {
        return cfg.getLong("balance")
    }
    fun setBal(bal: Long) {
        set("balance", bal)
    }
    fun addToBal(amount: Long) {
        set("balance", getBal() + amount)
    }
    fun rmFromBal(amount: Long) {
        set("balance", getBal() - amount)
    }

    fun getWarns(): Int {
        return cfg.getInt("punishments.amount-of.warnings")
    }
    fun setWarns(amount: Int) {
        set("punishments.amount-of.warnings", amount)
    }
    fun addWarn() {
        setWarns(getWarns() + 1)
    }
    fun getMutes(): Int {
        return cfg.getInt("punishments.amount-of.mutes")
    }
    fun setMutes(amount: Int) {
        set("punishments.amount-of.mutes", amount)
    }
    fun addMute() {
        setMutes(getMutes() + 1)
    }
    fun getKicks(): Int {
        return cfg.getInt("punishments.amount-of.kicks")
    }
    fun setKicks(amount: Int) {
        set("punishments.amount-of.kicks", amount)
    }
    fun addKick() {
        setKicks(getKicks() + 1)
    }
    fun getBans(): Int {
        return cfg.getInt("punishments.amount-of.bans")
    }
    fun setBans(amount: Int) {
        set("punishments.amount-of.bans", amount)
    }
    fun addBan() {
        setBans(getBans() + 1)
    }

    fun isBanned(): Boolean {
        if (shouldUnban()) {
            setUnbanned()
            return false
        }
        return cfg.getBoolean("punishments.banned.enabled")
    }
    fun banReason(): String {
        return cfg.getString("punishments.banned.reason") ?: "none"
    }
    fun banSender(): String {
        return cfg.getString("punishments.banned.sender") ?: "none"
    }
    fun banTime(): Long {
        return cfg.getLong("punishments.banned.time")
    }
    fun banStart(): Long {
        return cfg.getLong("punishments.banned.start")
    }
    fun banTimeRemaining(): Long {
        if (!isBanned()) return -1L
        return (banStart() + banTime()) - System.currentTimeMillis()
    }
    fun shouldUnban(): Boolean {
        if (banTime() == -1L) return false
        return banTimeRemaining() <= 0
    }
    fun bannedUntil(): String {
        return SimpleDateFormat("MMM dd,yyyy HH:mm").format(Date(banTimeRemaining()))
    }
    fun setBanned(reason: String, sender: String = "none", time: Long = -1L) {
        set("punishments.banned.enabled", true)
        set("punishments.banned.reason", reason)
        set("punishments.banned.sender", sender)
        set("punishments.banned.time", time)
        set("punishments.banned.start", System.currentTimeMillis())
    }
    fun setUnbanned() {
        set("punishments.banned.enabled", false)
        set("punishments.banned.reason", "none")
        set("punishments.banned.sender", "none")
        set("punishments.banned.time", -1L)
        set("punishments.banned.start", -1L)
    }

    fun isMuted(): Boolean {
        if (shouldUnmute()) {
            setUnmuted()
            return false
        }
        return cfg.getBoolean("punishments.muted.enabled")
    }
    fun muteReason(): String {
        return cfg.getString("punishments.muted.reason") ?: "none"
    }
    fun muteSender(): String {
        return cfg.getString("punishments.muted.sender") ?: "none"
    }
    fun muteTime(): Long {
        return cfg.getLong("punishments.muted.time")
    }
    fun muteStart(): Long {
        return cfg.getLong("punishments.muted.start")
    }
    fun muteTimeRemaining(): Long {
        if (!isMuted()) return -1L
        return (muteStart() + muteTime()) - System.currentTimeMillis()
    }
    fun mutedUntil(): String {
        return SimpleDateFormat("MMM dd,yyyy HH:mm").format(Date(muteTimeRemaining()))
    }
    fun shouldUnmute(): Boolean {
        if (muteTime() == -1L) return false
        return muteTimeRemaining() <= 0
    }
    fun setMuted(reason: String, sender: String = "none", time: Long = -1L) {
        set("punishments.muted.enabled", true)
        set("punishments.muted.reason", reason)
        set("punishments.muted.sender", sender)
        set("punishments.muted.time", time)
        set("punishments.muted.start", System.currentTimeMillis())
    }
    fun setUnmuted() {
        set("punishments.muted.enabled", false)
        set("punishments.muted.reason", "none")
        set("punishments.muted.sender", "none")
        set("punishments.muted.time", -1L)
        set("punishments.muted.start", -1L)
    }

    fun setHome(name: String, loc: Location) {
        set("homes.$name.world", loc.world.uid.toString())
        set("homes.$name.x", loc.x)
        set("homes.$name.y", loc.y)
        set("homes.$name.z", loc.z)
        set("homes.$name.pitch", loc.pitch)
        set("homes.$name.yaw", loc.yaw)
    }

    fun delHome(name: String) {
        set("homes.$name", null)
    }

    fun getHome(plugin: JavaPlugin, name: String): Location? {
        val world = cfg.getString("homes.$name.world") ?: return null
        val x = cfg.getDouble("homes.$name.x")
        val y = cfg.getDouble("homes.$name.y")
        val z = cfg.getDouble("homes.$name.z")
        val pitch = cfg.getDouble("homes.$name.pitch").toFloat()
        val yaw = cfg.getDouble("homes.$name.yaw").toFloat()
        return Location(plugin.server.getWorld(UUID.fromString(world)), x, y, z, yaw, pitch)
    }

    fun homeExists(name: String): Boolean {
        val world = cfg.getString("homes.$name.world")
        return world != null
    }

    fun getHomes(): List<String> {
        val homes = cfg.getConfigurationSection("homes") ?: return ArrayList()
        return homes.getKeys(false).toList()
    }

    fun firstJoin(): Date {
        return Date(cfg.getLong("time.first-joined"))
    }
    fun lastLogin(): Date {
        return Date(cfg.getLong("time.last-login"))
    }
    fun setLastLogin() {
        set("time.last-login", Date().time)
    }
    fun lastLogoff(): Date {
        return Date(cfg.getLong("last-logoff"))
    }
    fun setLastLogoff() {
        set("time.last-logoff", Date().time)
    }

    init {
        set("name", player.name)

        default("perms.prefix", "none")

        default("balance", 100L)

        default("punishments.amount-of.warnings", 0)
        default("punishments.amount-of.mutes", 0)
        default("punishments.amount-of.kicks", 0)
        default("punishments.amount-of.bans", 0)

        default("punishments.muted.enabled", false)
        default("punishments.muted.reason", "none")
        default("punishments.muted.sender", "none")
        default("punishments.muted.time", -1L)
        default("punishments.muted.start", -1L)

        default("punishments.banned.enabled", false)
        default("punishments.banned.reason", "none")
        default("punishments.banned.sender", "none")
        default("punishments.banned.time", -1L)
        default("punishments.banned.start", -1L)

        default("time.first-joined", Date().time)
        setLastLogin()
        default("last-logoff", -1L)
    }

}