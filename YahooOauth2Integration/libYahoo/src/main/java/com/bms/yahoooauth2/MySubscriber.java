package com.bms.yahoooauth2;

import rx.Subscriber;


/**
 * Created by rahulkumar on 04/11/16.
 */

public class MySubscriber<T> extends Subscriber<T> {

    ResultInterface resultInterface;

    public MySubscriber(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

    @Override
    public void onCompleted() {
        ProgressDialogg.DismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        resultInterface.Error(e);
    }

    @Override
    public void onNext(T t) {
        resultInterface.Success(t);
    }
}
