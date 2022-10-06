package ru.blinov.control.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.repository.InventoryCardRepository;
import ru.blinov.control.inventory.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class InventoryControlServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private InventoryCardRepository inventoryCardRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private InventoryControlService sut;
	
	@AfterAll
	public static void tearDown() throws IOException {

		File directory = new File("./src/main/resources/images/products");
		
		if(directory.exists()) {
			FileUtils.forceDelete(directory);
		}
	}
	
	@Test
	public void Should_get_a_page_of_users() {

		//Arrange
		Page<User> page = new PageImpl<>(Lists.newArrayList(new User(), new User(), new User()));
		int pageIndex = 0;
		int pageSize = page.getSize();
		
		when(userRepository.findAll(PageRequest.of(pageIndex, pageSize))).thenReturn(page);
		
		//Act
		Page<User> result = sut.findAllUsers(pageIndex, pageSize);
		
		//Assert
		assertThat(result).hasSize(pageSize);
	}
	
	@Test
	public void Should_get_a_user_by_the_given_id() {
		
		//Arrange
		User user = new User();
		Integer userId = 1;
		user.setId(userId);
		
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		
		//Act
		User result = sut.findUserById(userId);
		
		//Assert
		assertThat(result.getId()).isEqualTo(userId);
	}
	
	@Test
	public void When_trying_to_get_a_user_by_the_given_id_should_throw_NoSuchElementException() {
		
		//Arrange
		Integer userId = 999;
		String message = "User with id '" + userId + "' does not exist.";
		
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		
		//Assert
		assertThrows(message, NoSuchElementException.class, () -> sut.findUserById(userId));
	}

	@Test
	public void Should_get_a_user_by_the_given_username() {
		
		//Arrange
		User user = new User();
		String username = "jackobrien";
		user.setUsername(username);
		
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
		
		//Act
		User result = sut.findUserByUsername(username);
		
		//Assert
		assertThat(result.getUsername()).isEqualTo(username);
	}
	
	@Test
	public void When_trying_to_get_a_user_by_the_given_username_should_throw_NoSuchElementException() {
		
		//Arrange
		User user = new User();
		String username = "jackobrien";
		user.setUsername(username);
		
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
		
		//Act
		User result = sut.findUserByUsername(username);
		
		//Assert
		assertThat(result.getUsername()).isEqualTo(username);
	}
	
	@Test
	public void Should_save_the_given_user() {
		
		//Arrange
		User user = new User();
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		String encodedPassword = "$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq";
		when(passwordEncoder.encode("123")).thenReturn(encodedPassword);
		
		//Act
		sut.saveUser(user);
		
		//Assert
		verify(userRepository).save(captor.capture());
		assertThat(user).isEqualTo(captor.getValue());
		assertThat(user.getPassword()).isEqualTo(encodedPassword);
	}

	@Test
	public void Should_delete_a_user_by_the_given_id() {
		
		//Arrange
		User user = new User();
		Integer userId = 1;
		user.setId(userId);
		
		InventoryCard card1 = new InventoryCard();
		card1.setUser(user);
		InventoryCard card2 = new InventoryCard();
		card2.setUser(user);
		
		List<InventoryCard> inventoryCards = Lists.newArrayList(card1, card2);
		
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(inventoryCardRepository.findAllByUser(user)).thenReturn(inventoryCards);
		
		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		
		//Act
		sut.deleteUserById(userId);

		//Assert
		assertThat(inventoryCards.stream().allMatch(card -> card.getUser() == null));
		verify(userRepository).deleteById(captor.capture());
		assertThat(userId).isEqualTo(captor.getValue());
	}
	
	@Test
	public void Should_get_a_page_of_inventory_cards() {
		
		//Arrange
		Page<InventoryCard> page = new PageImpl<>(Lists.newArrayList(new InventoryCard(), new InventoryCard(), new InventoryCard()));
		int pageIndex = 0;
		int pageSize = page.getSize();
		
		when(inventoryCardRepository.findAll(PageRequest.of(pageIndex, pageSize))).thenReturn(page);
		
		//Act
		Page<InventoryCard> result = sut.findAllInventoryCards(pageIndex, pageSize);
		
		//Assert
		assertThat(result).hasSize(pageSize);
	}
	
	@Test
	public void Should_get_an_inventory_card_by_the_given_id() {
		
		//Arrange
		InventoryCard inventoryCard = new InventoryCard();
		Integer inventoryCardId = 1;
		inventoryCard.setId(inventoryCardId);
		
		when(inventoryCardRepository.findById(inventoryCardId)).thenReturn(Optional.of(inventoryCard));
		
		//Act
		InventoryCard result = sut.findInventoryCardById(inventoryCardId);
		
		//Assert
		assertThat(result.getId()).isEqualTo(inventoryCardId);
	}
	
	@Test
	public void Should_save_the_given_inventory_card() throws IOException {
		
		//Arrange
		InventoryCard inventoryCard = new InventoryCard();
		
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
		
		User user = new User();
		String username = "jackobrien";
		user.setUsername(username);
		
		when(inventoryCardRepository.existsByIdentifier(any(String.class))).thenReturn(false);
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
		
		//Act
		sut.saveInventoryCard(inventoryCard, multipartFile, imageSrc, principal);
		
		//Assert
		assertThat(inventoryCard.getIdentifier()).isNotNull();
		assertThat(inventoryCard.getUser().getUsername()).isEqualTo(username);
	}
	
	@Test
	public void Should_delete_an_inventory_card_by_the_given_id() throws IOException {
		
		//Arrange
		InventoryCard inventoryCard = new InventoryCard();
		Integer inventoryCardId = 1;
		String folderName = "";	
		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		
		when(inventoryCardRepository.findById(inventoryCardId)).thenReturn(Optional.of(inventoryCard));
		
		//Act
		sut.deleteInventoryCardById(inventoryCardId, folderName);

		//Assert
		verify(inventoryCardRepository).deleteById(captor.capture());
		assertThat(inventoryCardId).isEqualTo(captor.getValue());
	}
		
}