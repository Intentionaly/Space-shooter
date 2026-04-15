package common.bullet;

import common.data.Entity;
import common.data.GameData;

public interface BulletSPI {
    /**
     *
     * @param e
     * @param gameData
     * @return
     */

    Entity createBullet(Entity e, GameData gameData);
}
