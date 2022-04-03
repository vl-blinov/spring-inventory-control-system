package ru.blinov.control.inventory.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InventoryWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		Path inventoryCardUploadDirectory = Paths.get("./src/main/resources/static/images/products");
		
		String inventoryCardUploadPath = inventoryCardUploadDirectory.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/src/main/resources/static/images/products/**")
				.addResourceLocations("file:/" + inventoryCardUploadPath + "/");
	}
	
}
