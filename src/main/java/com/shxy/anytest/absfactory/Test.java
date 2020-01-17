package com.shxy.anytest.absfactory;

public class Test {
    public static void main(String[] args){

        PeripheralFactory lFactory = new LogitechPeripheralFactory();
        PeripheralFactory rFactory = new RazerPeripheralFactory();

        KeyBoard lKeyBoard = lFactory.createKeyBoard();
        KeyBoard rKeyBoard = rFactory.createKeyBoard();

        Mouse lMouse = lFactory.createMouse();
        Mouse rMouse = rFactory.createMouse();


        System.out.println(lKeyBoard.click());
        System.out.println(rKeyBoard.click());

        System.out.println(lMouse.move());
        System.out.println(rMouse.move());
    }
}
