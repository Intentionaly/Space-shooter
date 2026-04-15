import asteroid.AsteroidPlugin;
import asteroid.AsteroidProcessor;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    provides common.services.IGamePluginService with AsteroidPlugin;
    provides common.services.IEntityProcessingService with AsteroidProcessor;
}