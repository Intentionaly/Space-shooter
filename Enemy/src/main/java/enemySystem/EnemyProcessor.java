package enemySystem;

import common.bullet.EnemyBullet;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IEntityProcessingService;

public class EnemyProcessor implements IEntityProcessingService {

    private double direction = 1;
    private int shootCooldown = 120;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            enemy.setX(enemy.getX() + direction * 2);

            if (enemy.getX() < 0) {
                direction = 1;
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                direction = -1;
            }

            shootCooldown--;

            if (shootCooldown <= 0) {
                world.addEntity(createEnemyBullet(enemy));
                shootCooldown = 120;
            }
        }

        // make enemy bullets move
        for (Entity bullet : world.getEntities(EnemyBullet.class)) {
            bullet.setY(bullet.getY() + 4);

            // bullets "hits" mothership
            if (bullet.getY() > gameData.getDisplayHeight()-80) {
                world.removeEntity(bullet);
            }
        }
    }

    private Entity createEnemyBullet(Entity enemy) {
        Entity bullet = new EnemyBullet();

        int size = 3;

        bullet.setPolygonCoordinates(
                size, -size,
                size, size,
                -size, size,
                -size, -size
        );

        bullet.setX(enemy.getX());
        bullet.setY(enemy.getY() + 25);
        bullet.setRadius(size + 5);

        return bullet;
    }
}