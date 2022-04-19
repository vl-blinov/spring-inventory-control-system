package ru.blinov.control.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

public interface InventoryControlService {
	
	//Inventory card
	public Page<InventoryCard> findAllInventoryCards(int page, int size);
	
	public InventoryCard findInventoryCardById(int id);
	
	public void saveInventoryCard(InventoryCard inventoryCard);
	
	public void deleteInventoryCardById(int id);
	
	public Boolean existsInventoryCardByIdentifier(String identifier);
	
	//User
	public List<User> findAllUsers();
	
	public User findUserById(int id);
	
	public void saveUser(User user);
	
	public void deleteUserById(int id);
	
	public User findUserByUsername(String username);

}
