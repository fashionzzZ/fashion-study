package com.fashion.netty.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wuxf
 * @date 2020/08/21
 **/
public class SelectorThreadGroup {
    SelectorThread[] threads;
    ServerSocketChannel server = null;
    AtomicInteger xid = new AtomicInteger(0);

    public SelectorThreadGroup(int nums) {
        threads = new SelectorThread[nums];
        for (int i = 0; i < nums; i++) {
            threads[i] = new SelectorThread();
            new Thread(threads[i], "nio-thread-" + i).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            nextSelector(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextSelector(Channel channel) {
        SelectorThread thread = next();
        thread.queue.add(channel);
        thread.selector.wakeup();
    }

    private SelectorThread next() {
        int index = xid.incrementAndGet() % threads.length;
        return threads[index];
    }
}
