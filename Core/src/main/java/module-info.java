module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    exports main;
    opens main to javafx.graphics, spring.core;

    uses common.services.IGamePluginService;
    uses common.services.IEntityProcessingService;
    uses common.services.IPostEntityProcessingService;
}