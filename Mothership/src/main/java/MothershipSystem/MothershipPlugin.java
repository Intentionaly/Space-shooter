package MothershipSystem;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;

public class MothershipPlugin implements IGamePluginService{

    /**
     *
     */

    private Entity mothership;

    public MothershipPlugin(){}

    @Override
    public void start(GameData gameData, World world) {

        // visible ship
        mothership = createMotherShip(gameData);
        world.addEntity(mothership);

    }

    private Entity createMotherShip(GameData gameData){
        Entity mothership = new Mothership();

        int halfWidth = gameData.getDisplayWidth() / 2;

        mothership.setPolygonCoordinates(
                -halfWidth, 0,          // top left
                halfWidth, 0,          // top right

                halfWidth - 60, 35,    // upper right slope
                halfWidth - 120, 55,   // lower right hull

                0, 70,                 // center bottom point

                -halfWidth + 120, 55,   // lower left hull
                -halfWidth + 60, 35     // upper left slope
        );
        mothership.setX(gameData.getDisplayWidth() / 2);
        mothership.setY(gameData.getDisplayHeight() - 60);

        return mothership;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(mothership);
    }


}
