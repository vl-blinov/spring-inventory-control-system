package ru.blinov.control.inventory.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

@DataJpaTest
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InventoryCardRepositoryTest {
	
	@Autowired
	private InventoryCardRepository sut;
	
	@Autowired
	private UserRepository userRepository;
	
	@Container
	private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");
														  
	@DynamicPropertySource
	public static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@Test
	public void Inventory_card_with_the_given_identifier_exists() {
		
		//Arrange
		String identifier = "67h95110e543";
		
		//Act
		boolean result = sut.existsByIdentifier(identifier);
		
		//Assert
		assertThat(result).isTrue();
	}
	
	@Test
	public void Inventory_card_with_the_given_identifier_does_not_exist() {
		
		//Arrange
		String identifier = "00h00000e000";
		
		//Act
		boolean result = sut.existsByIdentifier(identifier);
		
		//Assert
		assertThat(result).isFalse();
	}
	
	@Test
	public void Should_find_all_inventory_cards_created_by_the_given_user() {
		
		//Arrange		
		User user = userRepository.findById(6).get();
		int numberOfInventoryCardsCreatedByUser = user.getInventoryCards().size();
		
		//Act
		List<InventoryCard> result = sut.findAllByUser(user);
		
		//Assert
		assertThat(result).hasSize(numberOfInventoryCardsCreatedByUser);	
	}
}