package ru.blinov.control.inventory.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HomePageController.class)
public class HomePageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private DataSource dataSource;
	
	@Test
	@WithMockUser(username = "jackobrien", password = "123", roles = "ADMIN")
	public void testShowHomePage() throws Exception {
		mockMvc.perform(get("/amics"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("home-page"));
	}

}