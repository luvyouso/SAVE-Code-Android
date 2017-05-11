package com.bms.yahoooauth2;


import java.util.Map;

import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by rahulkumar on 03/11/16.
 */

public interface MyService<T> {

    @FormUrlEncoded
    @POST(Constants.OAUTH_TOKEN_URL)
    Observable<T> getAccessToken(@FieldMap Map<String, Object> data, @Header("Authorization") String header, @Header("Content-Type") String contentType);

    @GET(Constants.PROFILE_URL)
    Observable<T> getProfile(@Path("yahoo_id") String yahooid, @Header("Authorization") String header);

    @GET(Constants.CONNECTIONS_URL)
    Observable<T> getConnections(@Path("yahoo_id") String yahooid, @Header("Authorization") String header);

    @GET(Constants.CONTACTS_URL)
    Observable<T> getContacts(@Path("yahoo_id") String yahooid, @Header("Authorization") String header);
}
