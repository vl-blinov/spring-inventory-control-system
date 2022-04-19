package ru.blinov.control.inventory.enums;

public enum Authority {
	
	ROLE_USER("ROLE_USER"),
	ROLE_MASTER("ROLE_MASTER"),
	ROLE_ADMIN("ROLE_ADMIN");
	
	private final String name;
	
	Authority(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
