package ru.blinov.control.inventory.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

public interface InventoryControlService {
	
	//User
	
	@Transactional
	public Page<User> findAllUsers(int page, int size);
	
	@Transactional
	public User findUserById(int id);
	
	@Transactional
	public User findUserByUsername(String username);
	
	@Transactional
	public void saveUser(User user);
	
	@Transactional
	public void deleteInventoryCardUser(User user);
	
	@Transactional
	public void deleteUserById(int id);
	
	//Inventory card
	
	@Transactional
	public Page<InventoryCard> findAllInventoryCards(int page, int size);
	
	@Transactional
	public InventoryCard findInventoryCardById(int id);
	
	@Transactional
	public void setInventoryCardIdentifier(InventoryCard inventoryCard, String identifier);
	
	@Transactional
	public void setInventoryCardUser(InventoryCard inventoryCard, String username);
	
	@Transactional
	public void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc);
	
	@Transactional
	public void saveInventoryCard(InventoryCard inventoryCard);
	
	@Transactional
	public void deleteInventoryCardById(int id);
	
	@Transactional
	public void deleteImageFromDirectory(String fileName, String folderName);

}