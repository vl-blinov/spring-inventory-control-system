package ru.blinov.control.inventory.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class IdentifierGeneratorTest {
	
	@Test
	public void Should_generate_an_identifier_that_matches_the_pattern() {
		
		//Arrange
		String regex = "(\\d{2}h\\d{5}e\\d{3})";
		Pattern pattern = Pattern.compile(regex);
		
		//Act
		String identifier = IdentifierGenerator.randomIdentifier();
		
		//Assert
		Matcher matcher = pattern.matcher(identifier);
		assertThat(matcher.find()).isTrue();
	}
}
