package ru.blinov.control.inventory.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;
import ru.blinov.control.inventory.repository.InventoryCardRepository;
import ru.blinov.control.inventory.repository.UserRepository;
import ru.blinov.control.inventory.util.IdentifierGenerator;
import ru.blinov.control.inventory.util.InventoryFileHandler;

@Service
public class InventoryControlService {
	
	private UserRepository userRepository;
	
	private InventoryCardRepository inventoryCardRepository;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public InventoryControlService(UserRepository userRepository, InventoryCardRepository inventoryCardRepository,
								   PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.inventoryCardRepository = inventoryCardRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional(readOnly = true)
	@Cacheable(value = "users")
	public Page<User> findAllUsers(int page, int size) {	
		return userRepository.findAll(PageRequest.of(page, size));
	}

	@Transactional(readOnly = true)
	public User findUserById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User with id '" + id + "' does not exist."));
	}
	
	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("User with username '" + username + "' does not exist."));
	}
	
	@Transactional
	@CacheEvict(value = "users", allEntries = true)
	public void saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode("123"));
		
		userRepository.save(user);
	}
	
	@Transactional
	@CacheEvict(value = "users", allEntries = true)
	public void deleteUserById(Integer id) {
		
		deleteInventoryCardUser(id);
		
		userRepository.deleteById(id);
	}

	private void deleteInventoryCardUser(int id) {
		
		User user = findUserById(id);
		
		List<InventoryCard> inventoryCards = inventoryCardRepository.findAllByUser(user);
		
		inventoryCards.forEach(inventoryCard -> inventoryCard.setUser(null));
	}
	
	@Transactional(readOnly = true)
	@Cacheable(value = "inventoryCards")
	public Page<InventoryCard> findAllInventoryCards(int page, int size) {
		return inventoryCardRepository.findAll(PageRequest.of(page, size));
	}
	
	@Transactional(readOnly = true)
	public InventoryCard findInventoryCardById(int id) {
		return inventoryCardRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Inventory card with id '" + id + "' does not exist"));
	}
	
	@Transactional
	@CacheEvict(value = "inventoryCards", allEntries = true)
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
	
	private void copyProductImage(InventoryCard inventoryCard, MultipartFile multipartFile, String imageSrc) throws IOException {
		InventoryFileHandler.copyProductImage(inventoryCard, multipartFile, imageSrc);
	}
	
	private void setInventoryCardUser(InventoryCard inventoryCard, Principal principal) {

		User user = findUserByUsername(principal.getName());
		
		inventoryCard.setUser(user);
	}
	
	@Transactional
	@CacheEvict(value = "inventoryCards", allEntries = true)
	public void deleteInventoryCardById(int id, String folderName) {
		
		findInventoryCardById(id);
		
		InventoryFileHandler.deleteProductImageFromDirectory(folderName);
		
		inventoryCardRepository.deleteById(id);
	}

}