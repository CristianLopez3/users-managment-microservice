package com.cristian.tiusers.repository;

import com.cristian.tiusers.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            """
            SELECT u FROM User u
            JOIN u.company c
            WHERE c.name = :company
            """
    )
    Page<User> findUsersByCompany(Pageable pageable, @Param("company") String company);




}
