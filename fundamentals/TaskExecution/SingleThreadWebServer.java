package com.concurrency.fundamental.TaskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xwiam
 * @create 2018/06/07 18:25
 * @desc Sequential Web Server
 **/
public class SingleThreadWebServer {

    public static void main(String[] args) throws IOException{
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connecton = socket.accept();
            handleRequest(connecton);
        }
    }

    private static void handleRequest(Socket socket) {
        //request-handling logic here
    }
}
