package ru.blinov.control.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/amics")
public class PageController {
	
	@GetMapping()
	public String showHomePage() {
		return "home-page";
	}

}











