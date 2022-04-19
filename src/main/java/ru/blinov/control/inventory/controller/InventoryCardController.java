package ru.blinov.control.inventory.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
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
	public String listInventoryCards(@RequestParam(defaultValue="0") int page, Model model) {
		
		Page<InventoryCard> inventoryCards = inventoryControlService.findAllInventoryCards(page, 4);
		InventoryCard inventoryCard = new InventoryCard();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = inventoryControlService.findUserByUsername(username);
		
		model.addAttribute("inventoryCards", inventoryCards);
		model.addAttribute("currentPage", page);
		model.addAttribute("inventoryCard", inventoryCard);
		model.addAttribute("countries", CountryCode.values());
		model.addAttribute("inventoryCardClasses", InventoryCardClass.values());
		model.addAttribute("user", user);
		
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
									@RequestParam("imageSrc") String imageSrc) throws IOException {
		
		String fileName = null;
		String uploadDirectory;
		Path sourcePath = null;
		Path uploadPath;
		String sourceDirectory = null;
		
		//Set User
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		User user = inventoryControlService.findUserByUsername(username);
		
		inventoryCard.setUser(user);
		
		//Get image name
		if(multipartFile.isEmpty()) {
			Pattern pattern = Pattern.compile("([^\\s/]+(\\.(?i)(png|jpeg|jpg))$)");
			Matcher matcher = pattern.matcher(imageSrc);
			matcher.find();
			fileName = matcher.group();
			
			sourceDirectory = "./src/main/resources/static/images/products/" + inventoryCard.getIdentifier() + "/" + fileName;
			sourcePath = Paths.get(sourceDirectory);
			
		}
		
		String identifier = inventoryCard.generateIdentifier();
		while(true) {
			if(inventoryControlService.existsInventoryCardByIdentifier(identifier)) {
				identifier = inventoryCard.generateIdentifier();
			}
			else {
				break;
			}
		}
		inventoryCard.setIdentifier(identifier);
		
		if(!multipartFile.isEmpty()) {
			
			fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			inventoryCard.setProductImage(fileName);

			uploadDirectory = "./src/main/resources/static/images/products/" + inventoryCard.getIdentifier();
			
			uploadPath = Paths.get(uploadDirectory);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			InputStream inputStream = multipartFile.getInputStream();
			Path filePath = uploadPath.resolve(fileName);	
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);		
		}
		else {
			
			inventoryCard.setProductImage(fileName);
			
			uploadDirectory = "./src/main/resources/static/images/products/" + identifier;
			
			uploadPath = Paths.get(uploadDirectory);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			File fileToCopy = new File(sourceDirectory);
			File copiedFile = new File(uploadDirectory + "/" + fileName);
			
			Files.copy(fileToCopy.toPath(), copiedFile.toPath());
		}

		inventoryControlService.saveInventoryCard(inventoryCard);
		
		return "redirect:/amics/catalogue";
	}
	
	@GetMapping("/delete")
	public String deleteInventoryCard(@RequestParam("inventoryCardId") int id, 
						 			  @RequestParam("inventoryCardIdentifier") String identifier,
						 			  @RequestParam("inventoryCardProductImage") String productImage) {
		
		inventoryControlService.deleteInventoryCardById(id);
		
		Path deleteFile = Paths.get("./src/main/resources/static/images/products/" + identifier + "/" + productImage);
		Path deleteFileFolder = Paths.get("./src/main/resources/static/images/products/" + identifier);

		try {
			Files.delete(deleteFile);
			Files.delete(deleteFileFolder);
		} catch (IOException e) {}

		return "redirect:/amics/catalogue";
	}
	
}
















