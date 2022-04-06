package ru.blinov.control.inventory.controller;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		model.addAttribute("countries", CountryCode.values());
		
		return "/inventory/catalogue";
	}
	
	@GetMapping("/view")
	@ResponseBody
	public InventoryCard viewInventoryCard(@RequestParam("inventoryCardId") int id, Model model) {
		
		InventoryCard inventoryCard = inventoryCardService.findById(id);
		
		return inventoryCard;
	}
	
	@PostMapping("/save")
	public String saveInventoryCard(@ModelAttribute("inventoryCard") InventoryCard inventoryCard,
									@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
//		//?????
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
		
		String identifier = inventoryCard.generateIdentifier();
		
		while(true) {
			if(inventoryCardService.existsByIdentifier(identifier)) {
				identifier = inventoryCard.generateIdentifier();;
			}
			else {
				break;
			}
		}
		
		inventoryCard.setIdentifier(identifier);
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		inventoryCard.setProductImage(fileName);
		
		inventoryCardService.save(inventoryCard);
		
		Path uploadPath = Paths.get("./src/main/resources/static/images/products/" + inventoryCard.getIdentifier());
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		InputStream inputStream = multipartFile.getInputStream();
		Path filePath = uploadPath.resolve(fileName);	
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);	

		return "redirect:/amics/catalogue";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("inventoryCardId") int id, 
						 @RequestParam("inventoryCardIdentifier") String identifier,
						 @RequestParam("inventoryCardProductImage") String productImage) throws IOException {
		
		inventoryCardService.deleteById(id);
		
		Path deleteFile = Paths.get("./src/main/resources/static/images/products/" + identifier + "/" + productImage);
		Path deleteFileFolder = Paths.get("./src/main/resources/static/images/products/" + identifier);

		Files.delete(deleteFile);
		Files.delete(deleteFileFolder);

		return "redirect:/amics/catalogue";
	}
	
}
















