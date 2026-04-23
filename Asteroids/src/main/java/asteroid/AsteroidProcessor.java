package asteroid;


import common.asteroids.Asteroid;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidProcessor implements IEntityProcessingService {
    /**
     *
     */

    private int spawnCounter = 0;
    @Override
    public void process(GameData gameData, World world) {

        spawnCounter++;

        if (spawnCounter > 400) { // roughly 2 seconds at ~60fps
            world.addEntity(createAsteroid(gameData));
            spawnCounter = 0;
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {

            asteroid.setY(asteroid.getY() + 1.2); // change this number for speed

            // the reason this detection lives here instead of collisiondetection is its a very simple logic of 1 entity collision so no need
            // to move it into collisiondetect which is for 1v1 entity detection
            if (asteroid.getY() > gameData.getDisplayHeight() - 80) {

                // the -80 acts as the barrier detecting the asteroid hitting the mothership
                gameData.mothershipGotHit();

                /* debugging asteroids hitting ship
                System.out.println("ASTEROID HIT BASE");
                System.out.println("Health: " + gameData.getMothershipHealth());
                 */

                // asteroid "hits" the mothership
                world.removeEntity(asteroid);
            }
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Random rnd = new Random();
        Asteroid asteroid = new Asteroid();

        int size = 16;

        asteroid.setPolygonCoordinates(
                size, -size,
                -size, -size,
                -size, size,
                size, size
        );

        int screenWidth = gameData.getDisplayWidth();

        // Spawn at random X along the top
        asteroid.setX(rnd.nextInt(screenWidth));
        asteroid.setY(0);

        // size is same as collsion size
        asteroid.setRadius(size);

        return asteroid;
    }
}
