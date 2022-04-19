package ru.blinov.control.inventory.enums;

public enum Position {
	
	SPECIALIST("Specialist"),
	ENGINEER("Engineer"),
	SENIOR_ENGINEER("Senior Engineer"),
	COORDINATOR("Coordinator");
	
	private final String name;
	
	Position(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
