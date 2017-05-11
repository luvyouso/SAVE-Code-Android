package com.bms.yahoooauth2;

/**
 * Created by rahulkumar on 25/02/17.
 */

public class Constants {

    public static final String COMMON_URL = "https://api.login.yahoo.com/oauth2";
    public static final String SOCIAL_URL = "https://social.yahooapis.com/v1/user";
    //    public static final String CLIENT_ID="dj0yJmk9b1VPa283RTRab0tDJmQ9WVdrOVdrMXFRVGg0TlRBbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD05ZQ--";
    public static final String CLIENT_ID = "dj0yJmk9QmlDMnRBcVJpbm1BJmQ9WVdrOVZIaFZWRE53TTJNbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD03Mw--";
    //    public static final String CLIENT_SECRET="1156fd53e149083da7f79b53287c0d2ee329b932";
    public static final String CLIENT_SECRET = "f014349e248fb12a1ed6b6c541207e3325b0facf";
    public static final String CODE = "code";
    public static final String REDIRECT_URI = "https://www.google.com";
    public static final String OAuth_URI = COMMON_URL + "/request_auth?" + "client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code&language=en-us";
    public static final String OAUTH_TOKEN_URL = "/get_token";
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_SECRET_KEY = "client_secret";
    public static final String REDIRECT_URI_KEY = "redirect_uri";
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_VALUE = "authorization_code";
    public static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
    public static final String PROFILE_URL = "/{yahoo_id}/profile?format=json";
    public static final String CONNECTIONS_URL = "/{yahoo_id}/connections";
    public static final String CONTACTS_URL = "/{yahoo_id}/contacts?format=json";
}
