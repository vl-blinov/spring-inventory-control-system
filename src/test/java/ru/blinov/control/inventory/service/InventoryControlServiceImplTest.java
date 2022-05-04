package ru.blinov.control.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.enums.Authority;
import ru.blinov.control.inventory.enums.Department;
import ru.blinov.control.inventory.enums.Enabled;
import ru.blinov.control.inventory.enums.InventoryCardClass;
import ru.blinov.control.inventory.enums.Position;
import ru.blinov.control.inventory.util.IdentifierGenerator;

@SpringBootTest
@Testcontainers
public class InventoryControlServiceImplTest {
	
	@Autowired
	private InventoryControlService sut;
	
	@Container
	private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");
														  
	@DynamicPropertySource
	public static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}
	
	//User
	
	@Test
	public void Should_find_all_users_and_show_them_on_one_page() {

		//Act
		Page<User> result = sut.findAllUsers(0, 24);
		
		//Assert
		assertThat(result).size().isEqualTo(24);
	}

	@Test
	public void Should_find_a_user_by_given_id() {
		
		//Act
		User result = sut.findUserById(1);
		
		//Assert
		assertThat(result.getId()).isEqualTo(1);
	}

	@Test
	public void Should_find_a_user_by_given_username() {

		//Act
		User result = sut.findUserByUsername("jackobrien");
		
		//Assert
		assertThat(result.getUsername()).isEqualTo("jackobrien");
	}

	@Test
	public void Should_insert_a_new_user() {
		
		//Arrange
		User user = new User();
		
		user.setUsername("harrypotter");
		user.setEnabled(Enabled.YES.getValue());
		user.setAuthority(Authority.ROLE_USER.getName());
		user.setFirstName("Harry");
		user.setLastName("Potter");
		user.setDepartment(Department.PLANT_ENGINEERING.getName());
		user.setPosition(Position.ENGINEER.getName());
		user.setPhone("+353 1 325 0707");
		user.setEmail("harrypotter@amics.com");
		
		//Act
		sut.saveUser(user);
		
		//Assert
		assertThat(user.getId()).isNotEqualTo(0);
	}

	@Test
	@Transactional
	public void Should_update_a_user() {
		
		//Arrange
		User user = sut.findUserById(1);
		
		String oldPhone = user.getPhone();
		String newPhone = oldPhone + "777";
		user.setPhone(newPhone);

		//Act
		sut.saveUser(user);

		//Assert
		assertThat(sut.findUserById(1).getPhone()).isEqualTo(newPhone);
	}
	
	@Test
	@Transactional
	public void Should_delete_a_given_user_from_all_inventory_cards_created_by_this_user() {
		
		//Arrange
		InventoryCard inventoryCard = sut.findInventoryCardById(1);
		User user = inventoryCard.getUser();
		
		//Act
		sut.deleteInventoryCardUser(user);

		//Assert
		assertThat(inventoryCard.getUser()).isNull();
	}

	@Test
	@Transactional
	public void Should_delete_a_user_by_given_id() {

		//Act
		sut.deleteUserById(1);

		//Assert
		assertThrows(NoSuchElementException.class, () -> sut.findUserById(1));
	}
	
	//Inventory card

	@Test
	public void Should_find_all_inventory_cards_and_show_them_on_one_page() {

		//Act
		Page<InventoryCard> result = sut.findAllInventoryCards(0, 20);
		
		//Assert
		assertThat(result).size().isEqualTo(20);
	}

	@Test
	public void Should_find_an_inventory_card_by_given_id() {
		
		//Act
		InventoryCard result = sut.findInventoryCardById(1);
		
		//Assert
		assertThat(result.getId()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	public void Should_set_a_unique_identifier_for_a_new_inventory_card() {
		
		//Arrange
		InventoryCard inventoryCard = sut.findInventoryCardById(1);
		String identifier = inventoryCard.getIdentifier();
		
		InventoryCard newInventoryCard = new InventoryCard();
		
		//Act
		sut.setInventoryCardIdentifier(newInventoryCard, identifier);
		
		//Assert
		assertThat(newInventoryCard.getIdentifier()).isNotEqualTo(identifier);
	}

	@Test
	public void Should_set_a_user_for_a_new_inventory_card() {
		
		//Arrange
		InventoryCard inventoryCard = new InventoryCard();

		//Act
		sut.setInventoryCardUser(inventoryCard, "jackobrien");

		//Assert
		assertThat(inventoryCard.getUser().getUsername()).isEqualTo("jackobrien");
	}

	@Test
	@Transactional
	public void Should_insert_a_new_inventory_card() {
		
		//Arrange
		InventoryCard inventoryCard = new InventoryCard();
		
		inventoryCard.setClassName(InventoryCardClass.ELECTRICAL.getName());
		sut.setInventoryCardIdentifier(inventoryCard, IdentifierGenerator.randomIdentifier());
		inventoryCard.setCreatedAt(ZonedDateTime.now());
		sut.setInventoryCardUser(inventoryCard, "jackobrien");
		inventoryCard.setProductId("GV2ME01");
		inventoryCard.setProductName("Motor circuit breaker");
		inventoryCard.setProductType("GV2ME01");
		inventoryCard.setProductImage("GV2ME01.png");
		inventoryCard.setProductManufacturer("Schneider Electric");
		inventoryCard.setProductCountry("France");
		inventoryCard.setProductLength("78.5 mm");
		inventoryCard.setProductWeight("45 mm");
		inventoryCard.setProductHeight("89 mm");
		inventoryCard.setProductWeight("0.26 kg");
		inventoryCard.setProductDescription("Rated current: 0.16 A");
		
		//Act
		sut.saveInventoryCard(inventoryCard);
		
		//Assert
		assertThat(inventoryCard.getId()).isNotEqualTo(0);
	}
	
	@Test
	@Transactional
	public void Should_delete_an_inventory_card_by_given_id() {

		//Act
		sut.deleteInventoryCardById(1);

		//Assert
		assertThrows(NoSuchElementException.class, () -> sut.findInventoryCardById(1));
	}
		
}









