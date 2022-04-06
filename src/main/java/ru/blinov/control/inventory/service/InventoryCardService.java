package ru.blinov.control.inventory.service;

import java.util.List;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

public interface InventoryCardService {
	
	//Inventory card
	
	public List<InventoryCard> findAll();
	
	public InventoryCard findById(int id);
	
	public void save(InventoryCard inventoryCard);
	
	public void deleteById(int id);
	
	public Boolean existsByIdentifier(String identifier);
	
	//User
	
	public User findUserByUsername(String username);

}
