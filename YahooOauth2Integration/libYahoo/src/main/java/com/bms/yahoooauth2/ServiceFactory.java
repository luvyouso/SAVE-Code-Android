package com.bms.yahoooauth2;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.JacksonConverter;

/**
 * Created by rahulkumar on 03/11/16.
 */

public class ServiceFactory {


    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     *
     * @param clazz    Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(new JacksonConverter())
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("profile"))
                .build();
        T service = restAdapter.create(clazz);
        return service;
    }
}
