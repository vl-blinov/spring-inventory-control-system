package ru.blinov.control.inventory.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import ru.blinov.control.inventory.util.IdentifierGenerator;

@Controller
@RequestMapping("/amics")
public class InventoryCardController {
	
	@Autowired
	private InventoryControlService inventoryControlService;
	
	@GetMapping("/catalogue")
	public String listInventoryCards(@RequestParam(defaultValue="0") int page, Model model, Principal principal) {

		model.addAttribute("inventoryCards", inventoryControlService.findAllInventoryCards(page, 4));
		model.addAttribute("currentPage", page);
		
		model.addAttribute("inventoryCard", new InventoryCard());
		
		model.addAttribute("user", inventoryControlService.findUserByUsername(principal.getName()));
		
		model.addAttribute("countries", CountryCode.values());
		model.addAttribute("inventoryCardClasses", InventoryCardClass.values());
		
		return "inventory/catalogue";
	}
	
	@GetMapping("/view")
	@ResponseBody
	public InventoryCard viewInventoryCard(@RequestParam("inventoryCardId") int id) {
		return inventoryControlService.findInventoryCardById(id);
	}
	
	@PostMapping("/save")
	public String saveInventoryCard(@ModelAttribute("inventoryCard") InventoryCard inventoryCard,
									@RequestParam("fileImage") MultipartFile multipartFile,
									@RequestParam("imageSrc") String imageSrc,
									Principal principal) throws IOException {

		inventoryControlService.setInventoryCardIdentifier(inventoryCard, IdentifierGenerator.randomIdentifier());
		
		inventoryControlService.setInventoryCardUser(inventoryCard, principal.getName());

		inventoryControlService.copyProductImage(inventoryCard, multipartFile, imageSrc);
		
		inventoryControlService.saveInventoryCard(inventoryCard);
		
		return "redirect:/amics/catalogue";
	}
	
	@GetMapping("/delete")
	public String deleteInventoryCard(@RequestParam("inventoryCardId") int id, 
						 			  @RequestParam("inventoryCardIdentifier") String identifier,
						 			  @RequestParam("inventoryCardProductImage") String productImage) {
		
		inventoryControlService.deleteInventoryCardById(id);
		
		inventoryControlService.deleteImageFromDirectory(productImage, identifier);

		return "redirect:/amics/catalogue";
	}
	
}