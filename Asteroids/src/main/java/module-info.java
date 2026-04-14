import asteroid.AsteroidPlugin;
import asteroid.AsteroidProcessor;

module Asteroids {
    requires Common;
    requires commonAsteroids;
    provides common.services.IGamePluginService with AsteroidPlugin;
    provides common.services.IEntityProcessingService with AsteroidProcessor;
}