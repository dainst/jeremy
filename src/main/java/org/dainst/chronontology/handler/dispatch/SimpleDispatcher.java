package org.dainst.chronontology.handler.dispatch;

/**
 */

import com.fasterxml.jackson.databind.JsonNode;
import org.dainst.chronontology.handler.ServerStatusHandler;
import org.dainst.chronontology.handler.model.Results;
import org.dainst.chronontology.store.SearchableDatastore;
import spark.Request;

import java.io.IOException;

/**
 * The simple dispatcher holds a single searchable datastore.
 *
 * @author Daniel M. de Oliveira
 */
public class SimpleDispatcher extends Dispatcher {

    protected final SearchableDatastore datastore;

    public SimpleDispatcher(SearchableDatastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public JsonNode dispatchGet(final String bucket, final String key) {
        return datastore.get(bucket,key);
    }

    @Override
    public void addDatatoreStatus(ServerStatusHandler handler, Results r) throws IOException {
        r.add(handler.makeDataStoreStatus("main", datastore));
    }

    @Override
    public boolean dispatchPost(final String bucket, final String key, final JsonNode value) {
        return datastore.put(bucket,key, value);
    }

    @Override
    public boolean dispatchPut(final String bucket, final String key, final JsonNode value) {
        return datastore.put(bucket,key, value);
    }

    @Override
    public JsonNode dispatchGet(final String bucket, final String key,
                                final Boolean direct, // ignored
                                final Integer version
        ) {
        return dispatchGet(bucket,key);
    }

    @Override
    public Results dispatchSearch(final String bucket, final String query) {
        return datastore.search( bucket, query );
    }
}
