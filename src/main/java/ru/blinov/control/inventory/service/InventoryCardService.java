package ru.blinov.control.inventory.service;

import java.util.List;

import ru.blinov.control.inventory.dao.projection.InventoryCardIdentifier;
import ru.blinov.control.inventory.entity.InventoryCard;

public interface InventoryCardService {
	
	public List<InventoryCard> findAll();
	
	public InventoryCard findById(int id);
	
	public void save(InventoryCard inventoryCard);
	
	public void deleteById(int id);
	
	public List<InventoryCardIdentifier> findAllIdentifiers();

}
