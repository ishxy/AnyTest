package com.shxy.anytest.init;

import com.shxy.anytest.absfactory.*;

public class Test {
	public static void main(String[] args) {
		PeripheralFactory lFactory = new LogitechPeripheralFactory();
		PeripheralFactory rFactory = new RazerPeripheralFactory();

		Mouse lMouse = lFactory.createMouse();
		KeyBoard lKeyBoard = lFactory.createKeyBoard();

		Mouse rMouse = rFactory.createMouse();
		KeyBoard rKeyBoard = rFactory.createKeyBoard();

		System.out.println(lMouse.move());
		System.out.println(lKeyBoard.click());
		System.out.println(rMouse.move());
		System.out.println(rKeyBoard.click());
	}
}
