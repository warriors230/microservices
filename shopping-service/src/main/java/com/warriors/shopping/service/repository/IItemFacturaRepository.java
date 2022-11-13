package com.warriors.shopping.service.repository;

import com.warriors.shopping.service.entities.FacturaItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemFacturaRepository extends JpaRepository<FacturaItems,Long> {
}
