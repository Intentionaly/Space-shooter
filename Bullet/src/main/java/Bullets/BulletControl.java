package Bullets;

import common.bullet.Bullet;
import common.bullet.BulletSPI;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IEntityProcessingService;

public class BulletControl implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            bullet.setY(bullet.getY() - 8); // move up
            if (bullet.getY() < 0) {
                world.removeEntity(bullet);
            }
        }
    }
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {

        Entity bullet = new Bullet();

        int size = 3;

        bullet.setPolygonCoordinates(
                size, -size,
                size, size,
                -size, size,
                -size, -size
        );

        // Spawn slightly above player
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY() - 25);


        // size is same as collision area but a big bigger to be Qol
        bullet.setRadius(size+5);

        return bullet;
    }
}
