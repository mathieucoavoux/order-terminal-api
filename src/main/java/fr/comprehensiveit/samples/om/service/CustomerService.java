package fr.comprehensiveit.samples.om.service;

import fr.comprehensiveit.samples.om.entity.Customer;
import fr.comprehensiveit.samples.om.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    public ResponseEntity<Customer> getCustomerById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findById(id).get());
    }

    public ResponseEntity<Customer> createCustomer(Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.save(customer));
    }

    public ResponseEntity<Customer> findCustomerByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        Predicate predicateCustomerEmail = criteriaBuilder.equal(customerRoot.get("email"),email);
        query.where(predicateCustomerEmail);
        List<Customer> list = entityManager.createQuery(query).getResultList();
        ResponseEntity response;
        if(list.isEmpty()) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else if (list.size() > 1) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            response = ResponseEntity.status(HttpStatus.OK).body(list.get(0));
        }
        return response;

    }
}
