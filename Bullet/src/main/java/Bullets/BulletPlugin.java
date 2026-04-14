package Bullets;

import common.services.IGamePluginService;
import common.bullet.Bullet;
import common.data.Entity;
import common.data.GameData;
import common.data.World;



public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
