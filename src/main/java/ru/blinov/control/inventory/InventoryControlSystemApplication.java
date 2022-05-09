package ru.blinov.control.inventory;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.blinov.control.inventory.util.InventoryFileHandler;

@SpringBootApplication
public class InventoryControlSystemApplication {

	public static void main(String[] args) throws IOException {
		
		InventoryFileHandler.populateDirectoryWithProductImages();
		
		SpringApplication.run(InventoryControlSystemApplication.class, args);
	}

}
