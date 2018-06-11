package com.concurrency.fundamental.cancellatonandshutDown;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xwiam
 * @create 2018/06/08 15:35
 * @desc Unreliable Cancellation that can Leave Producers Stuck in a Blocking Operation.Don't do this
 **/
public class BrokenPrimeProducer extends Thread{

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                //while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancel() {
        cancelled = true;
//        interrupt();  using Interruption for cancellation
    }

    public static void main(String[] args) {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(10);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }finally {
            producer.cancel();
        }
    }
}
