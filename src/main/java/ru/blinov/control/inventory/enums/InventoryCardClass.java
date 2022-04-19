package ru.blinov.control.inventory.enums;

public enum InventoryCardClass {
	
	AIR_CONDITIONING("Air conditioning"),
	ELECTRICAL("Electrical"),
	ELECTRONICS("Electronics"),
	GAS("Gas"),
	HEATING("Heating"),
	HYDRAULICS("Hydraulics"),
	MECHANICAL("Mechanical"),
	VENTILATION("Ventilation");
	
	private final String name;
	
	InventoryCardClass(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
