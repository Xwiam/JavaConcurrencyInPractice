package com.concurrency.fundamental.cancellatonandshutDown;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xwiam
 * @create 2018/06/11 18:08
 * @desc atomically check for shutdown and conditionally increment a counter to "reserve" the right to submit a message
 **/
public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuardedBy("this") private boolean isShutdown;
    @GuardedBy("this") private int reservations;

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingDeque<String>();
        this.loggerThread = new LoggerThread();
        this.writer = new PrintWriter(writer);
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("");
            }
            ++reservations;
        }
        queue.put(msg);
    }


    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try{
                while (true) {
                    try {
                        synchronized (this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (this) {
                            --reservations;
                        }
                    } catch (InterruptedException e) {
                        //retry
                    }
                }
            }finally {
                writer.close();
            }
        }
    }
}

