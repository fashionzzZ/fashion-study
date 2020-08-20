package com.fashion.netty.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 客户端
 *
 * @author Wuxf
 * @since 2020-08-11
 **/
public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 7397);
            System.out.println("itstack-demo-netty client start done");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
