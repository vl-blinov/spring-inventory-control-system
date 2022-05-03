package ru.blinov.control.inventory.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;

public class InventoryFileHandler {
	
	public static void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc) {
		
		String fileName = null;
		InputStream inputStream = null;

		String uploadDirectory = "./src/main/resources/static/images/products/" + inventoryCard.getIdentifier();
		Path uploadPath = Paths.get(uploadDirectory);
		
		try {
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			if(!multipartFile.isEmpty()) {
				fileName = multipartFile.getOriginalFilename();
				inputStream = multipartFile.getInputStream();
			}
			else {	
				File fileToCopy = new File("." + imageSrc);
				fileName = fileToCopy.getName();
				Path sourcePath = Paths.get(fileToCopy.getAbsolutePath());
				inputStream = Files.newInputStream(sourcePath, StandardOpenOption.READ);
			}
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
			inputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inventoryCard.setProductImage(fileName);
	}

}