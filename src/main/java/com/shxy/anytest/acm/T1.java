package com.shxy.anytest.acm;

import java.nio.charset.Charset;
import java.util.*;

public class T1 {
    public static void main(String[] args) {

        Integer n;
        Scanner cin = new Scanner(System.in);
        n = cin.nextInt();
        if (n == 0)
            return;
        List<String> list = new LinkedList<String>();
        StringBuilder res = new StringBuilder();
        for (int p =0;p<n;p++){
            String c = cin.next();
            list.add(c);
        }

        for (int i=0;i<n-1;i++){
            int s = 0,e = list.size()-1;
            String head = list.get(s);
            String last = list.get(e);

            String phead = list.get(s);
            String plast = list.get(e);
            while(phead.equals(plast)){
               // System.out.println("*");
                s++;
                e--;
                if (e<s){
                    break;
                }
                phead = list.get(s);
                plast = list.get(e);
            }
            if (phead.compareTo(plast)<0){
                res.append(head);
                list.remove(s);
            } else {
                res.append(last);
                list.remove(list.size()-1);
            }
        }
        res.append(list.get(0));
        String s = res.toString();
        int x=1;
        if (res.length() - x*80>=0){
            s = res.substring((x-1)*80,(x-1)*80+80);
            System.out.println(s);
            x++;
        }
        if (res.length()%80!=0) {
            System.out.println(res.substring((x - 1) * 80, res.length()));
        }
    }
}

