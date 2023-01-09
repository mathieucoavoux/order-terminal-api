package fr.comprehensiveit.samples.om.repository;


import fr.comprehensiveit.samples.om.entity.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("dev")
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;


    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setEmail("frida.kahlo@email.com");
        customer.setLastname("Kahlo");
        customer.setTitle("Mrs");
        customer.setFirstname("Frida");
        customer.setZipCode("2222");
        customer.setAddress("Mexico");
        assertEquals("Frida",customerRepository.save(customer).getFirstname());
    }
}
