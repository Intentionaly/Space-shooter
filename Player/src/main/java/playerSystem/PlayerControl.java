package playerSystem;


import common.bullet.BulletSPI;
import common.data.Entity;
import common.data.GameData;
import common.data.GameKeys;
import common.data.World;
import common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControl implements IEntityProcessingService {
    /**
     *
     */

    private double shootCooldown = 0;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            double speed = 3;

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setX(player.getX() - speed);
            }

            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setX(player.getX() + speed);
            }

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                player.setY(player.getY() - speed);
            }

            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setY(player.getY() + speed);
            }

            if (shootCooldown > 0) {
                shootCooldown--;
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE) && shootCooldown <= 0) {

                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(player, gameData))
                );

                shootCooldown = 30; // ~30 frames between shots
            }

            if (player.getX() < 0) {
                player.setX(1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth()-1);
            }

            if (player.getY() < 0) {
                player.setY(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight()-1);
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        // would be best to use servicelocator here, but since it was moved into core, we cannot require it, therefore we use serviceloader
        return ServiceLoader
                // using layer aware method since bullet now lives in plugin
                .load(PlayerControl.class.getModule().getLayer(), BulletSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}
