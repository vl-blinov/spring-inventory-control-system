package ru.blinov.control.inventory.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.repository.InventoryCardRepository;
import ru.blinov.control.inventory.repository.UserRepository;
import ru.blinov.control.inventory.util.IdentifierGenerator;
import ru.blinov.control.inventory.util.InventoryFileHandler;

@Service
public class InventoryControlService {
	
	@Autowired
	private InventoryCardRepository inventoryCardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//User
	
	@Transactional
	public Page<User> findAllUsers(int page, int size) {	
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	@Transactional
	public User findUserById(int id) {
		
		Optional<User> user = userRepository.findById(id);

		return user.get();
	}
	
	@Transactional
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode("123"));
		
		userRepository.save(user);
	}
	
	@Transactional
	public void deleteUserById(int id) {
		
		deleteInventoryCardUser(id);
		
		userRepository.deleteById(id);
	}
	
	private void deleteInventoryCardUser(int id) {
		
		User user = findUserById(id);
		
		List<InventoryCard> inventoryCards = inventoryCardRepository.findAllByUser(user);
		
		for(int i = 0; i < inventoryCards.size(); i++) {
			inventoryCards.get(i).setUser(null);
		}	
	}
	
	//Inventory card
	
	@Transactional
	public Page<InventoryCard> findAllInventoryCards(int page, int size) {
		return inventoryCardRepository.findAll(PageRequest.of(page, size));
	}
	
	@Transactional
	public InventoryCard findInventoryCardById(int id) {
		
		Optional<InventoryCard> inventoryCard = inventoryCardRepository.findById(id);
		
		return inventoryCard.get();
	}
	
	@Transactional
	public void saveInventoryCard(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc, Principal principal) throws IOException {
		
		setInventoryCardIdentifier(inventoryCard);
		
		copyProductImage(inventoryCard, multipartFile, imageSrc);
		
		setInventoryCardUser(inventoryCard, principal);
		
		inventoryCardRepository.save(inventoryCard);
	}
	
	private void setInventoryCardIdentifier(InventoryCard inventoryCard) {
		
		String identifier = IdentifierGenerator.randomIdentifier();
		
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
	
	private void setInventoryCardUser(InventoryCard inventoryCard, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());
		
		inventoryCard.setUser(user);
	}
	
	private void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc) throws IOException {
		InventoryFileHandler.copyProductImage(inventoryCard, multipartFile, imageSrc);
	}
	
	@Transactional
	public void deleteInventoryCardById(int id, String folderName) throws IOException {
		
		InventoryFileHandler.deleteProductImageFromDirectory(folderName);
		
		inventoryCardRepository.deleteById(id);
	}

}