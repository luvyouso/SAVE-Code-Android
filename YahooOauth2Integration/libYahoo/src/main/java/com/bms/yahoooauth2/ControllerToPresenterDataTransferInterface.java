package com.bms.yahoooauth2;

/**
 * Created by rahulkumar on 04/11/16.
 */

public interface ControllerToPresenterDataTransferInterface<T> {

    void transferapiResult(T t);

    void transferError(Throwable e);
}
