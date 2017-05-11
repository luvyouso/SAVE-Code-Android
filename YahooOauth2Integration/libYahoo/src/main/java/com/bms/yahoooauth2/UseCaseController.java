package com.bms.yahoooauth2;

import android.app.Activity;

import java.util.Map;

import rx.Subscription;

/**
 * Created by rahulkumar on 03/11/16.
 */

public class UseCaseController<T> implements MyApiInterface, ResultInterface<T> {

    ControllerToPresenterDataTransferInterface controllerToPresenterDataTransferInterface;
    MethodClass methodClass;
    Subscription subscription;
    Activity activity;

    public UseCaseController(ControllerToPresenterDataTransferInterface controllerToPresenterDataTransferInterface, Activity activity) {
        this.controllerToPresenterDataTransferInterface = controllerToPresenterDataTransferInterface;
        this.activity = activity;
        methodClass = new MethodClass(this);
    }

    @Override
    public Subscription HitApi(Map<String, Object> objectMap, String url, String authorization, String contentType) {
        subscription = methodClass.ApiProcess(objectMap, url, authorization, contentType);
        return subscription;
    }

    @Override
    public void Success(T o) {
        controllerToPresenterDataTransferInterface.transferapiResult(o);
    }

    @Override
    public void Error(Throwable e) {

        controllerToPresenterDataTransferInterface.transferError(e);
    }
}
