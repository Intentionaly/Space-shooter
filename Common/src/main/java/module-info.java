module Common{
    exports common.services;
    exports common.data;
    // for core to use the servicelocator
    uses common.services.IEntityProcessingService;
    uses common.services.IGamePluginService;
    uses common.services.IPostEntityProcessingService;
}