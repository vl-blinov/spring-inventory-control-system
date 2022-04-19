package ru.blinov.control.inventory.enums;

public enum Department {

	PLANT_ENGINEERING("Plant Engineering"),
	INDUSTRIAL_SAFETY("Industrial Safety"),
	MAINTENANCE("Maintenance"),
	PRODUCTION_MANAGEMENT("Production Management"),
	QUALITY_CONTROL("Quality Control"),
	PURCHASING("Purchasing"),
	IT("Information Technology");
	
	private final String name;
	
	Department(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
