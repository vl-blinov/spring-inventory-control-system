package ru.blinov.control.inventory.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.blinov.control.inventory.entity.User;

@SpringBootTest
@Testcontainers
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
	public void Should_find_a_user_with_given_username() {
		
		//Arrange
		String username = sut.findById(1).get().getUsername();
		
		//Act
		User result = sut.findByUsername(username);
				
		//Assert
		assertThat(result).isNotNull();
		assertThat(result.getUsername()).isEqualTo(username);
	}
	
}