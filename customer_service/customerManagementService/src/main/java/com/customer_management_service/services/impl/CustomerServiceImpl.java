package com.customer_management_service.services.impl;

import com.customer_management_service.entites.Customer;
import com.customer_management_service.exceptions.ResourceNotFoundException;
import com.customer_management_service.repositories.CustomerRepository;
import com.customer_management_service.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
     CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private AtomicLong sequenceId = new AtomicLong(1000);

    @Override
    public Customer create(Customer customer) {

//        String userId = UUID.randomUUID().toString();
//        customer.setCustomerId(userId);

        long nextId = sequenceId.getAndIncrement();
        customer.setCustomerId(String.valueOf(nextId));

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer get(String id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with given id not found"));

        return customer;
    }
//    public ResponseEntity<Customer> get(String id) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(id);
//        if (optionalCustomer.isEmpty()) {
//            throw new ResourceNotFoundException("Customer with given id not found");
//        }
//        Customer customer = optionalCustomer.get();
//        return ResponseEntity.ok(customer);
//    }

    @Override
    public Customer update(String id, Customer customer) {
        Customer customer1 = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with given id not found"));
        customer1.setName(customer.getName());
        customer1.setPhone(customer.getPhone());
        customer1.setEmail(customer.getEmail());
        customer1.setAddress(customer.getAddress());


        return customerRepository.save(customer1);
    }

    @Override
    public void delete(String id) {


        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with given id not found"));
            boolean custDel = customerRepository.existsById(id);
            if(!custDel){
                throw new IllegalStateException("customer deleted");
            }

        customerRepository.deleteById(id);
    }
}
