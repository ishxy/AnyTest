package com.shxy.anytest.test;

import java.util.LinkedList;

public class ZH {

    public static void main(String[] args) {
        String in = "a+b*c+(d*e+f)*g";
        StringBuilder res = new StringBuilder();
        LinkedList<Character> stack = new LinkedList<>();

        int index = 0;
        while (index < in.length()) {
            if (in.charAt(index) == ' ') {
                index++;
                continue;
            }
            char c = in.charAt(index);
            if (c >= 'a' && c <= 'z') {
                res.append(c);
            } else {
                if (c == ')') {
                    char out;
                    do {
                        out = stack.pop();
                        if (out != '(')//不输出'('
                            res.append(out);
                    } while (out != '(');
                } else {
                    if (stack.isEmpty() || c == '(' || isHigher(c, stack.getFirst())) {
                        stack.push(c);
                    } else {
                        do {
                            if (stack.getFirst() == '(')//只有在遇到')'才弹出'('
                                break;
                            res.append(stack.pop());
                        } while (!stack.isEmpty() && isHigher(stack.getFirst(),c));//直到c优先级大于栈顶
                        stack.push(c);//加入
                    }
                }
            }
            System.out.println(res);
            index++;
        }
        while (!stack.isEmpty()) {
            res.append(stack.poll());
        }
        System.out.println(res.toString());
    }

    public static boolean isHigher(Character a, Character b) {
        return getP(a) >= getP(b);
    }

    private static int getP(Character a) {
        switch (a) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 3;
        }
    }
}
