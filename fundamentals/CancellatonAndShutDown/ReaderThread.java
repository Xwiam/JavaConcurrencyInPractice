package com.concurrency.fundamental.cancellatonandshutDown;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author xwiam
 * @create 2018/06/11 17:28
 * @desc Encapsulating Nonstandard Cancellation in a Thread by Overriding Interrupt
 **/
public class ReaderThread extends Thread{

    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket,InputStream in) throws IOException{
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {

        }finally {
            super.interrupt();
        }
    }


    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            /**
             * Allow thread to exit
             */
        }
    }

    public void processBuffer(byte[] buf, int count) {

    }
}
