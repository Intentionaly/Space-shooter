package common;

import common.data.Entity;
import common.data.GameData;
import common.data.GameKeys;
import common.data.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    @Test
    void NegativeCoordinates() {
        // testing negative coordinates
        Entity entity = new Entity();
        entity.setX(-1000);
        entity.setY(-500);

        assertEquals(-1000, entity.getX());
        assertEquals(-500, entity.getY());
    }

    @Test
    void ZeroAndNegativeRadius() {
        Entity entity = new Entity();

        // testing sizes of 0 and negative
        entity.setRadius(0);
        assertEquals(0, entity.getRadius());

        entity.setRadius(-10);
        assertEquals(-10, entity.getRadius());
    }

    @Test
    void EmptyPolygonCoordinates() {
        Entity entity = new Entity();

        // testing empty polygon coordinates
        entity.setPolygonCoordinates(new double[]{});

        assertNotNull(entity.getPolygonCoordinates());
        assertEquals(0, entity.getPolygonCoordinates().length);
    }

    @Test
    void entityIds() {
        Entity e1 = new Entity();
        Entity e2 = new Entity();

        //testing unique ids

        assertNotNull(e1.getID());
        assertNotNull(e2.getID());
        assertNotEquals(e1.getID(), e2.getID());
    }

    @Test
    void removingEntity() {
        World world = new World();
        Entity entity = new Entity();

        //testing if removing entity from empty world throws error

        assertDoesNotThrow(() -> world.removeEntity(entity));

        // checking its actually empty
        assertTrue(world.getEntities().isEmpty());
    }

    @Test
    void emptyWorld() {
        World world = new World();

        //checking world is empty without other interaction
        assertTrue(world.getEntities().isEmpty());
    }

    @Test
    void gameData() {
        GameData gameData = new GameData();

        //checking gameData exists
        assertNotNull(gameData.getKeys(),
                "GameKeys should be initialized by default");
    }

    @Test
    void keys() {
        GameKeys keys = new GameKeys();

        // checking all game inputs are being set to false by default

        assertFalse(keys.isDown(GameKeys.LEFT));
        assertFalse(keys.isDown(GameKeys.DOWN));
        assertFalse(keys.isDown(GameKeys.UP));
        assertFalse(keys.isDown(GameKeys.DOWN));
        assertFalse(keys.isDown(GameKeys.SPACE));
    }
}