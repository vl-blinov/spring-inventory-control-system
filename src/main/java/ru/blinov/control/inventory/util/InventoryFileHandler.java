package ru.blinov.control.inventory.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;

public class InventoryFileHandler {
	
	public static void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc) {
		
		String fileName = null;
		
		String identifier = inventoryCard.getIdentifier();
		
		String uploadDirectory = "./src/main/resources/static/images/products/" + identifier;
		Path uploadPath = Paths.get(uploadDirectory);
		
		if(!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(!multipartFile.isEmpty()) {
			
			fileName = multipartFile.getOriginalFilename();
			Path filePath = uploadPath.resolve(fileName);

			try {
				InputStream inputStream = multipartFile.getInputStream();
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else {
			
			File fileToCopy = new File("." + imageSrc);
			fileName = fileToCopy.getName();
			
			File copiedFile = new File(uploadDirectory + "/" + fileName);
			
			try {
				Files.copy(fileToCopy.toPath(), copiedFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		inventoryCard.setProductImage(fileName);
	}

}
