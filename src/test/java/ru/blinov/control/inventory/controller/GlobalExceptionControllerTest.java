package ru.blinov.control.inventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.enums.InventoryCardClass;

/*
 * Spring Security must be disabled, because username, password and role are stored in a database.
 * Otherwise consider defining a bean of type 'javax.sql.DataSource' in configuration and running docker container with PostgreSQL.
 * Explanation of @WebMvcTest attributes:
 * - excludeAutoConfiguration: disable SecurityAutoConfiguration ('user' with random password on the console);
 * - excludeFilters: exclude SecurityConfing (extends WebSecurityConfigurerAdapter which implements WebSecurityConfigurer) during the component scanning.
 */

@WebMvcTest(controllers = GlobalExceptionController.class, 
			excludeAutoConfiguration = SecurityAutoConfiguration.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class))
public class GlobalExceptionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InventoryCardController inventoryCardController;
	
	private InventoryCard inventoryCard() {
		
		InventoryCard inventoryCard = new InventoryCard();
		
		inventoryCard.setClassName(InventoryCardClass.ELECTRICAL.getName());
		inventoryCard.setProductId("GV2ME02");
		inventoryCard.setProductName("Motor circuit breaker");
		inventoryCard.setProductType("GV2ME02");
		inventoryCard.setProductImage("GV2ME02.png");
		inventoryCard.setProductManufacturer("Schneider Electric");
		inventoryCard.setProductCountry("France");
		inventoryCard.setProductLength("78.5 mm");
		inventoryCard.setProductWidth("45 mm");
		inventoryCard.setProductHeight("89 mm");
		inventoryCard.setProductWeight("0.26 kg");
		inventoryCard.setProductDescription("Rated current: 0.16 A");
		
		return inventoryCard;
	}
	
	private Principal principal() {
		
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "nolanrobertson";
			}
		};
		
		return principal;
	}
	
	@Test
	public void testExceptionHandler() throws Exception {
		
		InventoryCard inventoryCard = inventoryCard();
		
		Path filePath = Paths.get("./src/test/resources/images/GV2ME02.png");
		
		InputStream inputStream = Files.newInputStream(filePath, StandardOpenOption.READ);

		MockMultipartFile multipartFile = new MockMultipartFile("fileImage", filePath.getFileName().toString(), 
																MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
		
		String imageSrc = null;
		
		Principal principal = principal();

		when(inventoryCardController.saveInventoryCard(inventoryCard, multipartFile, imageSrc, principal)).thenThrow(new IOException());

		mockMvc.perform(get("/amics/save"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("error"));
	}

}