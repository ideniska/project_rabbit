package com.project_rabbit.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Integer> createCustomer(@RequestBody Customer customer) {
        Integer customerId = customerService.createCustomer(customer);
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable("id") Integer customerId) {
        Customer theCustomer = customerService.findCustomer(customerId)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("customer with id %s does not exist", customerId)));
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer customerId,
            @RequestBody Customer customer) {
        Customer theCustomer = customerService
                .updateCustomer(customerId, customer);
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Integer customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}