package ru.blinov.control.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.blinov.control.inventory.dao.InventoryCardRepository;
import ru.blinov.control.inventory.entity.InventoryCard;

@Service
public class InventoryCardServiceImpl implements InventoryCardService {
	
	@Autowired
	private InventoryCardRepository inventoryCardRepository;
	
	@Override
	public List<InventoryCard> findAll() {
		
		return inventoryCardRepository.findAll();
	}

	@Override
	public InventoryCard findById(int id) {
		
		Optional<InventoryCard> result = inventoryCardRepository.findById(id);
		
		InventoryCard inventoryCard = null;
		
		if(result.isPresent()) {
			inventoryCard = result.get();
		}
		else {
			throw new RuntimeException();
		}
		
		return inventoryCard;
	}

	@Override
	public void save(InventoryCard inventoryCard) {
		
		inventoryCardRepository.save(inventoryCard);

	}

	@Override
	public void deleteBuId(int id) {
		
		inventoryCardRepository.deleteById(id);

	}

}
