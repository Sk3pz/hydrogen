package es.skepz.hydrogen.tuodlib

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Color {
    const val primary = "&7"
    const val focused = "&3"
    const val secondary = "&b"
    const val errorPrimary = "&4"
    const val errorSecondary = "&c"
    const val errorFocused = "&f"
}
/***
 * @return returns the main (primary) color of the plugin
 ***/
fun cPri(): String { return Color.primary }
/***
 * @return returns the special (focused) color of the plugin
 ***/
fun cFoc(): String { return Color.focused }
/***
 * @return returns the secondary color of the plugin
 ***/
fun cSnd(): String { return Color.secondary }
/***
 * @return returns the primary error color of the plugin
 ***/
fun cErr(): String { return Color.errorPrimary }
/***
 * @return returns the secondary error color of the plugin
 ***/
fun cESn(): String { return Color.errorSecondary }
/***
 * @return returns the focused error color of the plugin
 ***/
fun cEFc(): String { return Color.errorFocused }

/***
 * Sends a message to a player
 * @param user: The user to send the message to
 * @param message: The message to send (will be formatted with '&' color codes)
 ***/
fun sendMessage(user: CommandSender, message: String) {
    user.sendMessage(colorize(message))
}

/***
 * Generate a usage string for a command
 * @param usage: the string typed after the / for the command (without the /)
 * @param argSections: a vararg of String Lists, with each list's index being its argument location
 * @return the finished usage string
 ***/
fun genUsage(usage: String, vararg argSections: List<String>): String {
    var fin = "${cErr()}/$usage <"
    for (sec in argSections)
        for (x in 0..sec.size)
            fin += if (x != sec.size - 1) "${cEFc()}${sec[x]}${cErr()}|" else "${cEFc()}${sec[x]}"
    return "$fin${cErr()}>"
}

/***
 * send an invalid command usage message to a user
 * @param user: the user to send the message to
 * @param usage: the correct usage of the command
 ***/
fun invalidCmdUsage(user: CommandSender, usage: String) {
    sendMessage(user, "${cErr()}Invalid Use! Usage: ${cEFc()}$usage")
}

/***
 * sends an invalid permissions message to a player
 * @param user: the user to send the message to
 ***/
fun noPerms(user: CommandSender) {
    sendMessage(user, "${cErr()}You don't have permission to use this command!")
}

/***
 * send a message that the entered player is not a correct player
 * @param user: the user to send the message to
 ***/
fun notPlayer(user: CommandSender) {
    sendMessage(user, "${cErr()}This player is either not online or does not exist!")
}

/***
 * sends a message to the sender that you have to be a player to run the command
 * @param user: the user to send the message to
 ***/
fun requirePlayer(user: CommandSender) {
    sendMessage(user, "${cErr()}You must be a player to use this command!")
}

/***
 * sends a message to the console
 * @param msg: the message to send
 ***/
fun sendConsoleMessage(msg: String) {
    sendMessage(Bukkit.getConsoleSender(), msg)
}

/***
 * broadcasts a message to everyone on the server, and console
 * @param msg: the message to send
 ***/
fun serverBroadcast(msg: String) {
    Bukkit.getServer().broadcastMessage(colorize(msg))
}

/**
 * An interactable message
 **/
class IMessage {
    private var smsg: String
    private var textComponent: TextComponent
    /***
     * the default constructor, initialises the start message as an empty string
     ***/
    constructor() {
        smsg = ""
        textComponent = TextComponent(smsg)
    }
    /***
     * The secondary constructor
     * @param start: the start / default message that will be added on to
     ***/
    constructor(start: String) {
        smsg = start
        textComponent = TextComponent(colorize(smsg))
    }

