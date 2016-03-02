package org.dainst.chronontology.handler;

import org.dainst.chronontology.controller.Dispatcher;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static org.dainst.chronontology.Constants.*;
import static org.dainst.chronontology.util.JsonUtils.json;

/**
 * @author Daniel M. de Oliveira
 */
public class PutHandler extends BaseDocumentHandler {

    public PutHandler(Dispatcher dispatcher, RightsValidator rightsValidator) {
        super(dispatcher, rightsValidator);
    }

    public Object handle(
            final Request req,
            final Response res) throws IOException {


        DocumentModel dm= makeDocumentModel(req,res,false);
        if (dm==null) return json();


        int status;
        DocumentModel oldDm = DocumentModel.from(
                dispatcher.dispatchGet(type(req), simpleId(req)));

        if (oldDm!=null) {

            if (!userAccessLevelSufficient(req,oldDm, RightsValidator.Operation.EDIT)) {
                res.status(HTTP_FORBIDDEN);
                return json();
            } else {
                dm.merge(oldDm); // TODO Review neccessary to clarify what
                // happens if an enriched version gets fetched in connect mode and got merged with the incoming
                // json. Does the enriched version gets send to the main datastore then?
                status= HTTP_OK;
            }

        } else {
            status= HTTP_CREATED;
        }

        if (!dispatcher.dispatchPut(type(req), simpleId(req),dm.j()))
            res.status(HTTP_INTERNAL_SERVER_ERROR);
        else
            res.status(status);

        return dm;
    }
}
