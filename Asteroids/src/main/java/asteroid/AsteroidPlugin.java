package asteroid;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;
import common.asteroids.Asteroid;

import java.util.Random;
public class AsteroidPlugin implements IGamePluginService {

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
