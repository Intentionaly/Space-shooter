

module Collision {
    requires Common;
    provides common.services.IPostEntityProcessingService with collisionSystem.CollisionDetect;
}