package fr.comprehensiveit.samples.om.repository;

import fr.comprehensiveit.samples.om.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal,Long> {
}
