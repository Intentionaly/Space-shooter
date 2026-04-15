

module Collision {
    requires Common;
    requires CommonBullet;
    requires CommonAsteroids;
    provides common.services.IPostEntityProcessingService with collisionSystem.CollisionDetect;
}