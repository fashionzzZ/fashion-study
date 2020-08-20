package com.fashion.algorithm.sort;

/**
 * 归并排序
 *
 * @author Wuxf
 * @since 2020-07-17
 **/
public class MergeSort {

    /**
     * 递归方法实现
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        // 让左部分有序
        process(arr, l, m);
        // 让右部分有序
        process(arr, m + 1, r);
        // merge左右部分，让整体有序
        merge(arr, l, m, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }

    /**
     * 非递归方法实现
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        int mergeSize = 1;
        while (mergeSize < n) {
            int l = 0;
            while (l < n) {
                int m = l + mergeSize - 1;
                if (m >= n) {
                    break;
                }
                int r = Math.min(m + mergeSize, n - 1);
                merge(arr, l, m, r);
                l = r + 1;
            }
            // 防止mergeSize超出整形最大值
            if (mergeSize > n / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Util.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Util.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!Util.isEqual(arr1, arr2)) {
                succeed = false;
                Util.printArray(arr1);
                Util.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
