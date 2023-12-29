package com.project_rabbit.Customer;

import java.util.Optional;

public interface CustomerService {
    Integer createCustomer(Customer customer);

    Optional<Customer> findCustomer(Integer customerId);

    Customer updateCustomer(Integer customerId,
            Customer customer);

    void deleteCustomer(Integer customerId);
}