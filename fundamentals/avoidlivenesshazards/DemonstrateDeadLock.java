package com.concurrency.fundamental.avoidlivenesshazards;

import java.util.Random;

/**
 * @author xiwam
 * @Date 2019/2/13 17:16
 * @Desc
 */
public class DemonstrateDeadLock {

    private static final Object tieLock = new Object();
    private static final int NUM_THREAD = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATION = 100000;

    public static void main(String[] args) {
        final Random random = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(random.nextInt(1000000));
        }

        for (int i = 0; i < NUM_THREAD; i++) {
            new Thread() {
                public void run() {
                    for (int i = 0; i < NUM_ITERATION; i++) {
                        int fromAccount = random.nextInt(NUM_ACCOUNTS);
                        int toAccount = random.nextInt(NUM_ACCOUNTS);
                        int amount = 1;
                        transferMoney(accounts[fromAccount], accounts[toAccount], amount);
                    }
                }
            }.start();
        }
    }

    public static void transferMoney(final Account fromAccount,final Account toAccount,final int totalAccount) {

        class Helper{
            public void transfer() {
                if (fromAccount.getCount() < totalAccount) {
                    throw new IllegalArgumentException("fromAccount can't transfer " + totalAccount);
                } else {
                    fromAccount.debit(totalAccount);
                    toAccount.credit(totalAccount);
                }
            }
        }
        //use System.identityHashCode to induce a lock ordering.It involves a few extra lines of code,but eliminates the possibility of deadlock.
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        if (fromHash > toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash < toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }
}
