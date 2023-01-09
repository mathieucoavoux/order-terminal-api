package fr.comprehensiveit.samples.om.repository;

import fr.comprehensiveit.samples.om.entity.OrderTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTerminalRepository extends JpaRepository<OrderTerminal,Long> {

}
