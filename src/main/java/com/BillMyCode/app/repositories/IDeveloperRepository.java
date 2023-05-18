package com.BillMyCode.app.repositories;

import com.BillMyCode.app.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeveloperRepository extends JpaRepository<Developer, Long> {

    @Query("SELECT d FROM Developer d WHERE d.seniority = :seniority")
    public List<Developer> searchBySeniority(@Param("seniority") String seniority);

}