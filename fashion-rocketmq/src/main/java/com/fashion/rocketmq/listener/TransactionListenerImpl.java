package com.fashion.rocketmq.listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现事务的监听接口
 *
 * @author Wuxf
 * @since 2020-07-28
 **/
public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 执行本地事务
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        LocalTransactionState state = LocalTransactionState.UNKNOW;
        try {
            int value = transactionIndex.getAndIncrement();
            int status = value % 3;
            localTrans.put(message.getTransactionId(), status);
            if (status == 1) {
                state = LocalTransactionState.COMMIT_MESSAGE;
            } else {
                state = LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    /**
     * 检查本地事务执行状态
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        LocalTransactionState state = LocalTransactionState.UNKNOW;
        Integer status = localTrans.get(msg.getTransactionId());
        if (status == 1) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }
}
