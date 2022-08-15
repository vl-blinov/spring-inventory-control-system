package ru.blinov.control.inventory.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.neovisionaries.i18n.CountryCode;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.enums.InventoryCardClass;
import ru.blinov.control.inventory.service.InventoryControlService;

@Controller
@RequestMapping("/amics")
public class InventoryCardController {
	
	private InventoryControlService inventoryControlService;
	
	public static final int DEFAULT_INVENTORY_CARDS_PER_PAGE = 4;
	
	@Autowired
	public InventoryCardController(InventoryControlService inventoryControlService) {
		this.inventoryControlService = inventoryControlService;
	}
	
	@GetMapping("/catalogue")
	public String listInventoryCards(@RequestParam(defaultValue="0") int page, Principal principal, Model model) {

		model.addAttribute("inventoryCards", inventoryControlService.findAllInventoryCards(page, DEFAULT_INVENTORY_CARDS_PER_PAGE));
		model.addAttribute("currentPage", page);
		
		model.addAttribute("inventoryCard", new InventoryCard());
		
		model.addAttribute("user", inventoryControlService.findUserByUsername(principal.getName()));
		
		model.addAttribute("countries", CountryCode.values());
		model.addAttribute("inventoryCardClasses", InventoryCardClass.values());
		
		return "inventory/catalogue";
	}
	
	@GetMapping("/view")
	@ResponseBody
	public InventoryCard viewInventoryCard(@RequestParam("inventoryCardId") int inventoryCardId) {
		return inventoryControlService.findInventoryCardById(inventoryCardId);
	}
	
	@PostMapping("/save")
	public String saveInventoryCard(@ModelAttribute("inventoryCard") InventoryCard inventoryCard,
									@RequestParam("fileImage") MultipartFile fileImage,
									@RequestParam("imageSrc") String imageSrc,
									Principal principal) throws IOException {
		
		inventoryControlService.saveInventoryCard(inventoryCard, fileImage, imageSrc, principal);
		
		return "redirect:/amics/catalogue";
	}
	
	@DeleteMapping("/delete")
	public String deleteInventoryCard(@RequestParam("inventoryCardId") int inventoryCardId, 
						 			  @RequestParam("inventoryCardImageFolder") String inventoryCardImageFolder) {
		
		inventoryControlService.deleteInventoryCardById(inventoryCardId, inventoryCardImageFolder);

		return "redirect:/amics/catalogue";
	}
	
}