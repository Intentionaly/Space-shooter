package Collision;

import collisionSystem.CollisionDetect;
import common.data.Entity;
import common.data.GameData;
import common.data.GameKeys;
import common.data.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {

    @Test
    void collisionDetection() {
        CollisionDetect collision = new CollisionDetect();

        Entity e1 = new Entity();
        Entity e2 = new Entity();

        e1.setX(0);
        e1.setY(0);
        e1.setRadius(10);

        e2.setX(5);
        e2.setY(5);
        e2.setRadius(10);

        //
        boolean result = collision.collides(e1, e2);

        // Assert
        assertTrue(result);
    }
}
