package ru.blinov.control.inventory.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.enums.Authority;
import ru.blinov.control.inventory.enums.Department;
import ru.blinov.control.inventory.enums.Enabled;
import ru.blinov.control.inventory.enums.Position;
import ru.blinov.control.inventory.service.InventoryControlService;

@Controller
@RequestMapping("/amics/users")
public class UserController {
	
	private InventoryControlService inventoryControlService;
	
	public static final int DEFAULT_USERS_PER_PAGE = 8;
	
	@Autowired
	public UserController(InventoryControlService inventoryControlService) {
		this.inventoryControlService = inventoryControlService;
	}
	
	@GetMapping("/profile")
	public String showUserProfile(Model model, Principal principal) {
		
		model.addAttribute("user", inventoryControlService.findUserByUsername(principal.getName()));
		
		return "users/user-profile";
	}
	
	@GetMapping("/list")
	public String listUsers(@RequestParam(defaultValue="0") int page, Model model) {
		
		model.addAttribute("users", inventoryControlService.findAllUsers(page, DEFAULT_USERS_PER_PAGE));
		model.addAttribute("currentPage", page);
		
		model.addAttribute("user", new User());
		
		model.addAttribute("departments", Department.values());
		model.addAttribute("positions", Position.values());
		model.addAttribute("authorities", Authority.values());
		model.addAttribute("enabled", Enabled.values());
		
		return "users/list-users";
	}
	
	@GetMapping("/find")
	@ResponseBody
	public User findUser(@RequestParam("userId") int userId) {
		return inventoryControlService.findUserById(userId);
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("redirect") String redirect) {
		
		inventoryControlService.saveUser(user);
		
		return redirect;
	}
	
	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam("userId") int userId) {
		
		inventoryControlService.deleteUserById(userId);

		return "redirect:/amics/users/list";
	}

}