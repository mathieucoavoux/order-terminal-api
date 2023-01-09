package fr.comprehensiveit.samples.om.repository;

import fr.comprehensiveit.samples.om.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
