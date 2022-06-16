package ru.blinov.control.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
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

@SpringBootTest
@Testcontainers
public class InventoryControlServiceTest {
	
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
	
	@AfterAll
	public static void tearDown() throws IOException {

		File directory = new File("./src/main/resources/images/products");
		
		if(directory.exists()) {
			FileUtils.forceDelete(directory);
		}
	}
	
	//User
	
	@Test
	public void Should_find_all_users_and_show_them_on_one_page() {

		//Arrange
		int currentPageNumber = 0;
		int pageSize = 30;
		int numberOfAllUsers = 24;
		
		//Act
		Page<User> result = sut.findAllUsers(currentPageNumber, pageSize);
		
		//Assert
		assertThat(result).size().isEqualTo(numberOfAllUsers);
	}

	@Test
	public void Should_find_a_user_by_given_id() {
		
		//Arrange
		int userId = 1;
		
		//Act
		User result = sut.findUserById(userId);
		
		//Assert
		assertThat(result.getId()).isEqualTo(userId);
	}

	@Test
	public void Should_find_a_user_by_given_username() {
		
		//Arrange
		String username = "jackobrien";
		
		//Act
		User result = sut.findUserByUsername(username);
		
		//Assert
		assertThat(result.getUsername()).isEqualTo("jackobrien");
	}

	@Test
	@Transactional
	public void Should_insert_a_new_user() {
		
		//Arrange
		User user = new User();
		
		user.setUsername("nolanrobertson");
		user.setEnabled(Enabled.YES.getValue());
		user.setAuthority(Authority.ROLE_USER.getName());
		user.setFirstName("Nolan");
		user.setLastName("Roberston");
		user.setDepartment(Department.PLANT_ENGINEERING.getName());
		user.setPosition(Position.ENGINEER.getName());
		user.setPhone("+353 1 325 0707");
		user.setEmail("nolanrobertson@amics.com");
		
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
	public void Should_delete_a_user_by_given_id() {
		
		//Arrange
		int userId = 4;
		InventoryCard inventoryCard = sut.findInventoryCardById(1);
		
		//Act
		sut.deleteUserById(userId);

		//Assert
		assertThrows(NoSuchElementException.class, () -> sut.findUserById(userId));
		assertThat(inventoryCard.getUser()).isNull();
	}
	
	//Inventory card

	@Test
	public void Should_find_all_inventory_cards_and_show_them_on_one_page() {
		
		//Arrange
		int currentPageNumber = 0;
		int pageSize = 30;
		int numberOfAllInventoryCards = 20;
		
		//Act
		Page<InventoryCard> result = sut.findAllInventoryCards(currentPageNumber, pageSize);
		
		//Assert
		assertThat(result).size().isEqualTo(numberOfAllInventoryCards);
	}

	@Test
	public void Should_find_an_inventory_card_by_given_id() {
		
		//Arrange
		int inventoryCardId = 1;
		
		//Act
		InventoryCard result = sut.findInventoryCardById(inventoryCardId);
		
		//Assert
		assertThat(result.getId()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void Should_insert_a_new_inventory_card() throws IOException {
		
		//Arrange
		InventoryCard inventoryCard = new InventoryCard();
		
		inventoryCard.setClassName(InventoryCardClass.ELECTRICAL.getName());
		inventoryCard.setProductId("GV2ME02");
		inventoryCard.setProductName("Motor circuit breaker");
		inventoryCard.setProductType("GV2ME02");
		inventoryCard.setProductManufacturer("Schneider Electric");
		inventoryCard.setProductCountry("France");
		inventoryCard.setProductLength("78.5 mm");
		inventoryCard.setProductWidth("45 mm");
		inventoryCard.setProductHeight("89 mm");
		inventoryCard.setProductWeight("0.26 kg");
		inventoryCard.setProductDescription("Rated current: 0.16 A");
		
		Path filePath = Paths.get("./src/test/resources/images/GV2ME02.png");
		
		InputStream inputStream = Files.newInputStream(filePath, StandardOpenOption.READ);
		
		MockMultipartFile multipartFile = new MockMultipartFile("fileImage", filePath.getFileName().toString(), 
																MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
		
		String imageSrc = Strings.EMPTY;
		
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "jackobrien";
			}
		};
		
		//Act
		sut.saveInventoryCard(inventoryCard, multipartFile, imageSrc, principal);
		
		//Assert
		assertThat(inventoryCard.getId()).isNotEqualTo(0);
		assertThat(inventoryCard.getUser().getUsername()).isEqualTo("jackobrien");
	}
	
	@Test
	@Transactional
	public void Should_delete_an_inventory_card_by_given_id() throws IOException {
		
		//Arrange
		int inventoryCardId = 1;
		String productImageFolder = sut.findInventoryCardById(inventoryCardId).getIdentifier();

		//Act
		sut.deleteInventoryCardById(inventoryCardId, productImageFolder);

		//Assert
		assertThrows(NoSuchElementException.class, () -> sut.findInventoryCardById(inventoryCardId));
	}
		
}