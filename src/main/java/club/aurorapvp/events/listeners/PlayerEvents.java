package club.aurorapvp.events.listeners;

import club.aurorapvp.NukkitRandomSpawn;
import club.aurorapvp.configs.CustomConfig;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import java.util.Random;

public class PlayerEvents implements Listener {

  public static final int RADIUS = CustomConfig.getConfig().getInt("spawn-radius");
  public static final Location SPAWN_POINT =
      new Location(CustomConfig.getConfig().getDouble("spawn-point.x"),
          CustomConfig.getConfig().getDouble("spawn-point.y"),
          CustomConfig.getConfig().getDouble("spawn-point.z"),
          NukkitRandomSpawn.INSTANCE.getServer()
              .getLevelByName(CustomConfig.getConfig().getString("spawn-point.world")));

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    if (event.getPlayer().hasPlayedBefore()) {
      return;
    }

    event.getPlayer().teleport(getSpawn(event.getPlayer()));
  }

  @EventHandler
  public void onRespawn(PlayerRespawnEvent event) {
    if (!(event.getPlayer().getSpawn() == event.getPlayer().getLevel().getSpawnLocation())) {
      return;
    }

    event.getPlayer().teleport(getSpawn(event.getPlayer()));
  }

  public Vector3 getSpawn(Player p) {
    Random rand = new Random();
    double x = rand.nextInt(RADIUS) + SPAWN_POINT.x;
    double z = rand.nextInt(RADIUS) + SPAWN_POINT.z;
    int y = p.getLevel().getHighestBlockAt((int) x, (int) z);

    return p.getLevel().getSafeSpawn(new Vector3(x, y, z));
  }
}