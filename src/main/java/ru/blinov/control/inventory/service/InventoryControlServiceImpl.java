package ru.blinov.control.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.blinov.control.inventory.dao.InventoryCardRepository;
import ru.blinov.control.inventory.dao.UserRepository;
import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

@Service
public class InventoryControlServiceImpl implements InventoryControlService {
	
	@Autowired
	private InventoryCardRepository inventoryCardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//Inventory card
	
	@Override
	public List<InventoryCard> findAllInventoryCards() {
		return inventoryCardRepository.findAll();
	}
	
	@Override
	public InventoryCard findInventoryCardById(int id) {
		
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
	public void saveInventoryCard(InventoryCard inventoryCard) {	
		inventoryCardRepository.save(inventoryCard);
	}

	@Override
	public void deleteInventoryCardById(int id) {
		inventoryCardRepository.deleteById(id);
	}

	@Override
	public Boolean existsInventoryCardByIdentifier(String identifier) {
		Boolean identifierIsExist = inventoryCardRepository.existsByIdentifier(identifier);
		return identifierIsExist;
	}

	//User
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(int id) {
		
		Optional<User> result = userRepository.findById(id);
		User user = null;
		
		if(result.isPresent()) {
			user = result.get();
		}
		else {
			throw new RuntimeException();
		}
		
		return user;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public User findUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
	
}













