package app.ebr.repositories;

import org.springframework.data.repository.CrudRepository;

import app.ebr.domains.models.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
