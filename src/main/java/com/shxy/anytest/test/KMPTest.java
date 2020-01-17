package com.shxy.anytest.test;

public class KMPTest {
    public static void main(String[] args) {
        System.out.println(kmp("abacbcdhabci", "abababc"));
    }

    public static int kmp(String s, String t) {
        int i = 0;
        int j = 0;
        int[] next = getNext(t);
        while (i != s.length() && j != t.length()) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;//j归零
            } else {
                j = next[j];
            }
        }
        if (j == t.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
/*
if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
 */
}
