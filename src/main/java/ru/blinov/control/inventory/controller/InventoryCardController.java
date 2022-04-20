package ru.blinov.control.inventory.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neovisionaries.i18n.CountryCode;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.enums.InventoryCardClass;
import ru.blinov.control.inventory.service.InventoryControlService;

@Controller
@RequestMapping("/amics")
public class InventoryCardController {
	
	@Autowired
	private InventoryControlService inventoryControlService;
	
	@GetMapping("/catalogue")
	public String listInventoryCards(@RequestParam(defaultValue="0") int page, Model model, Principal principal) {
		
		Page<InventoryCard> inventoryCards = inventoryControlService.findAllInventoryCards(page, 4);
		model.addAttribute("inventoryCards", inventoryCards);
		model.addAttribute("currentPage", page);
		
		model.addAttribute("inventoryCard", new InventoryCard());
		
		User user = inventoryControlService.findUserByUsername(principal.getName());
		model.addAttribute("user", user);

		model.addAttribute("countries", CountryCode.values());
		model.addAttribute("inventoryCardClasses", InventoryCardClass.values());
		
		return "/inventory/catalogue";
	}
	
	@GetMapping("/view")
	@ResponseBody
	public InventoryCard viewInventoryCard(@RequestParam("inventoryCardId") int id) {

		InventoryCard inventoryCard = inventoryControlService.findInventoryCardById(id);

		return inventoryCard;
	}
	
	@PostMapping("/save")
	public String saveInventoryCard(@ModelAttribute("inventoryCard") InventoryCard inventoryCard,
									@RequestParam("fileImage") MultipartFile multipartFile,
									@RequestParam("imageSrc") String imageSrc,
									Principal principal) throws IOException {

		inventoryControlService.setInventoryCardUser(inventoryCard, principal.getName());
		
		inventoryControlService.setInventoryCardIdentifier(inventoryCard);
		
		String fileName;
		String identifier = inventoryCard.getIdentifier();
		String uploadDirectory = "./src/main/resources/static/images/products/" + identifier;
		Path uploadPath = Paths.get(uploadDirectory);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		if(!multipartFile.isEmpty()) {
			
			fileName = multipartFile.getOriginalFilename();
			Path filePath = uploadPath.resolve(fileName);
			
			InputStream inputStream = multipartFile.getInputStream();

			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);		
		}
		else {
			
			File fileToCopy = new File("." + imageSrc);
			fileName = fileToCopy.getName();
			
			File copiedFile = new File(uploadDirectory + "/" + fileName);
			
			Files.copy(fileToCopy.toPath(), copiedFile.toPath());
		}
		
		inventoryCard.setProductImage(fileName);
		
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
















