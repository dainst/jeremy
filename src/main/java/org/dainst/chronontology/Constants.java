package org.dainst.chronontology;

/**
 * @author Daniel M. de Oliveira
 */
public class Constants {

    public static final String VERSION = "version";
    public static final String MODIFIED = "modified";
    public static final String CREATED = "created";

    // A library for getting the status codes is not used on purpose
    // to protect against dependency change.
    static final int HTTP_CREATED = 201;
    static final int HTTP_FORBIDDEN = 403;
    static final int HTTP_OK = 200;
    static final int HTTP_NOT_FOUND = 404;
    static final int HTTP_UNAUTHORIZED = 401;

    static final String HEADER_AUTH = "Authorization";
    static final String HEADER_CT = "Content-Type";
    static final String HEADER_LOC = "location";
    static final String HEADER_JSON = "application/json";
}
