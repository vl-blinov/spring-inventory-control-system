package ru.blinov.control.inventory.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.dao.InventoryCardRepository;
import ru.blinov.control.inventory.dao.UserRepository;
import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.util.IdentifierGenerator;
import ru.blinov.control.inventory.util.InventoryFileHandler;

@Service
public class InventoryControlServiceImpl implements InventoryControlService {
	
	@Autowired
	private InventoryCardRepository inventoryCardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//User
	
	@Override
	public Page<User> findAllUsers(int page, int size) {	
		return userRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public User findUserById(int id) {
		
		Optional<User> user = userRepository.findById(id);

		return user.get();
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode("123"));
		
		userRepository.save(user);
	}

	@Override
	public void deleteInventoryCardUser(User user) {
		
		List<InventoryCard> inventoryCards = inventoryCardRepository.findAllByUser(user);
		
		for(int i = 0; i < inventoryCards.size(); i++) {
			inventoryCards.get(i).setUser(null);
		}	
	}
	
	@Override
	public void deleteUserById(int id) {	
		userRepository.deleteById(id);
	}
	
	//Inventory card
	
	@Override
	public Page<InventoryCard> findAllInventoryCards(int page, int size) {
		return inventoryCardRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public InventoryCard findInventoryCardById(int id) {
		
		Optional<InventoryCard> inventoryCard = inventoryCardRepository.findById(id);
		
		return inventoryCard.get();
	}
	
	@Override
	public void setInventoryCardIdentifier(InventoryCard inventoryCard, String identifier) {
		
		while(true) {
			if(inventoryCardRepository.existsByIdentifier(identifier)) {
				identifier = IdentifierGenerator.randomIdentifier();
			}
			else {
				break;
			}
		}
		
		inventoryCard.setIdentifier(identifier);
	}
	
	@Override
	public void setInventoryCardUser(InventoryCard inventoryCard, String username) {

		User user = userRepository.findByUsername(username);
		
		inventoryCard.setUser(user);
	}
	
	@Override
	public void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc) {
		InventoryFileHandler.copyProductImage(inventoryCard, multipartFile, imageSrc);
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
	public void deleteImageFromDirectory(String fileName, String folderName) {

		Path deleteFile = Paths.get("./src/main/resources/static/images/products/" + folderName + "/" + fileName);
		Path deleteFolder = Paths.get("./src/main/resources/static/images/products/" + folderName);

		try {
			Files.delete(deleteFile);
			Files.delete(deleteFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}