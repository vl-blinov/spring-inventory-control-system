package ru.blinov.control.inventory.enums;

public enum Enabled {
	
	YES(1),
	NO(0);
	
	private final int value;
	
	Enabled(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
