package ru.blinov.control.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.blinov.control.inventory.dao.projection.InventoryCardIdentifier;
import ru.blinov.control.inventory.entity.InventoryCard;

public interface InventoryCardRepository extends JpaRepository<InventoryCard, Integer> {
	
	List<InventoryCardIdentifier> findAllBy();

}
