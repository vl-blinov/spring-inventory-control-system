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
		
		if(multipartFile.isEmpty() && imageSrc.isEmpty()) {
			return;
		}

		Path uploadPath = Paths.get("./src/main/resources/images/products/" + inventoryCard.getIdentifier());	
		Files.createDirectories(uploadPath);
		
		String fileName = getImageFileName(multipartFile, imageSrc);
		InputStream inputStream = getImageFileInputStream(multipartFile, imageSrc);
		
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		
		inputStream.close();
		
		inventoryCard.setProductImage(fileName);
	}
	
	private static String getImageFileName(MultipartFile multipartFile, String imageSrc) {
		
		String fileName;
		
		if(!multipartFile.isEmpty()) {
			fileName = multipartFile.getOriginalFilename();
		}
		else {	
			File fileToCopy = new File("." + imageSrc);
			fileName = fileToCopy.getName();
		}

		return fileName;
	}
	
	private static InputStream getImageFileInputStream(MultipartFile multipartFile, String imageSrc) throws IOException {
		
		InputStream inputStream;
		
		if(!multipartFile.isEmpty()) {
			inputStream = multipartFile.getInputStream();
		}
		else {
			Path sourcePath = Paths.get(new File("." + imageSrc).getAbsolutePath());
			inputStream = Files.newInputStream(sourcePath, StandardOpenOption.READ);
		}
		
		return inputStream;
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