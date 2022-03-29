package ru.blinov.control.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.service.InventoryCardService;

@Controller
@RequestMapping("/amics")
public class InventoryCardController {
	
	@Autowired
	private InventoryCardService inventoryCardService;
	
	@GetMapping("/catalogue")
	public String listInventoryCards(Model model) {
		
		List<InventoryCard> inventoryCards = inventoryCardService.findAll();
		
		model.addAttribute("inventoryCards", inventoryCards);
		
		return "/inventory/catalogue";
	}
	
}
















