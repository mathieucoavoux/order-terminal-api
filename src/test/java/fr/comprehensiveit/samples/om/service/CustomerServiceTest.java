package fr.comprehensiveit.samples.om.service;

import fr.comprehensiveit.samples.om.config.OrderTerminalConfig;
import fr.comprehensiveit.samples.om.entity.Customer;
import fr.comprehensiveit.samples.om.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderTerminalConfig.class)
@ActiveProfiles("dev")
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("frida.kahlo@email.org");
        customer.setLastname("Kahlo");
        customer.setTitle("Mrs");
        customer.setFirstname("Frida");
        customer.setZipCode("2222");
        customer.setAddress("1 street of Artist, Mexico");
        ResponseEntity<Customer> responseEntity = customerService.createCustomer(customer);
        Customer resultCustomer = responseEntity.getBody();
        assertEquals("frida.kahlo@email.org",resultCustomer.getEmail());
        assert resultCustomer.getCustomerId() > 0;
    }

    @Test
    @Sql("/new-customer.sql")
    public void testFindCustomerByEmail() {
        ResponseEntity<Customer> responseEntity = customerService.findCustomerByEmail("marie.curie@email.org");
        Customer customer = responseEntity.getBody();
        assertEquals("marie.curie@email.org",customer.getEmail());
    }
}
