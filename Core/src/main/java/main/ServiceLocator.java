package common.util;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public enum ServiceLocator {

    INSTANCE;

    private static final Map<Class, ServiceLoader> loadermap = new HashMap<>();
    private final ModuleLayer layer;

    ServiceLocator() {
        try {
            // points to plugin folder
            Path pluginsDir = Paths.get("plugins");

            // we want a secondary from the main, for shared modules that plugins may require (asteroids require commonasteroids)
            Path modsDir = Paths.get("mods-mvn");
            ModuleFinder modsFinder = ModuleFinder.of(modsDir);

            // finds modular jars, gathers the module names
            ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

            // Find all names of all found plugin modules
            List<String> plugins = pluginsFinder
                    .findAll()
                    .stream()
                    .map(ModuleReference::descriptor)
                    .map(ModuleDescriptor::name)
                    .collect(Collectors.toList());

            // Create configuration for the plugin modules, first looking in plugins and the mods-mvn for shared modules
            Configuration pluginsConfiguration = ModuleLayer.boot().configuration().resolve(pluginsFinder, modsFinder, plugins);

            // from java building a configuration from resolve, this turns those configurations into module layers
            layer = ModuleLayer
                    // get the existing layer ( use the boot layer as a parent)
                    .boot()
                    // create new child layer
                    .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = loadermap.get(service);

        // looks in the plugin layer we named in the start of the file
        if (loader == null) {
            loader = ServiceLoader.load(layer, service);
            loadermap.put(service, loader);
        }

        List<T> list = new ArrayList<T>();

        if (loader != null) {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }
        }

        return list;
    }
}
