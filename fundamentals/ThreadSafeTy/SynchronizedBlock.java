package com.concurrency.fundamental.threadSafe;

/**
 * @author xwiam
 * @create 2018/04/19 19:31
 * @desc synchronize the mutable and sharable variable
 **/
public class SynchronizedBlock {

    private long count;

    private ExpensiveObject expensiveObject = null;

    public long getCount() {
        return count;
    }

    public ExpensiveObject getExpensiveObject() {
        return expensiveObject;
    }

    public void blockCompute() {
        for (int i = 0;i < 100000; i++) {
            System.out.println("i = " + i);
        }
    }


    /**
     *  not threadsafe
     */
    @NotThreadSafe
    public void synchronize() {
        System.out.println(" start time = " +System.currentTimeMillis());
            count++;
            blockCompute();
            if (expensiveObject == null) {
                expensiveObject = new ExpensiveObject();
            }
        System.out.println(" end time = " + System.currentTimeMillis());
    }

    /**
     *  because of System.out.println() synchronize this object ,JVM optimize the mechanism of synchronization
     */
    @ThreadSafe
    public void synchronize2() {
        System.out.println(" start time = " +System.currentTimeMillis());
        synchronized (this) {
            count++;
            blockCompute();
            if (expensiveObject == null) {
                expensiveObject = new ExpensiveObject();
            }
        }
        System.out.println(" end time = " + System.currentTimeMillis());
    }

    /**
     * synchronize the mutable and sharable variable , not include the invariant
     *
     */
    @ThreadSafe
    public void synchronize3() {
        System.out.println(" start time = " +System.currentTimeMillis());
        synchronized (this) {
            count++;
        }
            blockCompute();
        synchronized (this) {
            if (expensiveObject == null) {
                expensiveObject = new ExpensiveObject();
            }
        }
        System.out.println(" end time = " + System.currentTimeMillis());
    }


    public static void main(String[] args) {

        final SynchronizedBlock block = new SynchronizedBlock();
        for (int i = 0;i < 10 ;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    block.synchronize();
                    System.out.println(Thread.currentThread().getName() + " : " + block.count);
                    System.out.println(block.expensiveObject);
                }
            }).start();
        }
    }
}
