module Mothership {
    requires Common;
    requires CommonMothership;
    provides common.services.IGamePluginService with MothershipSystem.MothershipPlugin;
}