package club.aurorapvp.events;

import club.aurorapvp.NukkitRandomSpawn;
import club.aurorapvp.events.listeners.PlayerEvents;

public class Events {
  public static void init() {
    NukkitRandomSpawn.SERVER.getPluginManager()
        .registerEvents(new PlayerEvents(), NukkitRandomSpawn.INSTANCE);
  }
}