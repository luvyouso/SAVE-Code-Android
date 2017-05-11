package com.bms.yahoooauth2;

import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rahulkumar on 03/11/16.
 */

public class MethodClass<T> {

    MyService myService;
    Subscription subscription;
    ResultInterface result;

    public MethodClass(ResultInterface result) {
        this.result = result;
    }


    public Subscription ApiProcess(Map<String, Object> map, String url, String authorization, String contentType) {
        switch (url) {
            case Constants.OAUTH_TOKEN_URL:
                myService = ServiceFactory.createRetrofitService(MyService.class, Constants.COMMON_URL);
                subscription = myService.getAccessToken(map, authorization, contentType).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new MySubscriber(result));
                break;
            case Constants.PROFILE_URL:
                myService = ServiceFactory.createRetrofitService(MyService.class, map.get("url").toString());
                subscription = myService.getProfile(map.get("yahoo_id").toString(), authorization).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new MySubscriber(result));
                break;
            case Constants.CONNECTIONS_URL:
                myService = ServiceFactory.createRetrofitService(MyService.class, map.get("url").toString());
                subscription = myService.getConnections(map.get("yahoo_id").toString(), authorization).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new MySubscriber(result));
                break;
            case Constants.CONTACTS_URL:
                myService = ServiceFactory.createRetrofitService(MyService.class, map.get("url").toString());
                subscription = myService.getContacts(map.get("yahoo_id").toString(), authorization).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new MySubscriber(result));
                break;
        }
        return subscription;
    }


}
