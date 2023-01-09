package fr.comprehensiveit.samples.om.service;

import fr.comprehensiveit.samples.om.config.OrderTerminalConfig;
import fr.comprehensiveit.samples.om.entity.OrderTerminal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderTerminalConfig.class)
@ActiveProfiles("dev")
public class OrderTerminalServiceTest {

    @Autowired
    private OrderTerminalService orderTerminalService;

    @Test
    @Sql("/new-customer.sql")
    @Sql("/new-terminal.sql")
    public void createOrder() {
        String[] terminals = { "202301012" };
        OrderTerminal orderTerminal = orderTerminalService.createOrder("123456",terminals).getBody();
        assertEquals(Long.decode("123456"),orderTerminal.getCustomer().getCustomerId());
    }

    @Test
    @Sql("/new-order-terminal.sql")
    public void getOrderById() {
        OrderTerminal orderTerminal = orderTerminalService.getOrderTerminalBy(Long.decode("1")).getBody();
        assertEquals(Long.decode("123456"),orderTerminal.getCustomer().getCustomerId());
    }
}
