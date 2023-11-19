package es.skepz.hydrogen.events

import es.skepz.hydrogen.Core
import es.skepz.hydrogen.tuodlib.colorize
import es.skepz.hydrogen.tuodlib.sendMessage
import es.skepz.hydrogen.tuodlib.wrappers.CoreEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDeathEvent

// testing commands:
// /summon ender_dragon ~ ~ ~ {Health:1}
// /summon wither ~ ~ ~ {Health:5}

class EventPlayerKill(val core: Core) : CoreEvent(core) {

    @EventHandler
    fun onKill(event: EntityDeathEvent) {
//        val entity = event.entity
//        val killer = entity.killer ?: return
//
//        val weapon = killer.inventory.itemInMainHand
//        if (weapon.type != Material.STICK) return
//
//        val metadata = weapon.itemMeta
//        val initialized = metadata.hasDisplayName() && metadata.displayName()!!.toString().contains(colorize("&4&kI&r&c"))
//        sendMessage(killer, "initialized: $initialized")
//
//        val newLore = metadata.lore()?.toMutableList() ?: mutableListOf()
//
//        fun updateLore(killType: String): Boolean {
//            // get the index of the killType
//            val index = newLore.indexOfFirst { it.toString().contains(colorize(killType)) }
//            if (index >= 0) {
//                // update the lore
//                val str = (newLore[index] as TextComponent).content()
//                val split = str.split(colorize(": &4"))
//                val kills = split.last().toInt()
//                newLore[index] = Component.text(colorize("$killType: &4${kills + 1}"))
//                return true
//            }
//            // return false if no kills found
//            return false
//        }
//
//        fun playEffects() {
//            //killer.world.spawnParticle(Particle.EXPLOSION_LARGE, killer.location, 1)
//            //killer.world.playSound(killer.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f)
//        }
//
//        if (entity.type == EntityType.WITHER || entity.type == EntityType.ENDER_DRAGON) {
//
//            playEffects()
//
//            if (!initialized) {
//                metadata.displayName(Component.text(colorize("&4&kI&r&c${if (entity.type == EntityType.WITHER) "Wither" else "Dragon"} &cDefeater&r&4&kI&r")))
//            }
//
//            // update the lore
//            if (!updateLore(if (entity.type == EntityType.WITHER) colorize("&cWithers") else colorize("&cDragons"))) {
//                newLore.add(Component.text(if (entity.type == EntityType.WITHER) colorize("&cWithers Slain: &41") else colorize("&cDragons Slain: &41")))
//            }
//
//            // get both wither and dragon kills
//            val witherKills = newLore.find { it.toString().contains(colorize("&cWithers Slain")) }
//            val dragonKills = newLore.find { it.toString().contains(colorize("&cDragons Slain")) }
//
//            if (witherKills != null && dragonKills != null) {
//                metadata.displayName(Component.text(colorize("&4&kI&r&cThe Ultimate Stick Of Power&r&4&kI&r")))
//
//                // enchant the stick
//                val damageAllLevel = metadata.getEnchantLevel(Enchantment.DAMAGE_ALL)
//                metadata.addEnchant(Enchantment.DAMAGE_ALL, damageAllLevel + 20, true)
//                metadata.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true)
//                metadata.addEnchant(Enchantment.SWEEPING_EDGE, 5, true)
//                metadata.addEnchant(Enchantment.FIRE_ASPECT, 3, true)
//            }
//        } else return
//
//        metadata.lore(newLore)
//        weapon.itemMeta = metadata
    }

}