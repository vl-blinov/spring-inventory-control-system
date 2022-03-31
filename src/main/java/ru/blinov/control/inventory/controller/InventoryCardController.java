package ru.blinov.control.inventory.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.blinov.control.inventory.dao.projection.InventoryCardIdentifier;
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
		
		InventoryCard inventoryCard = new InventoryCard();
		
		model.addAttribute("inventoryCards", inventoryCards);
		
		model.addAttribute("inventoryCard", inventoryCard);
		
		return "/inventory/catalogue";
	}
	
	@PostMapping("/save")
	public String saveInventoryCard(@ModelAttribute("inventoryCard") InventoryCard inventoryCard) {
		
//		//?????
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
		
		List<InventoryCardIdentifier> identifiers = inventoryCardService.findAllIdentifiers();
		
		String identifier = generateIdentifier();

		for(int i = 0; i < identifiers.size(); i++) {
			if(!identifiers.get(i).getIdentifier().equals(identifier)) {
				continue;
			}
			else {
				identifier = generateIdentifier();
				i = -1;
			}
		}
		
		inventoryCard.setIdentifier(identifier);
		
		inventoryCardService.save(inventoryCard);
		
		return "redirect:/amics/catalogue";
	}
	
	public static String generateIdentifier() {
		
		String part1 = RandomStringUtils.randomNumeric(2);
		String part2 = RandomStringUtils.randomNumeric(5);
		String part3 = RandomStringUtils.randomNumeric(3);
		
		String identifier = part1.concat("h").concat(part2).concat("e").concat(part3);
		
		return identifier;
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("inventoryCardId") int id) {
		
		inventoryCardService.deleteById(id);
		
		return "redirect:/amics/catalogue";
	}
	
}
















