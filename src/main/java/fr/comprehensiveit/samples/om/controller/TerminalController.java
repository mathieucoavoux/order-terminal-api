package fr.comprehensiveit.samples.om.controller;

import fr.comprehensiveit.samples.om.entity.Terminal;
import fr.comprehensiveit.samples.om.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @GetMapping("/get-by-id")
    public ResponseEntity<Terminal> getTerminalById(@RequestParam Long id) {
        return terminalService.getTerminalById(id);
    }

    @PostMapping(path = "/create-terminal",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Terminal> createTerminal(@RequestBody Terminal terminal) {
        return terminalService.createTerminal(terminal);
    }
}
