package ru.blinov.control.inventory.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest(controllers = HomePageController.class, 
			excludeAutoConfiguration = SecurityAutoConfiguration.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class))
public class HomePageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testShowHomePage() throws Exception {
		mockMvc.perform(get("/amics"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("home-page"));
	}

}