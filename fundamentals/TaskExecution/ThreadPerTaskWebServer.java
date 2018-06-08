package com.concurrency.fundamental.TaskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xwiam
 * @create 2018/06/07 18:28
 * @desc Web Server that start a New Thread for Each Request
 **/
public class ThreadPerTaskWebServer {

    public static void main(String[] args)  throws IOException{
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket socket) {
        //request-handling logic here
    }
}
