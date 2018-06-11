package com.concurrency.fundamental.cancellatonandshutDown;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xwiam
 * @create 2018/06/11 17:03
 * @desc using a volatile field to Hold Cancellation state
 **/

@ThreadSafe
public class PrimeGenerator implements Runnable{

    private static ExecutorService exec = Executors.newCachedThreadPool();
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    static List<BigInteger> aScecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try{
            TimeUnit.SECONDS.sleep(1);
        }finally {
            generator.cancel();
        }
        return generator.get();
    }
}
