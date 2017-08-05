package gg.frog.mc.treasuremap.command;

import org.bukkit.command.CommandSender;

import gg.frog.mc.treasuremap.PluginMain;
import gg.frog.mc.treasuremap.config.LangCfg;
import gg.frog.mc.treasuremap.config.PluginCfg;
import gg.frog.mc.treasuremap.utils.StrUtil;

public class MeCmd implements Runnable {

    private PluginMain pm;
    private String[] args;
    private CommandSender sender;
    private boolean isPlayer;

    public MeCmd(PluginMain pm, CommandSender sender, boolean isPlayer, String[] args) {
        this.pm = pm;
        this.sender = sender;
        this.isPlayer = isPlayer;
        this.args = args;
    }

    @Override
    public void run() {
        if (isPlayer) {
            if (args.length == 1) {
                sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_PROCESSING));
            } else {
                sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + LangCfg.MSG_PARAMETER_MISMATCH));
                sender.sendMessage(StrUtil.messageFormat(LangCfg.CMD_ME, pm.PLUGIN_NAME_LOWER_CASE));
            }
        } else {
            sender.sendMessage(StrUtil.messageFormat(PluginCfg.PLUGIN_PREFIX + "&4Only player can use this command."));
        }
    }
}
