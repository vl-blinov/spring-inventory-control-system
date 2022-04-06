package ru.blinov.control.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.blinov.control.inventory.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
