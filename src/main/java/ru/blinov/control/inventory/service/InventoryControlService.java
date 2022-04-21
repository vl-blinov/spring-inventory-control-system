package ru.blinov.control.inventory.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

public interface InventoryControlService {
	
	//Inventory card
	
	public Page<InventoryCard> findAllInventoryCards(int page, int size);
	
	public InventoryCard findInventoryCardById(int id);
	
	public void saveInventoryCard(InventoryCard inventoryCard);
	
	public void deleteInventoryCardById(int id);
	
	public void deleteImageFromDirectory(String fileName, String folderName);
	
	public void setInventoryCardIdentifier(InventoryCard inventoryCard);
	
	public void setInventoryCardUser(InventoryCard inventoryCard, String username);
	
	public void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc);
	
	//User
	
	public Page<User> findAllUsers(int page, int size);
	
	public User findUserById(int id);
	
	public void saveUser(User user);
	
	public void deleteUserById(int id);
	
	public User findUserByUsername(String username);

}
