package com.warriors.customerservice.service;

import com.warriors.customerservice.entities.Customers;
import com.warriors.customerservice.entities.Region;

import java.util.List;

public interface ICustomerService {
    public List<Customers> findAll();

    public Customers save(Customers customers);

    public Customers update(Long id, Customers customers);

    public void delete(Long id);

    public Customers findByNumberID(String numberID);

    public List<Customers> findByLastName(String lastName);

    public List<Customers> findByRegion(Region region);

    public Customers findById(Long id);
}
