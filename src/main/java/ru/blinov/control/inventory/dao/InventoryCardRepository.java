package ru.blinov.control.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.blinov.control.inventory.entity.InventoryCard;
import ru.blinov.control.inventory.entity.User;

public interface InventoryCardRepository extends JpaRepository<InventoryCard, Integer> {
	
	public Boolean existsByIdentifier(String identifier);
	
	public List<InventoryCard> findAllByUser(User user);
}
