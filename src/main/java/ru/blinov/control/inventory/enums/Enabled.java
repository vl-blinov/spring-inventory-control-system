package ru.blinov.control.inventory.enums;

public enum Enabled {
	
	YES(1),
	NO(0);
	
	private final Integer value;
	
	Enabled(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
}
