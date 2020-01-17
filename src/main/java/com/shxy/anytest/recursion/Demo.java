package com.shxy.anytest.recursion;

public class Demo {
    public void Perm(int list[], int k, int m) {
        if (k == m) {
            for (int i = 0; i <= m; i++)
                System.out.print(list[i]);
            System.out.println();
        } else {
            for (int i = k; i <= m; i++) {
                // 从固定的数后第一个依次交换
                Swap(list, k, i);
                Perm(list, k + 1, m);
                // 这组递归完成之后需要交换回来
                Swap(list, k, i);
            }
        }

    }
    public void Swap(int[] list, int i, int j) {
        int t = list[i];
        list[i] = list[j];
        list[j] = t;
    }
    public static void main(String[] args) {
        Demo d = new Demo();
        int[] arr = {1,2,3,4};
        d.Perm(arr, 0, 3);
    }
}