package org.dainst.chronontology.controller;

/**
 */

import com.fasterxml.jackson.databind.JsonNode;
import org.dainst.chronontology.util.Results;
import org.dainst.chronontology.store.SearchableDatastore;
import spark.Request;

import java.io.IOException;

/**
 *
 *
 * @author Daniel M. de Oliveira
 */
public class SimpleController extends Controller {

    protected final SearchableDatastore connectDatastore;

    public SimpleController(SearchableDatastore connectDatastore) {
        this.connectDatastore= connectDatastore;
    }

    @Override
    protected JsonNode _get(String bucket, String key) {
        return connectDatastore.get(bucket,key);
    }

    @Override
    protected void _addDatatoreStatus(Results r) throws IOException {
        r.add(makeDataStoreStatus("main",connectDatastore));
    }

    @Override
    protected void _handlePost(String bucket, String key, JsonNode value) {
        connectDatastore.put(bucket,key, value);
    }

    @Override
    protected void _handlePut(String bucket, String key, JsonNode value) {
        connectDatastore.put(bucket,key, value);
        connectDatastore.put(bucket,key, value);
    }

    @Override
    protected JsonNode _handleGet(String bucket, String key, Request req) {
        return connectDatastore.get(bucket,key);
    }

    @Override
    protected JsonNode _handleSearch(String bucket, String query) {
        return connectDatastore.search( bucket, query );
    }
}
