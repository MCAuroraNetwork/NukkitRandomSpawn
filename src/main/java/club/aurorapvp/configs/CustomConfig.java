package club.aurorapvp.configs;

import club.aurorapvp.NukkitRandomSpawn;
import cn.nukkit.utils.Config;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CustomConfig {
  private static final HashMap<String, Object> DEFAULTS = new HashMap<>();
  private static final File FILE = new File(NukkitRandomSpawn.DATA_FOLDER, "config.yml");
  private static Config config;

  public static void init() {
    reload();
    generateDefaults();
  }

  public static void generateDefaults() {
    DEFAULTS.put("spawn-radius", 1000);

    for (String path : DEFAULTS.keySet()) {
      if (!config.exists(path) || config.getString(path) == null) {
        config.set(path, DEFAULTS.get(path));

        config.save(FILE);
      }
    }
  }

  public static Config getConfig() {
    return config;
  }

  public static void reload() {
    if (!FILE.exists()) {
      try {
        FILE.getParentFile().mkdirs();
        FILE.createNewFile();
      } catch (IOException e) {
        NukkitRandomSpawn.INSTANCE.getLogger().critical("Failed to generate config file");
      }
    }
    config = NukkitRandomSpawn.INSTANCE.getConfig();
    NukkitRandomSpawn.INSTANCE.getLogger().info("Config reloaded!");
  }
}