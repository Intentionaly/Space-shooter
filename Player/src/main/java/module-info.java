module Player {
    requires Common;
    requires CommonBullet;
    requires CommonPlayer;
    uses common.bullet.BulletSPI;
    provides common.services.IGamePluginService with playerSystem.PlayerPlugin;
    provides common.services.IEntityProcessingService with playerSystem.PlayerControl;

}