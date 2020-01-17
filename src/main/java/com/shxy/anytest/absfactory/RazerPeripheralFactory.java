package com.shxy.anytest.absfactory;

public class RazerPeripheralFactory implements PeripheralFactory {

	@Override
	public Mouse createMouse() {
		// TODO Auto-generated method stub
		return new RazerMouse();
	}

	@Override
	public KeyBoard createKeyBoard() {
		// TODO Auto-generated method stub
		return new RazerKeyBoard();
	}

}
