package com.shxy.anytest.test;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args){
        int[] arr = {4, 1, 2, 6, 8, 7, 9};
        for (int i = 0; i < arr.length; i++) {
            int maxi = i;
            int max = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]>max){
                    max = arr[j];
                    maxi = j;
                }
            }
            if (maxi!=i){
                int t = arr[i];
                arr[i] = max;
                arr[maxi] = t;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
