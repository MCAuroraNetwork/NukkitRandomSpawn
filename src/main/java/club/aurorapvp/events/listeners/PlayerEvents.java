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
    double x = rand.nextInt(RADIUS);
    double z = rand.nextInt(RADIUS);
    int y = p.getLevel().getHighestBlockAt((int) x, (int) z);

    return p.getLevel().getSafeSpawn(new Vector3(x, y, z));
  }
}