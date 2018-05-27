package com.concurrency.fundamental.buildingblocks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xwiam
 * @create 2018/05/27 17:24
 * @desc Using FutureTask to preload data that is needed later
 **/
public class PreLoader {

    ProductInfo loadProductInfo() throws DataLoadException{
        return null;
    }

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException{
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw LaunderThrowable.launderThrowable(cause);
            }
        }
    }

    interface ProductInfo{

    }

}

class DataLoadException extends Exception {

}
