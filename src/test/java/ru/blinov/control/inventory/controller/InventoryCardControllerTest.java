package ru.blinov.control.inventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.time.ZonedDateTime;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.enums.Authority;
import ru.blinov.control.inventory.enums.Department;
import ru.blinov.control.inventory.enums.Enabled;
import ru.blinov.control.inventory.enums.InventoryCardClass;
import ru.blinov.control.inventory.enums.Position;
import ru.blinov.control.inventory.service.InventoryControlService;
import ru.blinov.control.inventory.util.IdentifierGenerator;

/*
 * Spring Security must be disabled, 
 * otherwise consider defining a bean of type 'javax.sql.DataSource' in configuration and running docker container with postgres.
 * @WebMvcTest attributes:
 * excludeAutoConfiguration: disable SecurityAutoConfiguration ('user' with random password on the console);
 * excludeFilters: exclude SecurityConfing (extends WebSecurityConfigurerAdapter which implements WebSecurityConfigurer) during the component scanning.
 */

@WebMvcTest(controllers = InventoryCardController.class, 
			excludeAutoConfiguration = SecurityAutoConfiguration.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class))
public class InventoryCardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InventoryControlService inventoryControlService;
	
	private User user() {
		
		User user = new User();
		
		user.setId(1);
		user.setUsername("nolanrobertson");
		user.setEnabled(Enabled.YES.getValue());
		user.setAuthority(Authority.ROLE_USER.getName());
		user.setFirstName("Nolan");
		user.setLastName("Robertson");
		user.setDepartment(Department.PLANT_ENGINEERING.getName());
		user.setPosition(Position.ENGINEER.getName());
		user.setPhone("+353 1 325 0707");
		user.setEmail("nolanrobertson@amics.com");
		
		return user;
	}
	
	private InventoryCard inventoryCard() {
		
		InventoryCard inventoryCard = new InventoryCard();
		
		inventoryCard.setId(1);
		inventoryCard.setClassName(InventoryCardClass.ELECTRICAL.getName());
		inventoryCard.setIdentifier(IdentifierGenerator.randomIdentifier());
		inventoryCard.setCreatedAt(ZonedDateTime.now());
		inventoryCard.setUser(user());
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
	
	private Principal principle() {
		
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "nolanrobertson";
			}
		};
		
		return principal;
	}
	
	@Test
	public void testListInventoryCards() throws Exception {
		
		Principal principal = principle();
		
		Page<InventoryCard> inventoryCards = new PageImpl<>(Lists.newArrayList(inventoryCard()));
		
		when(inventoryControlService.findAllInventoryCards(0, 4)).thenReturn(inventoryCards);
		when(inventoryControlService.findUserByUsername(principal.getName())).thenReturn(user());
		
		mockMvc.perform(get("/amics/catalogue").param("page", "0").principal(principal))
			   .andExpect(status().isOk())
			   .andExpect(model().attributeExists("inventoryCards"))
			   .andExpect(model().attributeExists("currentPage"))
			   .andExpect(model().attributeExists("inventoryCard"))
			   .andExpect(model().attributeExists("user"))
			   .andExpect(model().attributeExists("countries"))
			   .andExpect(model().attributeExists("inventoryCardClasses"))
			   .andExpect(view().name("inventory/catalogue"));
	}
	
	@Test
	public void testViewInventoryCard() throws Exception {
		
		when(inventoryControlService.findInventoryCardById(1)).thenReturn(inventoryCard());
		
		mockMvc.perform(get("/amics/view").param("inventoryCardId", "1"))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id").value("1"))
			   .andExpect(jsonPath("$.className").value(InventoryCardClass.ELECTRICAL.getName()))
			   .andExpect(jsonPath("$.identifier").isNotEmpty())
			   .andExpect(jsonPath("$.createdAt").isNotEmpty())
			   .andExpect(jsonPath("$.user").isNotEmpty())
			   .andExpect(jsonPath("$.productId").value("GV2ME02"))
			   .andExpect(jsonPath("$.productName").value("Motor circuit breaker"))
			   .andExpect(jsonPath("$.productType").value("GV2ME02"))
			   .andExpect(jsonPath("$.productImage").value("GV2ME02.png"))
			   .andExpect(jsonPath("$.productManufacturer").value("Schneider Electric"))
			   .andExpect(jsonPath("$.productCountry").value("France"))
			   .andExpect(jsonPath("$.productLength").value("78.5 mm"))
			   .andExpect(jsonPath("$.productWidth").value("45 mm"))
			   .andExpect(jsonPath("$.productHeight").value("89 mm"))
			   .andExpect(jsonPath("$.productWeight").value("0.26 kg"))
			   .andExpect(jsonPath("$.productDescription").value("Rated current: 0.16 A"));
	}
	
	@Test
	public void testSaveInventoryCard() throws Exception {
		
		Path filePath = Paths.get("./src/test/resources/images/GV2ME02.png");
		
		InputStream inputStream = Files.newInputStream(filePath, StandardOpenOption.READ);

		MockMultipartFile multipartFile = new MockMultipartFile("fileImage", filePath.getFileName().toString(), 
																MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
		
		InventoryCard inventoryCard = inventoryCard();
		
		Principal principal = principle();
		
		mockMvc.perform(MockMvcRequestBuilders.multipart("/amics/save").file(multipartFile)
						.flashAttr("inventoryCard", inventoryCard)
						.param("imageSrc", "")
						.principal(principal))
						.andExpect(status().is3xxRedirection())
						.andExpect(view().name("redirect:/amics/catalogue"));
	}
	
	@Test
	public void testDeleteInventoryCard() throws Exception {
		
		mockMvc.perform(get("/amics/delete")
			   .param("inventoryCardId", "1")
			   .param("inventoryCardIdentifier", "12h09357e724")
			   .param("inventoryCardProductImage", "GV2ME02.png"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/amics/catalogue"));
	}

}