package fr.comprehensiveit.samples.om.repository;

import fr.comprehensiveit.samples.om.entity.Terminal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("dev")
public class TerminalRepositoryTest {

    @Autowired
    TerminalRepository terminalRepository;

    @Test
    public void testSave() {
        Terminal terminal = new Terminal();
        terminal.setManufacturer("C");
        terminal.setModel("R");
        terminal.setVersion("v");
        terminal.setSerialNumber("SN1");
        Terminal terminalResult = terminalRepository.save(terminal);
        assertEquals("C",terminalResult.getManufacturer());
    }
}
