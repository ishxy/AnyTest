package com.shxy.anytest.cjn;

import java.lang.reflect.Proxy;
import java.util.*;

public class Test {
    public static void main(String[] args){
        Vector<Vector<Integer>> arr = new Vector<>();
        List<Node> list = new ArrayList<>();
        for (int i = 0;i<5;i++){
            arr.add(new Vector<>(Arrays.asList(1,2,3,4,8,6,7)));
            list.add(new Node(-999,-999));
        }

        for(int i=0;i<arr.size();i++){
            Vector<Integer> v = arr.get(i);
            Node node = list.get(i);
            for(int j =0;j<v.size();j++){
                if (v.get(j)>node.first){
                    node.sencond = node.first;
                    node.first = v.get(j);

                }else if(v.get(j)>node.sencond){
                    node.sencond = v.get(j);
                }
            }
        }

        for(int i =0;i<arr.size();i++){
            Node node = list.get(i);
            System.out.println("No." + i + " 差 = " + (node.first - node.sencond));
        }

    }

    private static class Node{
        int first = 0;
        int sencond = 0;

        public Node(int first, int sencond) {
            this.first = first;
            this.sencond = sencond;
        }
    }
}
