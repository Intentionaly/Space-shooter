package collisionSystem;

import common.services.IPostEntityProcessingService;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.asteroids.Asteroid;
import common.bullet.Bullet;

import java.util.ArrayList;
import java.util.List;


public class CollisionDetect implements IPostEntityProcessingService {

    public CollisionDetect() {
    }

    @Override
    public void process(GameData gameData, World world) {

        //needed to add a "delay" to deleting or creating entities, as it was causing a bug of bullets that just hit a asteroid to also hit its children since it was being removed and added at the same time

        List<Entity> entitiesToRemove = new ArrayList<>();
        List<Entity> entitiesToAdd = new ArrayList<>();

        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if a bullet or a asteroid is to be removed, it cannot collide anymore
                if (entitiesToRemove.contains(entity1) || entitiesToRemove.contains(entity2)) {
                    continue;
                }

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // CollisionDetection with bullets and asteroids making them spawn children
                if (this.collides(entity1, entity2)) {
                    if (entity1 instanceof Bullet && entity2 instanceof Asteroid) {
                        // if the 2 entities colidding are bullet and asteroid add them to the list to be removed

                        entitiesToRemove.add(entity1);
                        entitiesToRemove.add(entity2);

                        // go through the array and spawn children by adding them one by one
                        for (Asteroid child : splitAsteroid((Asteroid) entity2)) {
                            entitiesToAdd.add(child);
                        }


                        // checking for the other order aswell (bullet hitting asteroid or asteroid hitting bullet)
                    } else if (entity1 instanceof Asteroid && entity2 instanceof Bullet) {
                        entitiesToRemove.add(entity1);
                        entitiesToRemove.add(entity2);

                        for (Asteroid child : splitAsteroid((Asteroid) entity1)) {
                            entitiesToAdd.add(child);
                        }

                    } else {
                        entitiesToRemove.add(entity1);
                        entitiesToRemove.add(entity2);
                    }
                }
            }
        }

        // at the end we go through the lists and do whats needed

        for (Entity entity : entitiesToRemove) {
            world.removeEntity(entity);
        }

        for (Entity entity : entitiesToAdd) {
            world.addEntity(entity);
        }

    }

    private static Asteroid [] splitAsteroid (Asteroid parent){

        // stops infinite loop of child asteroids
        if (parent.getRadius() <= 8) {
            return new Asteroid[0];
        }

        // make 2 new asteroids
        Asteroid leftChildAsteroid = new Asteroid();
        Asteroid rightChildAsteroid = new Asteroid();

        // get the radius from the parent asteroid
        // in all these method calls bassicly copies info from the parent
        float childRadius = parent.getRadius() / 2;

        leftChildAsteroid.setPolygonCoordinates(
                childRadius, -childRadius,
                -childRadius, -childRadius,
                -childRadius, childRadius,
                childRadius, childRadius
        );

        rightChildAsteroid.setPolygonCoordinates(
                childRadius, -childRadius,
                -childRadius, -childRadius,
                -childRadius, childRadius,
                childRadius, childRadius
        );

        leftChildAsteroid.setY(parent.getY()+10);
        rightChildAsteroid.setY(parent.getY()+10);

        leftChildAsteroid.setX(parent.getX()-10);
        rightChildAsteroid.setX(parent.getX()+10);

        leftChildAsteroid.setRadius(childRadius);
        rightChildAsteroid.setRadius(childRadius);

        return new Asteroid[]{leftChildAsteroid, rightChildAsteroid};

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
