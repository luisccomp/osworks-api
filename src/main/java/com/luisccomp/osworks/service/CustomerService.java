package com.luisccomp.osworks.service;

import com.luisccomp.osworks.domain.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> find();

    Customer find(Long id);

    Customer save(Customer customer);

    Customer update(Long id, Customer customer);

    void delete(Long id);

}
