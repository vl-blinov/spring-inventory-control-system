package ru.blinov.control.inventory.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.enums.InventoryCardClass;

public class InventoryFileHandlerTest {
	
	private InventoryCard inventoryCard() {
		
		InventoryCard inventoryCard = new InventoryCard();
		
		inventoryCard.setIdentifier("00h00000e000");
		inventoryCard.setClassName(InventoryCardClass.ELECTRICAL.getName());
		inventoryCard.setCreatedAt(ZonedDateTime.now());
		inventoryCard.setProductId("GV2ME02");
		inventoryCard.setProductName("Motor circuit breaker");
		inventoryCard.setProductType("GV2ME02");
		inventoryCard.setProductManufacturer("Schneider Electric");
		inventoryCard.setProductCountry("France");
		inventoryCard.setProductLength("78.5 mm");
		inventoryCard.setProductWidth("45 mm");
		inventoryCard.setProductHeight("89 mm");
		inventoryCard.setProductWeight("0.26 kg");
		inventoryCard.setProductDescription("Rated current: 0.16 A");
		
		return inventoryCard;
	}
	
	@BeforeEach
	public void setUp() throws IOException {

		File directory = new File("./src/main/resources/images/products");
		
		if(directory.exists()) {
			FileUtils.forceDelete(directory);
		}
	}
	
	@AfterAll
	public static void tearDown() throws IOException {

		File directory = new File("./src/main/resources/images/products");
		
		if(directory.exists()) {
			FileUtils.forceDelete(directory);
		}
	}

	@Test
	public void Should_create_a_folder_and_copy_uploaded_image_when_creating_a_new_inventory_card() throws IOException {
		
		//Arrange
		InventoryCard inventoryCard = inventoryCard();
		
		String productImageName = "GV2ME02.png";
		Path fileToCopyPath = Paths.get("./src/test/resources/images/" + productImageName);
		
		Path createdFolderPath = Paths.get("./src/main/resources/images/products/" + inventoryCard.getIdentifier());
		Path copiedFilePath = Paths.get("./src/main/resources/images/products/" + inventoryCard.getIdentifier() + "/" + productImageName);
		
		InputStream inputStream = Files.newInputStream(fileToCopyPath, StandardOpenOption.READ);
		
		MockMultipartFile multipartFile = new MockMultipartFile("fileImage", fileToCopyPath.getFileName().toString(), 
																MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
		
		String emptyImageSrc = null;

		//Act
		InventoryFileHandler.copyProductImage(inventoryCard, multipartFile, emptyImageSrc);

		//Assert
		assertThat(Files.exists(createdFolderPath, LinkOption.NOFOLLOW_LINKS)).isTrue();
		assertThat(Files.exists(copiedFilePath, LinkOption.NOFOLLOW_LINKS)).isTrue();
		assertThat(inventoryCard.getProductImage()).isEqualTo(productImageName);
	}

	@Test
	public void Should_create_a_folder_and_copy_image_when_reusing_an_existing_inventory_card() throws IOException {
		
		//Arrange
		InventoryCard inventoryCard = inventoryCard();
		
		String productImageName = "GV2ME02.png";
		
		Path createdFolderPath = Paths.get("./src/main/resources/images/products/" + inventoryCard.getIdentifier());
		Path copiedFilePath = Paths.get("./src/main/resources/images/products/" + inventoryCard.getIdentifier() + "/" + productImageName);
		
		MockMultipartFile emptyMultipartFile = new MockMultipartFile("fileImage", InputStream.nullInputStream());
		
		String imageSrc = "/src/test/resources/images/" + productImageName;

		//Act
		InventoryFileHandler.copyProductImage(inventoryCard, emptyMultipartFile, imageSrc);

		//Assert
		assertThat(Files.exists(createdFolderPath, LinkOption.NOFOLLOW_LINKS)).isTrue();
		assertThat(Files.exists(copiedFilePath, LinkOption.NOFOLLOW_LINKS)).isTrue();
		assertThat(inventoryCard.getProductImage()).isEqualTo(productImageName);
	}
	
	@Test
	public void Should_delete_a_folder_with_image_from_directory() throws IOException {
		
		//Arrange
		String folderName = "00h00000e000";
		String folderDirectory = "./src/main/resources/images/products/" + folderName;
		
		Path folderPath = Paths.get(folderDirectory);
		Path productImagePath = Paths.get(folderDirectory + "/GV2ME02.png");
		
		Files.createDirectories(folderPath);

		File source = new File("./src/test/resources/images/");
		File resource = new File(folderDirectory);

		FileUtils.copyDirectory(source, resource);
		
		//Act
		InventoryFileHandler.deleteProductImageFromDirectory(folderName);
		
		//Assert
		assertThat(Files.exists(productImagePath, LinkOption.NOFOLLOW_LINKS)).isFalse();
		assertThat(Files.exists(folderPath, LinkOption.NOFOLLOW_LINKS)).isFalse();
	}
	
	@Test
	public void Should_clean_out_current_directory_and_copy_folders_with_images_from_another_directory() throws IOException {
		
		//Arrange
		String currentDirectory = "./src/main/resources/images/products/";
		
		Files.createDirectories(Paths.get(currentDirectory + "new-folder"));

		//Act
		InventoryFileHandler.populateDirectoryWithProductImages();

		//Assert
		assertThat(new File(currentDirectory).list().length).isEqualTo(20);
	}

}