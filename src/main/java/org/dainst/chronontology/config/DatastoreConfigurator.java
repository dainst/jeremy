package org.dainst.chronontology.config;

import org.apache.log4j.Logger;
import org.dainst.chronontology.store.Datastore;
import org.dainst.chronontology.store.ESRestSearchableDatastore;
import org.dainst.chronontology.store.FileSystemDatastore;
import org.dainst.chronontology.store.rest.JsonRestClient;

import java.io.File;

/**
 * @author Daniel M. de Oliveira
 */
public class DatastoreConfigurator implements Configurator<Datastore,DatastoreConfig> {

    public Datastore configure(DatastoreConfig config) {

        if (config.getType().equals(ConfigConstants.DATASTORE_TYPE_ES)) {
            return new ESRestSearchableDatastore(
                    new JsonRestClient(config.getUrl()),config.getIndexName());
        } else {
            return initDS(config);
        }
    }

    private FileSystemDatastore initDS(DatastoreConfig config) {

        String datastorePath= config.getPath();
        return new FileSystemDatastore(datastorePath);
    }
}
