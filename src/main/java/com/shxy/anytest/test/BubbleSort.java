package com.shxy.anytest.test;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 6, 8, 7, 9};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
