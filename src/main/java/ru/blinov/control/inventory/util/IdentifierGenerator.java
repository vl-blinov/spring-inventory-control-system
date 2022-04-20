package ru.blinov.control.inventory.util;

import org.apache.commons.lang3.RandomStringUtils;

public class IdentifierGenerator {
	
	public static String randomIdentifier() {
		
		String part1 = RandomStringUtils.randomNumeric(2);
		String part2 = RandomStringUtils.randomNumeric(5);
		String part3 = RandomStringUtils.randomNumeric(3);
		
		String identifier = part1.concat("h").concat(part2).concat("e").concat(part3);
		
		return identifier;
	}

}
