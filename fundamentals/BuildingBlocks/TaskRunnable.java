package com.concurrency.fundamental.buildingblocks;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;

/**
 * @author xwiam
 * @create 2018/05/27 17:10
 * @desc Restoring the Interrupted Status so as not to swallow the Interrupt
 **/
public class TaskRunnable implements Runnable{

    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            //restore interrrupted status,so that code higher up the call stack can see that an interrupt was issued.
            Thread.currentThread().interrupt();
        }
    }

    public void processTask(Task task) {
        //doSomething
    }
}
