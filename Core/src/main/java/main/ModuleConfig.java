package main;

import common.services.IEntityProcessingService;
import common.services.IGamePluginService;
import common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModuleConfig {
    /**
     *
     */

    public ModuleConfig() {
    }

    @Bean
    public Game game(){

        return new Game(gamePluginServices(),
                entityProcessingServiceList(),
                postEntityProcessingServices());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){

        return ServiceLocator.
                INSTANCE.
                locateAll(IEntityProcessingService.class);
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {

        return ServiceLocator.
                INSTANCE.
                locateAll(IGamePluginService.class);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLocator.
                INSTANCE.
                locateAll(IPostEntityProcessingService.class);
    }
}

