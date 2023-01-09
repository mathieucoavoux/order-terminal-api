package fr.comprehensiveit.samples.om.service;

import fr.comprehensiveit.samples.om.entity.Customer;
import fr.comprehensiveit.samples.om.entity.OrderTerminal;
import fr.comprehensiveit.samples.om.entity.Terminal;
import fr.comprehensiveit.samples.om.repository.OrderTerminalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTerminalService {

    @Autowired
    private OrderTerminalRepository orderTerminalRegistry;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TerminalService terminalService;

    @Transactional
    public ResponseEntity<OrderTerminal> getOrderTerminalBy(Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderTerminalRegistry.findById(orderId).get());
    }

    public ResponseEntity<OrderTerminal> createOrder(String customerId,String[] terminalIds) {
        Customer customer = customerService.getCustomerById(Long.decode(customerId)).getBody();
        List<Terminal> list = new ArrayList<Terminal>();
        if(null == terminalIds) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
        for(String terminalId : terminalIds) {
            Terminal terminal = terminalService.getTerminalById(Long.decode(terminalId)).getBody();
            list.add(terminal);
        }
        OrderTerminal orderTerminal = new OrderTerminal();
        orderTerminal.setOrderTimestamp(Timestamp.from(Instant.now()));
        orderTerminal.setCustomer(customer);
        orderTerminal.setPrice(200.00);
        orderTerminal.setTerminals(list);
        return ResponseEntity.status(HttpStatus.OK).body(orderTerminalRegistry.save(orderTerminal));
    }
}
