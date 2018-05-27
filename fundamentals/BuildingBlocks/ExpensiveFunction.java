package com.concurrency.fundamental.buildingblocks;

import java.math.BigInteger;

/**
 * @author xwiam
 * @create 2018/05/27 17:47
 * @desc
 **/
public class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //after deep thought
        return new BigInteger(arg);
    }
}
