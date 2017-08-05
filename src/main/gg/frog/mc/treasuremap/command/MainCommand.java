package gg.frog.mc.treasuremap.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import gg.frog.mc.treasuremap.PluginMain;
import gg.frog.mc.treasuremap.config.LangCfg;
import gg.frog.mc.treasuremap.config.PluginCfg;
import gg.frog.mc.treasuremap.utils.StrUtil;

public class MainCommand implements CommandExecutor, TabCompleter {

    private PluginMain pm;

    public MainCommand(PluginMain pm) {
        this.pm = pm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase(pm.PLUGIN_NAME_LOWER_CASE) || commandLabel.equalsIgnoreCase("pt")) {
            boolean isPlayer = false;
            if (sender instanceof Player) {
                isPlayer = true;
            }
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                getHelp(sender, isPlayer);
                return true;
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_PROCESSING));
                    if (isPlayer) {
                        Player player = (Player) sender;
                        if (sender.isOp() || player.hasPermission("treasuremap.reload")) {
                            pm.getConfigManager().reloadConfig();
                            sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_CONFIG_RELOADED));
                            pm.getServer().getConsoleSender().sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_CONFIG_RELOADED));
                        } else {
                            sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_NO_PERMISSION));
                        }
                    } else {
                        pm.getConfigManager().reloadConfig();
                        sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_CONFIG_RELOADED));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("me")) {
                    if (hasPermission(sender, isPlayer, "treasuremap.me")) {
                        MeCmd meCmd = new MeCmd(pm, sender, isPlayer, args);
                        new Thread(meCmd).start();
                    }
                } else {
                    sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_HELP, pm.PLUGIN_NAME_LOWER_CASE));
                }
                return true;
            }
        }
        return false;
    }

    private void getHelp(CommandSender sender, boolean isPlayer) {
        sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + "\n&a===== " + pm.PLUGIN_NAME + " Version:" + pm.PLUGIN_VERSION + (pm.getDescription().getCommands().containsKey("pt") ? " Aliases:/pt" : "") + " ====="));
        if (isPlayer && (sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".me"))) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_ME, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".packages")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_PACKAGES, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".get")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_GET, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".give")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_GIVE, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".set")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_SET, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".remove")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_REMOVE, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".removeall")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_REMOVEALL, pm.PLUGIN_NAME_LOWER_CASE));
        }
        if (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".reload")) {
            sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_RELOAD, pm.PLUGIN_NAME_LOWER_CASE));
        }
    }

    private boolean hasPermission(CommandSender sender, boolean isPlayer, String permissionPath) {
        if (isPlayer) {
            Player player = (Player) sender;
            if (sender.isOp() || player.hasPermission(permissionPath)) {
            } else {
                sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_NO_PERMISSION));
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tipList = new ArrayList<String>();
        boolean isPlayer = false;
        if (sender instanceof Player) {
            isPlayer = true;
        }
        if (args.length == 1) {
            args[0] = args[0].toLowerCase(Locale.ENGLISH);
            if ("me".startsWith(args[0]) && (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".me"))) {
                tipList.add("me");
            }
            if ("reload".startsWith(args[0]) && (!isPlayer || sender.isOp() || sender.hasPermission(pm.PLUGIN_NAME_LOWER_CASE + ".reload"))) {
                tipList.add("reload");
            }
        }
        return tipList;
    }
}
