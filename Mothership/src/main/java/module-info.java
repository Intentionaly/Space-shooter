module Mothership {
    requires Common;
    provides common.services.IGamePluginService with MothershipSystem.MothershipPlugin;
}