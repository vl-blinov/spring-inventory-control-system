package ru.blinov.control.inventory.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;

public class InventoryFileHandler {
	
	public static void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc) throws IOException {
		
		String fileName = null;
		InputStream inputStream = null;

		Path uploadPath = Paths.get("./src/main/resources/images/products/" + inventoryCard.getIdentifier());
		
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
		
		inventoryCard.setProductImage(fileName);
	}
	
	public static void deleteProductImageFromDirectory(String folderName) {
			FileUtils.deleteQuietly(new File("./src/main/resources/images/products/" + folderName));
	}
	
	public static void populateDirectoryWithProductImages() throws IOException {
		
		File cleanOut = new File("./src/main/resources/images/products");
		File source = new File("./src/main/resources/static/images-source/");
		File resource = new File("./src/main/resources/images");
		
		if(cleanOut.exists()) {
			FileUtils.forceDelete(cleanOut);
		}
		
		FileUtils.copyDirectory(source, resource);
	}

}