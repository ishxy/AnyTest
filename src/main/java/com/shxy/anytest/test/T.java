package com.shxy.anytest.test;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class T {

    public static void main(String[] args) {
        float[][] matric = {
                {1, 0, 0, 0, 0},
                {0, 1, 0.1f, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0.2f, 0, 1, 0},
                {0.2f, 0.1f, 0.1f, 0.5f, 1},
        };
        Scanner cin = new Scanner(System.in);
        String color = cin.next();
        Integer[] icolor = getColor(color);
        Integer[] res = new Integer[5];
        System.out.println("icolor = " + Arrays.toString(icolor));
        Arrays.fill(res,0);
        for(int i =0;i<5;i++){
            float t = 0;
            for(int j=0;j<5;j++){
                float c = matric[j][i];
                t += icolor[j]*matric[i][j];
            }
            if (i!=4)
                res[i] = (int)t;
            else
                res[i] = (int)t;
        }
        for(int i =0;i<res.length-1;i++){
            String s = new BigInteger(res[i].toString()).toString(16);
            System.out.print(s.toUpperCase());
        }
        System.out.println();
    }

    private static Integer[] getColor(String color) {
        Integer[] res = new Integer[5];
        for(int i =0;i<4;i++){
            res[i] = Integer.valueOf(color.substring(i*2,(i*2)+2),16);
        }
        res[4] = 1;
        return res;
    }

    public static void T1() {
        Scanner cin = new Scanner(System.in);
        String line = cin.nextLine();
        String[] each = line.split(" ");
        String res = each[0];
        for (int i = 1; i < each.length; i++) {
            res = f(res, each[i]);
        }
        System.out.println(res);
    }

    public static String f(String a, String b) {
        a = removeZ(a);
        b = removeZ(b);
        int sizea = a.length();
        int sizeb = b.length();
        int max = Math.max(sizea, sizeb);
        int min = Math.min(sizea, sizeb);
        if (sizea > sizeb) {
            b = change(b, sizea - sizeb);
        } else if (sizea < sizeb) {
            a = change(a, sizeb - sizea);
        }
        char[] res = new char[max];
        boolean y = false;
        for (int i = max - 1; i >= 0; i--) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if (c1 == '0' && c2 == '0') {
                res[i] = y ? '1' : '0';
                y = false;
            } else if (c1 == '1' && c2 == '0') {
                res[i] = y ? '0' : '1';
            } else if (c1 == '0' && c2 == '1') {
                res[i] = y ? '0' : '1';
            } else {//1 ,1
                res[i] = y ? '1' : '0';
                y = true;
            }
        }
        StringBuilder builder = new StringBuilder();
        if (y) {
            builder.append('1');
        }
        for (int i = 0; i < max; i++) {
            builder.append(res[i]);
        }
        return builder.toString();
    }

    //1111 11
    public static String removeZ(String s) {
        int i;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0')
                break;
        }
        return s.substring(i);
    }

    public static String change(String s, int sum) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sum; i++) {
            builder.append('0');
        }
        builder.append(s);
        return builder.toString();
    }
}
