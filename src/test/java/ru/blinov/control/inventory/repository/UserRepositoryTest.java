package ru.blinov.control.inventory.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

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

import ru.blinov.control.inventory.entity.User;

@DataJpaTest
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository sut;
	
	@Container
	private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");
														  
	@DynamicPropertySource
	public static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}
	
	@Test
	public void Should_find_a_user_with_the_given_username() {
		
		//Arrange
		String username = "jackobrien";
		
		//Act
		Optional<User> result = sut.findByUsername(username);
				
		//Assert
		assertThat(result.get().getUsername()).isEqualTo(username);
	}
	
	@Test
	public void Should_not_find_a_user_with_the_given_username() {
		
		//Arrange
		String username = "jackobriennnn";
		
		//Act
		Optional<User> result = sut.findByUsername(username);
				
		//Assert
		assertThat(result).isEmpty();
	}
}