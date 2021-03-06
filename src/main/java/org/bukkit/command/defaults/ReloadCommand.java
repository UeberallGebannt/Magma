package org.bukkit.command.defaults;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.magmafoundation.magma.api.ServerAPI;
import org.magmafoundation.magma.configuration.MagmaConfig;

public class ReloadCommand extends BukkitCommand {
    public ReloadCommand(String name) {
        super(name);
        this.description = "Reloads the server configuration and plugins";
        this.usageMessage = "/reload";
        this.setPermission("bukkit.command.reload");
        this.setAliases(Arrays.asList("rl"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        if (!MagmaConfig.instance.enableReloadCommand.getValues()) {
            Command.broadcastCommandMessage(sender, ChatColor.RED + "The /reload command is disabled by default.");
            Command.broadcastCommandMessage(sender, ChatColor.RED + "If you want to use it, enable it in the magma.yml.");
            return true;
        }

        if (ServerAPI.getModSize() > 4 && !(args.length >= 1 && args[0].equalsIgnoreCase("confirm"))) {
            Command.broadcastCommandMessage(sender, ChatColor.RED + "The /reload command is not supported if mods are installed.");
            Command.broadcastCommandMessage(sender, ChatColor.RED + "Please use '/reload confirm' if you want to force reload the server.");
            return true;
        }

        Command.broadcastCommandMessage(sender, ChatColor.RED + "Please note that this command is not supported and may cause issues when using some plugins.");
        Command.broadcastCommandMessage(sender, ChatColor.RED + "If you encounter any issues please use the /stop command to restart your server.");
        Bukkit.reload();
        Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return Collections.emptyList();
    }
}
