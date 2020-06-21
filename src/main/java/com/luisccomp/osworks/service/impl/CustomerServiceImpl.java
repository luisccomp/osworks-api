package com.luisccomp.osworks.service.impl;

import com.luisccomp.osworks.domain.model.Customer;
import com.luisccomp.osworks.exception.NotFoundException;
import com.luisccomp.osworks.repository.CustomerRepository;
import com.luisccomp.osworks.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> find() {
        return repository.findAll();
    }

    @Override
    public Customer find(Long id) {
        var optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundException("Customer not found.");
        }
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        final var optional = repository.findById(id);

        if (optional.isPresent()) {
            customer.setId(id);

            return repository.save(customer);
        } else {
            throw new NotFoundException("Customer not found.");
        }
    }

    @Override
    public void delete(Long id) {
        final var optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new NotFoundException("Customer not found.");
        }
    }

}
