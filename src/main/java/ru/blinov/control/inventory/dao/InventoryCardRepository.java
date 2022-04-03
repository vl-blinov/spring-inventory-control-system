package ru.blinov.control.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.blinov.control.inventory.entity.InventoryCard;

public interface InventoryCardRepository extends JpaRepository<InventoryCard, Integer> {

	Boolean existsByIdentifier(String identifier);
}
