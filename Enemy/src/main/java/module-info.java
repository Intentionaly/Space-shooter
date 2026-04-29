module Enemy {
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    provides common.services.IGamePluginService with enemySystem.EnemyPlugin;
    provides common.services.IEntityProcessingService with enemySystem.EnemyProcessor;
}