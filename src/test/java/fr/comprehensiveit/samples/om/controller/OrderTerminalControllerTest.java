package fr.comprehensiveit.samples.om.controller;

import fr.comprehensiveit.samples.om.config.OrderTerminalConfig;
import fr.comprehensiveit.samples.om.entity.OrderTerminal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = OrderTerminalConfig.class)
@ActiveProfiles("integration")
public class OrderTerminalControllerTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("/new-order-terminal.sql")
    public void testGetOrderId() {
        String url = "http://localhost:" + port+"/v1/order-terminal/get-by-id?orderId=1";
        OrderTerminal orderTerminal = restTemplate.getForObject(url,OrderTerminal.class);
        assertEquals(Double.valueOf("200.00"),orderTerminal.getPrice());
    }
}
