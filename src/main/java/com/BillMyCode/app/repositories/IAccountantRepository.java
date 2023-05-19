package com.BillMyCode.app.repositories;

import com.BillMyCode.app.entities.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountantRepository extends JpaRepository<Accountant, Long> {
}
