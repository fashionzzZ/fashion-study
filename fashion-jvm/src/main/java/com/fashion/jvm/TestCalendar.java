package com.fashion.jvm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 测试时间
 *
 * @author Wuxf
 * @since 2020-06-02 19:22:11
 **/
public class TestCalendar {
    public static void main(String[] args) {
//        BigDecimal amount = new BigDecimal("99.80");
//        System.out.println(yuanToFen(amount));
//        for (int i = 0; i < 10; i++) {
//
//            System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        }

//        BigDecimal bigDecimal = new BigDecimal("0.00");
//        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
//            System.out.println("相等");
//        }
//        System.out.println(getMonth());
//        period(24);
        int [] arr = {52,35,17,76,89,9,43,3,12,34,5,67,8,98,45};
        int k = 5;
        Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));
        for (int i : arr) {
            if (heap.size() < k || i < heap.peek()) {
                heap.offer(i);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 将堆中的元素存入数组
        int j = 0;
        for (int e : heap) {
            System.out.println(e);
        }
    }

    /**
     * 元转分
     */
    private static String yuanToFen(BigDecimal amount) {
        amount = amount.multiply(new BigDecimal("100"));
        amount = amount.setScale(0, RoundingMode.DOWN);
        return String.valueOf(amount);
    }

    private static int getMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    private static void period(int freePeriodicals) {
        LocalDateTime localDateTime = LocalDateTime.now();
        while (freePeriodicals > 0) {
            for (int i = 1; i < 3; i++) {
                int periodicalNum = (localDateTime.getMonthValue() - 1) * 2 + i;
                // 生成期刊号
                String period = localDateTime.getYear() + "年第" + periodicalNum + "期";
                System.out.println(period);
                freePeriodicals--;
            }
            localDateTime = localDateTime.plusMonths(1);
        }
    }
}
