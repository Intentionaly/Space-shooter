package playerSystem;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;


public class PlayerPlugin implements IGamePluginService {
    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(
                0, -12,
                6, 6,
                -6, 6
        );
        playerShip.setX(gameData.getDisplayWidth()/2);
        playerShip.setY(gameData.getDisplayHeight() * 0.75);
        playerShip.setRadius(12);
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }
}