    /***
     * sets the tooltip for the start message, making it hoverable
     * @param toolTip: the tooltip that will display on hovering over the message
     * @return returns this object for chaining message types
     ***/
    fun setDefaultHover(toolTip: String): IMessage {
        textComponent.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(colorize(toolTip)).create())
        return this
    }

    /***
     * sets the link for the start message, making it clickable
     * @param link: the link that clicking the message will send the user(s) to
     * @return returns this object for chaining message types
     ***/
    fun setDefaultClick(link: String): IMessage {
        textComponent.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, link)
        return this
    }

    /***
     * sets the command for the start message, making it clickable
     * @param cmd: the command to run when clicked
     * @return returns this object for chaining message types
     ***/
    fun setDefaultCmd(cmd: String): IMessage {
        textComponent.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd)
        return this
    }

    /***
     * appends a normal string to the message
     * @param msg: the message to be added
     * @return returns this object for chaining message types
     ***/
    fun add(msg: String): IMessage {
        textComponent.addExtra(TextComponent(colorize(msg)))
        return this
    }

    /***
     * adds a string with a tooltip to the message
     * @param msg: the message to be added
     * @param toolTip: the tooltip to display on hovering over the message
     * @return returns this object for chaining message types
     ***/
    fun addHoverable(msg: String, toolTip: String): IMessage {
        val tc = TextComponent(colorize(msg))
        tc.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(colorize(toolTip)).create())
        textComponent.addExtra(tc)
        return this
    }

    /***
     * adds a message which will run a command upon being clicked
     * @param msg: the message that can be clicked
     * @param command: the command to be run on click
     * @return returns this object for chaining message types
     ***/
    fun addClickCmd(msg: String, command: String): IMessage {
        val tc = TextComponent(colorize(msg))
        tc.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)
        textComponent.addExtra(tc)
        return this
    }

    /***
     * a message that will run a command upon being clicked and will display a tooltip upon hovering over it
     * @param msg: the message to be displayed
     * @param command: the command to be run on click
     * @param toolTip:
     * @return returns this object for chaining message types
     ***/
    fun addHoverableClickCmd(msg: String, command: String, toolTip: String): IMessage {
        val tc = TextComponent(colorize(msg))
        tc.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)
        tc.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(colorize(toolTip)).create())
        textComponent.addExtra(tc)
        return this
    }

    /***
     * add a message that will open a link when clicked
     * @param msg: the message to be added
     * @param link: the link to be opened on click
     * @return returns this object for chaining message types
     ***/
    fun addLink(msg: String, link: String): IMessage {
        val tc = TextComponent(colorize(msg))
        tc.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, link)
        textComponent.addExtra(tc)
        return this
    }

    /***
     * add a clickable link message with a tooltip
     * @param msg: the message to display
     * @param link: the link to be opened on click
     * @param toolTip: the tooltip to be displayed
     * @return returns this object for chaining message types
     ***/
    fun addHoverableLink(msg: String, link: String, toolTip: String): IMessage {
        val tc = TextComponent(colorize(msg))
        tc.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, link)
        tc.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(colorize(toolTip)).create())
        textComponent.addExtra(tc)
        return this
    }

    /***
     * adds a newline (also possible with \n anywhere)
     * @return returns this object for chaining message types
     ***/
    fun newLine(): IMessage {
        add("\n")
        return this
    }

    /***
     * get the text component
     * @return returns the text component for more customization
     ***/
    fun build(): TextComponent {
        return textComponent
    }

    /***
     * send the full message to a player
     * @param player: the player to send the message to
     * @return returns this object for chaining message types
     ***/
    fun send(player: Player): IMessage {
        player.spigot().sendMessage(build())
        return this
    }

    /***
     * send the full message to all players on the server
     * @return returns this object for chaining message types
     ***/
    fun broadcast(): IMessage {
        for (player in Bukkit.getOnlinePlayers()) send(player)
        return this
    }
}

/***
 * Make an informational message with a tooltip
 * @param user: The player to send it to
 * @param title: the title
 * @param subtitle: a short description
 * @param toolTip: what to be displayed on hover
 ***/
fun info(user: Player, title: String, subtitle: String, toolTip: String) {
    IMessage("${cFoc()}$title").newLine()
            .add("${cSnd()}$subtitle")
            .newLine().newLine()
            .addHoverable(" &7&o[?] Hover over this for more information", toolTip)
            .send(user)
}

/***
 * Make an error message with a tooltip
 * @param user: The player to send it to
 * @param title: the title
 * @param subtitle: a short description
 * @param toolTip: what to be displayed on hover
 ***/
fun invalid(user: Player, title: String, subtitle: String, toolTip: String) {
    IMessage("${cEFc()}$title").newLine()
            .add("${cESn()}$subtitle")
            .newLine().newLine()
            .addHoverable(" &7&o[?] Hover over this for more information", toolTip)
            .send(user)
}