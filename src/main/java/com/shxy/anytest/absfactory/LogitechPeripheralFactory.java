package com.shxy.anytest.absfactory;

public class LogitechPeripheralFactory implements PeripheralFactory {

	@Override
	public Mouse createMouse() {
		// TODO Auto-generated method stub
		return new LogitechMouse();
	}

	@Override
	public KeyBoard createKeyBoard() {
		// TODO Auto-generated method stub
		return new LogitechKeyBoard();
	}

}
