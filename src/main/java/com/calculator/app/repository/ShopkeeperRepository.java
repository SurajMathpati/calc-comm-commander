package com.calculator.app.repository;

import com.calculator.app.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Long> {
}
