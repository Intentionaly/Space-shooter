module Enemy {
    requires Common;
    requires CommonBullet;
    provides common.services.IGamePluginService with enemySystem.EnemyPlugin;
    provides common.services.IEntityProcessingService with enemySystem.EnemyProcessor;
}