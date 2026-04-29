
module Collision {
    requires Common;
    requires CommonBullet;
    requires CommonAsteroids;
    requires CommonMothership;
    provides common.services.IPostEntityProcessingService with collisionSystem.CollisionDetect;
}