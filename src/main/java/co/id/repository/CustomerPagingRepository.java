package co.id.repository;

import co.id.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerPagingRepository extends JpaRepository<Customer, Long> {

}