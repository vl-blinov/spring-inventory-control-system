package ru.blinov.control.inventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

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
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.enums.Authority;
import ru.blinov.control.inventory.enums.Department;
import ru.blinov.control.inventory.enums.Enabled;
import ru.blinov.control.inventory.enums.Position;
import ru.blinov.control.inventory.service.InventoryControlService;

/*
 * Spring Security must be disabled, because username, password and role are stored in a database.
 * Otherwise consider defining a bean of type 'javax.sql.DataSource' in configuration and running docker container with PostgreSQL.
 * Explanation of @WebMvcTest attributes:
 * - excludeAutoConfiguration: disable SecurityAutoConfiguration ('user' with random password on the console);
 * - excludeFilters: exclude SecurityConfing (extends WebSecurityConfigurerAdapter which implements WebSecurityConfigurer) during the component scanning.
 */

@WebMvcTest(controllers = UserController.class, 
			excludeAutoConfiguration = SecurityAutoConfiguration.class,
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfigurer.class))
public class UserControllerTest {
	
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
	
	@Test
	public void testShowUserProfile() throws Exception {
		
		User user = user();
		
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "nolanrobertson";
			}
		};
		
		when(inventoryControlService.findUserByUsername(principal.getName())).thenReturn(user);

		mockMvc.perform(get("/amics/users/profile").principal(principal))
			   .andExpect(status().isOk())
			   .andExpect(model().attributeExists("user"))
			   .andExpect(view().name("users/user-profile"));
	}

	@Test
	public void testListUsers() throws Exception {

		User user = user();
		Page<User> users = new PageImpl<>(Lists.newArrayList(user));
		
		when(inventoryControlService.findAllUsers(0, 8)).thenReturn(users);	

		mockMvc.perform(get("/amics/users/list").param("page", "0"))
			   .andExpect(status().isOk())
			   .andExpect(model().attributeExists("users"))
			   .andExpect(model().attributeExists("currentPage"))
			   .andExpect(model().attributeExists("user"))
			   .andExpect(model().attributeExists("departments"))
			   .andExpect(model().attributeExists("positions"))
			   .andExpect(model().attributeExists("authorities"))
			   .andExpect(model().attributeExists("enabled"))
			   .andExpect(view().name("users/list-users"));
	}

	@Test
	public void testFindUser() throws Exception {
		
		User user = user();
		
		when(inventoryControlService.findUserById(1)).thenReturn(user);
		
		mockMvc.perform(get("/amics/users/find").param("userId", "1"))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id").value("1"))
			   .andExpect(jsonPath("$.username").value("nolanrobertson"))
			   .andExpect(jsonPath("$.enabled").value(Enabled.YES.getValue()))
			   .andExpect(jsonPath("$.authority").value(Authority.ROLE_USER.getName()))
			   .andExpect(jsonPath("$.firstName").value("Nolan"))
			   .andExpect(jsonPath("$.lastName").value("Robertson"))
			   .andExpect(jsonPath("$.department").value(Department.PLANT_ENGINEERING.getName()))
			   .andExpect(jsonPath("$.position").value(Position.ENGINEER.getName()))
			   .andExpect(jsonPath("$.phone").value("+353 1 325 0707"))
			   .andExpect(jsonPath("$.email").value("nolanrobertson@amics.com"));
	}

	@Test
	public void testSaveUser() throws Exception {
		
		String redirect = "redirect:/amics/users/list";
		
		mockMvc.perform(post("/amics/users/save").flashAttr("user", user()).param("redirect", redirect))
		   .andExpect(status().is3xxRedirection())
		   .andExpect(view().name(redirect));
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		mockMvc.perform(get("/amics/users/delete").param("userId", "1"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/amics/users/list"));	
	}

}