package fr.comprehensiveit.samples.om.repository;

import fr.comprehensiveit.samples.om.entity.OrderTerminal;
import fr.comprehensiveit.samples.om.entity.Terminal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("dev")
public class OrderTerminalRepositoryTest {

    @Autowired
    OrderTerminalRepository orderTerminalRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TerminalRepository terminalRepository;

    @Test
    @Sql("/new-customer.sql")
    @Sql("/new-terminal.sql")
    public void testSave() {
        OrderTerminal orderTerminal = new OrderTerminal();
        orderTerminal.setCustomer(customerRepository.findById(Long.decode("123456")).get());
        orderTerminal.setPrice(20.00);
        orderTerminal.setOrderTimestamp(Timestamp.from(Instant.now()));
        Terminal terminal = terminalRepository.findById(Long.decode("202301012")).get();
        List<Terminal> list = new ArrayList<Terminal>();
        list.add(terminal);
        orderTerminal.setTerminals(list);
        OrderTerminal orderTerminalResult = orderTerminalRepository.save(orderTerminal);
        assertEquals(20.00,orderTerminalResult.getPrice());
        assertEquals(Long.decode("202301012"),orderTerminalResult.getTerminals().get(0).getTerminalId());
        assertEquals(Long.decode("123456"),orderTerminalResult.getCustomer().getCustomerId());
    }
}
