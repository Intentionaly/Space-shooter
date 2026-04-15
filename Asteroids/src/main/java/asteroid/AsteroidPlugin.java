package asteroid;

import common.asteroids.Asteroid;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;
public class AsteroidPlugin implements IGamePluginService {
    /**
     *
     * @param gameData
     * @param world
     */

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

}
