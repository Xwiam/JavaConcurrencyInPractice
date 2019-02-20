package com.concurrency.fundamental.performanceandscalability;

import com.concurrency.fundamental.cancellatonandshutDown.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiwam
 * @Date 2019/2/20 1:48
 * @Desc
 */
public class ServerStatus {
    @GuardedBy("this") public final Set<String> users = new HashSet<String>();
    @GuardedBy("this") public final Set<String> queries = new HashSet<String>();

    public synchronized void addUser(String u) {
        users.add(u);
    }

    public synchronized void addQuery(String q) {
        queries.add(q);
    }

}

class BetterServerStatus {
    @GuardedBy("users") public final Set<String> users = new HashSet<String>();
    @GuardedBy("queries") public final Set<String> queries = new HashSet<String>();

    //split the lock,only lock users,not this
    public void betterAddUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }

    //split the lock,only lock queries,not this
    public void betterAddQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        }
    }
}
