package ru.blinov.control.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.blinov.control.inventory.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
}
