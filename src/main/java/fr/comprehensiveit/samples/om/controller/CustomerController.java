package fr.comprehensiveit.samples.om.controller;

import fr.comprehensiveit.samples.om.entity.Customer;
import fr.comprehensiveit.samples.om.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get-by-id")
    public ResponseEntity<Customer> getCustomerById(@RequestParam Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/get-by-email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        return customerService.findCustomerByEmail(email);
    }

    @PostMapping(path = "/create-customer",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }


}
