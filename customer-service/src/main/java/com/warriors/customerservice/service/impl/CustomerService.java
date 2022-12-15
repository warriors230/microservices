package com.warriors.customerservice.service.impl;

import com.warriors.customerservice.entities.Customers;
import com.warriors.customerservice.entities.Region;
import com.warriors.customerservice.repository.ICustomersRepository;
import com.warriors.customerservice.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

   private final ICustomersRepository ICustomersRepository;

    @Override
    public List<Customers> findAll() {
        return ICustomersRepository.findAll();
    }

    @Override
    public Customers save(Customers customers) {
        Customers customersBD = ICustomersRepository.findByNumberID(customers.getNumberID());
        if (customersBD != null) {
            return customersBD;
        }
        customers.setState("CREATED");
        customersBD = ICustomersRepository.save(customers);
        return customersBD;
    }

    @Override
    public Customers update(Long id, Customers customers) {
        Customers customersBD = ICustomersRepository.findById(id).orElse(null);
        if (customersBD == null) {
            return null;
        }
        customersBD.setFirtsName(customers.getFirtsName());
        customersBD.setLastName(customers.getLastName());
        customersBD.setEmail(customers.getEmail());
        customersBD.setPhotoUrl(customers.getPhotoUrl());
        return ICustomersRepository.save(customersBD);
    }

    @Override
    public void delete(Long id) {
        ICustomersRepository.deleteById(id);
    }

    @Override
    public Customers findByNumberID(String numberID) {
        return ICustomersRepository.findByNumberID(numberID);
    }

    @Override
    public List<Customers> findByLastName(String lastName) {
        return ICustomersRepository.findByLastName(lastName);
    }

    @Override
    public List<Customers> findByRegion(Region region) {
        return ICustomersRepository.findByRegion(region);
    }


    public Customers findById(Long id) {
        return ICustomersRepository.findById(id).orElse(null);
    }
}
