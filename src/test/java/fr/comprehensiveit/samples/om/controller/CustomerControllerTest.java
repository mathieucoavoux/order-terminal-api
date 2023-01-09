package fr.comprehensiveit.samples.om.controller;

import fr.comprehensiveit.samples.om.config.OrderTerminalConfig;
import fr.comprehensiveit.samples.om.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = OrderTerminalConfig.class)
@ActiveProfiles("integration")
public class CustomerControllerTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("/new-customer.sql")
    public void testGetCustomer() {
        String url = "http://localhost:" + port+"/v1/customer/get-by-id?id=123456";
        Customer customer = restTemplate.getForObject(url, Customer.class);
        assertEquals("Marie",customer.getFirstname());
    }
}
