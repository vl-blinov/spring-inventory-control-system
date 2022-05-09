package ru.blinov.control.inventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

/*
 * Spring Security must be disabled, 
 * otherwise consider defining a bean of type 'javax.sql.DataSource' in configuration and running docker container with postgres.
 * @WebMvcTest attributes:
 * excludeAutoConfiguration: disable SecurityAutoConfiguration ('user' with random password on the console);
 * excludeFilters: exclude SecurityConfing (extends WebSecurityConfigurerAdapter which implements WebSecurityConfigurer) during the component scanning.
 */

@WebMvcTest(controllers = GlobalExceptionController.class, 
			excludeAutoConfiguration = SecurityAutoConfiguration.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class))
public class GlobalExceptionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InventoryCardController inventoryCardController;
	
	@Test
	public void testExceptionHandler() throws Exception {
		
		when(inventoryCardController.deleteInventoryCard(1, "folder")).thenThrow(new IOException());

		mockMvc.perform(get("/amics/save"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("error"));
	}

}