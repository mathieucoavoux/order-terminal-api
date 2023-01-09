package fr.comprehensiveit.samples.om.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.comprehensiveit.samples.om.entity.OrderTerminal;
import fr.comprehensiveit.samples.om.service.OrderTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order-terminal")
public class OrderTerminalController {


    @Autowired
    private OrderTerminalService orderTerminalService;

    @GetMapping("/get-by-id")
    public ResponseEntity<OrderTerminal> getOrderTerminalBy(@RequestParam Long orderId) {
        return orderTerminalService.getOrderTerminalBy(orderId);
    }

    @GetMapping(path="/create-order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderTerminal> createOrder(@JsonProperty String customerId, @JsonProperty String[] terminalIds) {
        return orderTerminalService.createOrder(customerId,terminalIds);

    }
}
