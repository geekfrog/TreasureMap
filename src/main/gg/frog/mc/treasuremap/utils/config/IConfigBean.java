package gg.frog.mc.treasuremap.utils.config;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

public interface IConfigBean {
    
    YamlConfiguration toConfig();
    
    void toConfigBean(MemorySection config);
}
