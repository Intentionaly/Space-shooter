import common.bullet.BulletSPI;

module Bullet {
    requires Common;
    requires CommonBullet;
    provides common.services.IGamePluginService with Bullets.BulletPlugin;
    provides BulletSPI with Bullets.BulletControl;
    provides common.services.IEntityProcessingService with Bullets.BulletControl;
}