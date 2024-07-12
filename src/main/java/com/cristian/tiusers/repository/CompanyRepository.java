package com.cristian.tiusers.repository;

import com.cristian.tiusers.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("""
        SELECT c FROM Company c
        WHERE c.name LIKE %:name%
    """)
    Optional<List<Company>> findByNameLike(String name);


}
