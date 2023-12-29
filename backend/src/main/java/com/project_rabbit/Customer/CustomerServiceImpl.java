package com.project_rabbit.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

        private final CustomerRepository customerRepository;

        @Override
        public Integer createCustomer(Customer customer) {
                return customerRepository
                                .save(customer).getCustomerId();
        }

        @Override
        public Optional<Customer> findCustomer(Integer customerId) {
                return customerRepository
                                .findById(customerId);

        }

        @Override
        public Customer updateCustomer(Integer customerId, Customer customer) {
                Customer theCustomer = customerRepository.findById(customerId)
                                .stream()
                                .findFirst()
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                String.format("customer with id %s does not exist",
                                                                                customer.getCustomerId())));

                theCustomer.setEmail(customer.getEmail());

                return customerRepository
                                .save(theCustomer);
        }

        @Override
        public void deleteCustomer(Integer customerId) {
                Customer theCustomer = customerRepository
                                .findById(customerId).get();
                if (theCustomer == null) {
                        throw new RuntimeException(
                                        String.format("The customer with id %s does not exist",
                                                        customerId));
                }
                customerRepository
                                .delete(theCustomer);

        }
}
