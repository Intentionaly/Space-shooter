package enemySystem;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemyShip = new Enemy();

        enemyShip.setPolygonCoordinates(
                0, 12,
                8, -8,
                -8, -8
        );

        enemyShip.setX(gameData.getDisplayWidth() / 2);
        enemyShip.setY(100);
        enemyShip.setRadius(12);

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}