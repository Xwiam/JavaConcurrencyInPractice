package com.concurrency.fundamental.avoidlivenesshazards;

/**
 * @author xiwam
 * @Date 2019/2/13 14:44
 * @Desc inducing a Lock Ordering to Avoid DeadLock
 *In the rare case that two objects have the same hash code,we must use an arbitrary means of ordering the lock acquisitions,and this reintroduces
 * the possibility of deadlock.To prevent inconsistent lock ordering in this case,a third "tie breaking" lock is used.
 */
public class TransferAmount {

    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAccount,final Account toAccount,final int totalAccount) {

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

class Account{

    private int count;

    public Account(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void debit(int count) {
        this.count -= count;
    }

    public void credit(int count) {
        this.count += count;
    }
}
