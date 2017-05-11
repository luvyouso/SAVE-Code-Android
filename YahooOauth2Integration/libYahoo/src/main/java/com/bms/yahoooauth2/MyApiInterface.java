package com.bms.yahoooauth2;

import java.util.Map;

import rx.Subscription;

/**
 * Created by rahulkumar on 03/11/16.
 */

public interface MyApiInterface {

    Subscription HitApi(Map<String, Object> objectMap, String url, String authorization, String contentType);
}
