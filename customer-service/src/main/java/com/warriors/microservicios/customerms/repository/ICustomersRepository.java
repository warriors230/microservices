package com.warriors.microservicios.customerms.repository;

import com.warriors.microservicios.customerms.entity.Customers;
import com.warriors.microservicios.customerms.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomersRepository extends JpaRepository<Customers, Long> {
    public Customers findByNumberID(String numberID);

    public List<Customers> findByLastName(String lastName);

    public List<Customers> findByRegion(Region region);
}
